package com.sh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sh.entity.BuyPrice;
import com.sh.entity.FoodCategory;
import com.sh.entity.FoodInfo;
import com.sh.service.BuyPriceService;
import com.sh.service.FoodCategoryService;
import com.sh.service.FoodInfoService;
import com.sh.util.DateUtil;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;
import com.sh.vo.BuyPriceVo;
import com.sh.vo.TreeNode;

@Controller
@RequestMapping(value="/admin/buyPrice")
public class BuyPriceController {
	private Logger log = Logger.getLogger(BuyPriceController.class);
	
	@Resource
	private BuyPriceService buyPriceService;
	@Resource
	private FoodCategoryService foodCategoryService;
	@Resource
	private FoodInfoService foodInfoService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey, String buyDate){
		ModelAndView model = new ModelAndView("/admin/buyPrice/buy-price-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("buyDate", buyDate);
		int count = buyPriceService.count(map);
		model.addObject("count", count);
		model.addObject("page", new Pager(count));
		model.addObject("searchKey", searchKey);
		model.addObject("buyDate",buyDate);
		return model;
	}
	
	@RequestMapping("/listAjax.do")
	@ResponseBody
	public ResultData listAjax(Integer pageId, String searchKey, String buyDate){
		int start = PageUtil.getStart(pageId, Pager.PAGE_SIZE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("buyDate", buyDate);
		List<BuyPrice> list = buyPriceService.list(map, start, Pager.PAGE_SIZE);
		if(list!=null && list.size()>0){
			for(BuyPrice bp : list){
				if(bp.getFoodId()!=null){
					FoodInfo fi = foodInfoService.get(bp.getFoodId());
					bp.setFoodName(fi.getName());
				}
			}
		}
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id){
		ModelAndView model = new ModelAndView("/admin/buyPrice/buy-price-edit");
		if(id!=null && id!=0){
			BuyPrice buyPrice = buyPriceService.get(id);
			if(buyPrice!=null){
				List<BuyPrice> list_bp = buyPriceService.getByDate(DateUtil.dateToStr(buyPrice.getBuyDate()));
				List<BuyPriceVo> list_vo = new ArrayList<BuyPriceVo>();
				if(list_bp!=null && list_bp.size()>0){
					BuyPriceVo vo = null;
					for(BuyPrice bp :list_bp){
						vo = new BuyPriceVo();
						BeanUtils.copyProperties(bp, vo);
						FoodInfo fi = foodInfoService.get(bp.getFoodId());
						if(fi!=null){
							vo.setFoodName(fi.getName());
						}
						FoodCategory fc = foodCategoryService.get(bp.getFoodCategory());
						if(fc!=null){
							vo.setFoodCategoryName(fc.getName());
						}
						list_vo.add(vo);
					}
				}
				model.addObject("list", list_vo);
				model.addObject("id",id);
				model.addObject("buyDate", DateUtil.dateToStr(buyPrice.getBuyDate()));
			}
		}
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
		model.addObject("treeNode", JSON.toJSONString(list_tree));
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(String buyDate, Integer[] foodCategory, Integer[] foodId, String[] price){
		ResultData rd = new ResultData();
		buyPriceService.deleteAndSave(buyDate, foodCategory, foodId, price);
		rd.setStatus(1);
		return rd;
	}
	
	@RequestMapping("/getFoodByCategory.do")
	@ResponseBody
	public ResultData getFoodByCategory(Integer categoryId){
		ResultData rd = new ResultData();
		if(categoryId!=null){
			Map<String, Object> map = new HashMap<>();
			map.put("categoryId", categoryId);
			rd.setResult(foodInfoService.list(map));
			rd.setStatus(1);
		}
		return rd;
	}
	
}
