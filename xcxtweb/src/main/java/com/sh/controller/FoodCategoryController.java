package com.sh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sh.entity.FoodCategory;
import com.sh.service.FoodCategoryService;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;
import com.sh.vo.FoodCategoryVo;
import com.sh.vo.TreeNode;

@Controller
@RequestMapping(value="/admin/foodCategory")
public class FoodCategoryController {
	private Logger log = Logger.getLogger(FoodCategoryController.class);
	
	@Resource
	private FoodCategoryService foodCategoryService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey, Integer parentId){
		ModelAndView model = new ModelAndView("/admin/foodCategory/food-category-list");
		if(parentId==null){
			parentId = 0;
		}else{
			model = new ModelAndView("/admin/foodCategory/food-category-list-c");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("parentId", parentId);
		int count = foodCategoryService.count(map);
		model.addObject("count", count);
		model.addObject("page", new Pager(count));
		model.addObject("searchKey", searchKey);
		model.addObject("parentId", parentId);
		return model;
	}
	
	@RequestMapping("/listAjax.do")
	@ResponseBody
	public ResultData listAjax(Integer pageId, String searchKey, Integer parentId){
		ResultData rd = new ResultData();
		try {
			if(parentId==null){
				parentId=0;
			}
			int start = PageUtil.getStart(pageId, Pager.PAGE_SIZE);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("searchKey", searchKey);
			map.put("parentId", parentId);
			List<FoodCategory> list = foodCategoryService.list(map, start, Pager.PAGE_SIZE);
			if(parentId!=0 && list!=null && list.size()>0){
				FoodCategory foodCategory = foodCategoryService.get(parentId);
				List<FoodCategoryVo> list_vo = new ArrayList<FoodCategoryVo>();
				if(foodCategory!=null){
					FoodCategoryVo fcVo = null;
					for(FoodCategory fc : list){
						fcVo = new FoodCategoryVo();
						BeanUtils.copyProperties(fc, fcVo);
						fcVo.setParentName(foodCategory.getName());
						list_vo.add(fcVo);
					}
				}
				rd.setResult(list_vo);
			}else{
				rd.setResult(list);
			}
			rd.setStatus(1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id, Boolean isChild, Integer parentId){
		ModelAndView model = new ModelAndView("/admin/foodCategory/food-category-edit");
		if(id!=null && id!=0){
			FoodCategory fc = foodCategoryService.get(id);
			model.addObject("foodCategory", fc);
		}
		model.addObject("isChild", isChild);
		model.addObject("parentId", parentId);
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(FoodCategory foodCategory){
		ResultData rd = new ResultData();
		if(foodCategory!=null){
			if(foodCategory.getId()!=null && foodCategory.getId()!=0){
				FoodCategory foodCategory_o = foodCategoryService.get(foodCategory.getId());
				foodCategory_o.setName(foodCategory.getName());
				foodCategory_o.setParentId(foodCategory.getParentId()==null?0:foodCategory.getParentId());
				foodCategoryService.update(foodCategory_o);
			}else{
				foodCategory.setParentId(foodCategory.getParentId()==null?0:foodCategory.getParentId());
				foodCategory.setStatus(0);
				foodCategoryService.save(foodCategory);
			}
			rd.setStatus(1);
		}
		return rd;
	}
	
	@RequestMapping("/changeStatus.do")
	@ResponseBody
	public ResultData changeStatus(Integer id){
		ResultData rd = new ResultData();
		try {
			if(id!=null){
				FoodCategory foodCategory = foodCategoryService.get(id);
				foodCategory.setStatus(foodCategory.getStatus()==0?1:0);
				foodCategoryService.update(foodCategory);
				rd.setStatus(1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}
	
	@RequestMapping("/getFoodCategoryTree.do")
	@ResponseBody
	public ResultData getFoodCategoryTree(Integer id){
		ResultData rd = new ResultData();
		try {
			List<TreeNode> list_tree = new ArrayList<TreeNode>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", 0);
			List<FoodCategory> list_cat = foodCategoryService.list(map);
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
			rd.setStatus(1);
			rd.setResult(list_tree);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}
	
}
