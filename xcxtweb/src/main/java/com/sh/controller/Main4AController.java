package com.sh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sh.entity.User;
import com.sh.service.MenuService;
import com.sh.service.UserService;
import com.sh.util.Global;
import com.sh.util.ResultData;
import com.sh.vo.MenuVo;

@Controller
@RequestMapping(value="/admin")
public class Main4AController {
	private Logger log = Logger.getLogger(Main4AController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
	
	@RequestMapping("/index.do")
	public ModelAndView index(HttpServletRequest request){
		log.info("Main4AController index method..........");
		Integer userId = (Integer)request.getSession().getAttribute(Global.USERID_SESSION);
		ModelAndView model = new ModelAndView("/admin/index");
		User user = null;
		if(userId!=null){
			user = userService.get(userId);
			model.addObject("user", user);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		if(user!=null && user.getUsername().equals("admin")){
			//管理员过滤
		}else{
			map.put("userId", userId);
		}
		List<MenuVo> menuVos = menuService.findAll(map);
		log.info(JSON.toJSONString(menuVos));
		model.addObject("menus", menuVos);
		
		return model;
	}
	
	@RequestMapping("/login.do")
	public ModelAndView login(){
		ModelAndView model = new ModelAndView("/admin/login");
		return model;
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletRequest request){
		ModelAndView model = new ModelAndView("redirect:/admin/login.do");
		request.getSession().removeAttribute(Global.USERID_SESSION);
		return model;
	}
	
	@RequestMapping("/checkLogin.do")
	@ResponseBody
	public ResultData checkLogin(String username, String password,HttpServletRequest request){
		ResultData rd = new ResultData();
		User user = userService.checkLogin(username, password);
		if(user!=null){
			request.getSession().setAttribute(Global.USERID_SESSION, user.getId());
			rd.setStatus(1);
		}else{
			rd.setStatus(0);
			rd.setMessage("用户名或密码错误");
		}
		return rd;
	}
	
}
