package com.sh.controller;

import java.util.Date;
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

import com.sh.entity.Role;
import com.sh.entity.User;
import com.sh.service.RoleService;
import com.sh.service.UserService;
import com.sh.util.Global;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.PasswordUtil;
import com.sh.util.ResultData;

@Controller
@RequestMapping(value="/admin/user")
public class UserController {
	private Logger log = Logger.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey){
		log.info("UserController's list method..........");
		ModelAndView model = new ModelAndView("/admin/user/user-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		int count = userService.count(map);
		model.addObject("count", count);
		model.addObject("page", new Pager(count));
		model.addObject("searchKey", searchKey);
		return model;
	}
	
	@RequestMapping("/listAjax.do")
	@ResponseBody
	public ResultData listAjax(Integer pageId, String searchKey){
		int start = PageUtil.getStart(pageId, Pager.PAGE_SIZE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		List<User> list = userService.list(map, start, Pager.PAGE_SIZE);
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id){
		ModelAndView model = new ModelAndView("/admin/user/user-edit");
		if(id!=null && id!=0){
			User user = userService.get(id);
			model.addObject("user", user);
		}
		List<Role> roles = roleService.findAll();
		model.addObject("roles", roles);
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(User user){
		ResultData rd = new ResultData();
		if(user!=null){
			User usr = userService.getByUsername(user.getUsername());
			if(user.getId()!=null && user.getId()!=0){
				User user_o = userService.get(user.getId());
				if(usr!=null && !usr.getUsername().equals(user_o.getUsername())){
					rd.setMessage("账号已经存在，无法保存");
				}else{
					user_o.setName(user.getName());
					user_o.setRoleId(user.getRoleId());
					userService.update(user_o);
					rd.setStatus(1);
				}
			}else{
				if(usr!=null){
					rd.setMessage("账号已经存在，无法保存");
				}else{
					user.setCreateTime(new Date());
					user.setDr(0);
					user.setPassword(PasswordUtil.encryt(Global.INIT_PWD));
					userService.save(user);
					rd.setStatus(1);
				}
			}
		}
		return rd;
	}
	
	@RequestMapping("/initPwd.do")
	@ResponseBody
	public ResultData initPwd(Integer id){
		ResultData rd = new ResultData();
		if(id!=null){
			User user = userService.get(id);
			user.setPassword(PasswordUtil.encryt(Global.INIT_PWD));
			userService.update(user);
			rd.setStatus(1);
		}else{
			rd.setMessage("获取用户失败");
		}
		return rd;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResultData delete(Integer id){
		ResultData rd = new ResultData();
		if(id!=null){
			userService.delete(id);
			rd.setStatus(1);
		}else{
			rd.setMessage("获取用户失败");
		}
		return rd;
	}
	
	@RequestMapping("/toUpdatePwd.do")
	public ModelAndView toUpdatePwd(HttpServletRequest request){
		ModelAndView model = new ModelAndView("/admin/user/user-pwd-edit");
		return model;
	}
	
	@RequestMapping("/updatePwd.do")
	@ResponseBody
	public ResultData updatePwd(HttpServletRequest request, String oldPassword, String newPassword){
		ResultData rd = new ResultData();
		Integer userId = (Integer)request.getSession().getAttribute(Global.USERID_SESSION);
		if(userId!=null){
			User user = userService.get(userId);
			if(user!=null){
				if(user.getPassword().equals(PasswordUtil.encryt(oldPassword))){
					user.setPassword(PasswordUtil.encryt(newPassword));
					userService.update(user);
					rd.setStatus(1);
				}else{
					rd.setMessage("输入的原密码不正确");
				}
			}else{
				rd.setMessage("获取用户信息错误");
			}
		}else{
			rd.setMessage("请刷新重新登录");
		}
		return rd;
	}
	
}
