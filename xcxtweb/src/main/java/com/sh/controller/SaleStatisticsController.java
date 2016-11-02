package com.sh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sh.entity.FoodCategory;
import com.sh.service.FoodCategoryService;
import com.sh.service.OrderDetailService;
import com.sh.service.OrderService;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;
import com.sh.vo.PurchaseOrderVo;
import com.sh.vo.TreeNode;

@Controller
@RequestMapping(value="/admin/saleStatistics")
public class SaleStatisticsController {
	private Logger log = Logger.getLogger(SaleStatisticsController.class);
	
	@Resource
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private FoodCategoryService foodCategoryService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey, String startDate, String endDate, 
			String foodCategoryName, Integer foodCategoryId, String purchaser){
		String url = "/admin/statistics/sale-list";
		ModelAndView model = new ModelAndView(url);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		if(!StringUtils.isEmpty(startDate)){
			map.put("startDate", startDate);
		}
		if(!StringUtils.isEmpty(endDate)){
			map.put("endDate", endDate);
		}
		if(foodCategoryId!=null){
			map.put("foodCategoryId", foodCategoryId);
		}
		if(!StringUtils.isEmpty(purchaser)){
			map.put("purchaser", purchaser);
		}
		int count = orderService.countPurchaseOrderByMap(map);
		model.addObject("count", count);
		model.addObject("page", new Pager(count));
		model.addObject("searchKey", searchKey);
		model.addObject("startDate", startDate);
		model.addObject("endDate", endDate);
		model.addObject("foodCategoryId",foodCategoryId);
		model.addObject("foodCategoryName",foodCategoryName);
		model.addObject("purchaser",purchaser);
		//获取菜品树
		List<TreeNode> list_tree = new ArrayList<TreeNode>();
		Map<String, Object> map_param = new HashMap<String, Object>();
		map_param.put("status", 0);
		List<FoodCategory> list_cat = foodCategoryService.list(map_param);
		if(list_cat!=null && list_cat.size()>0){
			TreeNode tn = null;
			for(FoodCategory fc : list_cat){
				tn = new TreeNode();
				tn.setId(fc.getId());
				tn.setpId(fc.getParentId()==null?0:fc.getParentId());
				tn.setName(fc.getName());
				tn.setOpen(true);
				list_tree.add(tn);
			}
		}
		model.addObject("treeNode", JSON.toJSONString(list_tree));
		return model;
	}
	
	@RequestMapping("/listAjax.do")
	@ResponseBody
	public ResultData listAjax(Integer pageId, String searchKey, String startDate, String endDate, 
			String foodCategoryName, Integer foodCategoryId, String purchaser){
		int start = PageUtil.getStart(pageId, Pager.PAGE_SIZE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		if(!StringUtils.isEmpty(startDate)){
			map.put("startDate", startDate);
		}
		if(!StringUtils.isEmpty(endDate)){
			map.put("endDate", endDate);
		}
		if(foodCategoryId!=null){
			map.put("foodCategoryId", foodCategoryId);
		}
		if(!StringUtils.isEmpty(purchaser)){
			map.put("purchaser", purchaser);
		}
		List<PurchaseOrderVo> list = orderService.findPurchaseOrderByMap(map, start, Pager.PAGE_SIZE);
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
}
