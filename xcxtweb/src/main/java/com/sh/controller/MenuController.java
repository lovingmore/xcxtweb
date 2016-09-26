package com.sh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sh.entity.Menu;
import com.sh.entity.User;
import com.sh.service.MenuService;
import com.sh.util.Global;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;

@Controller
@RequestMapping(value="/admin/menu")
public class MenuController {
	private Logger log = Logger.getLogger(MenuController.class);
	
	@Resource
	private MenuService menuService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey){
		ModelAndView model = new ModelAndView("/admin/menu/menu-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		int count = menuService.count(map);
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
		List<Menu> list = menuService.list(map, start, Pager.PAGE_SIZE);
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id){
		ModelAndView model = new ModelAndView("/admin/menu/menu-edit");
		if(id!=null && id!=0){
			Menu menu = menuService.get(id);
			model.addObject("menu", menu);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		List<Menu> menus = menuService.list(map);
		model.addObject("menus", menus);
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(Menu menu){
		ResultData rd = new ResultData();
		if(menu!=null){
			if(menu.getId()!=null && menu.getId()!=0){
				Menu menu_o = menuService.get(menu.getId());
				menu_o.setName(menu.getName());
				menu_o.setListno(menu.getListno());
				menu_o.setParentId(menu.getParentId());
				menu_o.setUrl(menu.getUrl());
				menuService.update(menu_o);
			}else{
				menu.setCreateTime(new Date());
				menuService.save(menu);
			}
			rd.setStatus(1);
		}
		return rd;
	}
}
