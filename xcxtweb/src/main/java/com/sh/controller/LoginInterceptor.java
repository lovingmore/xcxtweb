package com.sh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sh.util.Global;
import com.sh.util.UncheckUrlsUtil;
/*
 * 在Struts中  拦截器的上下文是通过, ActionContext来获取的（Spring的配置文件就在Context中）
 * MVC中,拦截器的上下文与过滤器相同, 吧request作为形参传入， 设置完毕后要手动配置拦截器
 * 
 * */
public class LoginInterceptor implements HandlerInterceptor {
	
	/** 
	* 免登入 免检查地址 
	*/ 
	private List<String> uncheckUrls;

	public List<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("-------在action之前执行,如果返回true,则继续向后执行--------");
//		System.out.println(request.getParameter("name"));
		// 此处实现登陆的拦截判断
		//过滤无须拦截的url
		String requestUrl = request.getRequestURI();
		System.out.println("requestUrl:"+requestUrl);
		if(UncheckUrlsUtil.getUncheckUrls().contains(requestUrl)){
			return true; 
		}
		//判断是否session过期
		if(request.getSession().getAttribute(Global.USERID_SESSION)==null){
			System.out.println(request.getContextPath());
			if(requestUrl.startsWith("/phone/")){
				response.sendRedirect("/phone/login.do");
			}else{
				response.sendRedirect("/admin/login.do");
			}
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println("----在Action 方法执行完毕之后,执行(没有抛异常的话)----------");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("----在Action 方法执行完毕之后,无论是否抛出异常,通常用来进行异常处理----------");
	}

}
