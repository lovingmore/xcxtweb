package com.sh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sh.entity.Menu;
import com.sh.entity.Role;
import com.sh.entity.RolePerm;
import com.sh.service.MenuService;
import com.sh.service.RolePermService;
import com.sh.service.RoleService;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;
import com.sh.vo.TreeNode;

@Controller
@RequestMapping(value="/admin/role")
public class RoleController {
	private Logger log = Logger.getLogger(RoleController.class);
	
	@Resource
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RolePermService rolePermService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey){
		ModelAndView model = new ModelAndView("/admin/role/role-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		int count = roleService.count(map);
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
		List<Role> list = roleService.list(map, start, Pager.PAGE_SIZE);
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id){
		ModelAndView model = new ModelAndView("/admin/role/role-edit");
		if(id!=null && id!=0){
			Role role = roleService.get(id);
			model.addObject("role", role);
		}
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(Role role){
		ResultData rd = new ResultData();
		if(role!=null){
			if(role.getId()!=null && role.getId()!=0){
				Role role_o = roleService.get(role.getId());
				role_o.setName(role.getName());
				roleService.update(role_o);
			}else{
				role.setCreateTime(new Date());
				roleService.save(role);
			}
			rd.setStatus(1);
		}
		return rd;
	}
	
	@RequestMapping("/allotMenu.do")
	public ModelAndView allotMenu(Integer id){
		ModelAndView model = new ModelAndView("/admin/role/allot-menu");
		List<TreeNode> list_tree = new ArrayList<TreeNode>();
		List<Menu> list_m = menuService.list(null);
		if(id!=null && list_m!=null && list_m.size()>0){
			List<RolePerm> list_rp = rolePermService.getByRoleId(id);
			TreeNode tn = null;
			for(Menu mu : list_m){
				tn = new TreeNode();
				tn.setId(mu.getId());
				tn.setpId(mu.getParentId());
				tn.setName(mu.getName());
				tn.setOpen(true);
				if(list_rp!=null && list_rp.size()>0){
					for(RolePerm rp : list_rp){
						if(rp.getMenuId()==mu.getId()){
							tn.setChecked(true);
						}
					}
				}
				list_tree.add(tn);
			}
		}
		System.out.println(JSON.toJSONString(list_tree));
		model.addObject("treeNode", JSON.toJSONString(list_tree));
		model.addObject("id", id);
		return model;
	}
	
	@RequestMapping("/saveAllotMenu.do")
	@ResponseBody
	public ResultData saveAllotMenu(Integer id, Integer[] ids){
		ResultData rd = new ResultData();
		if(id!=null && ids!=null){
			List<RolePerm> list = new ArrayList<RolePerm>();
			RolePerm rp = null;
			Role role = roleService.get(id);
			for(Integer mid : ids){
				rp = new RolePerm();
				rp.setRoleId(id);
				rp.setMenuId(mid);
				list.add(rp);
			}
			rolePermService.saveList(id, list);
			rd.setStatus(1);
		}else{
			rd.setStatus(0);
			rd.setMessage("缺少参数，无法保存");
		}
		return rd;
	}
}
