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
import com.sh.entity.Coupon;
import com.sh.entity.Menu;
import com.sh.entity.Role;
import com.sh.entity.RolePerm;
import com.sh.service.CouponService;
import com.sh.service.MenuService;
import com.sh.service.RolePermService;
import com.sh.service.RoleService;
import com.sh.util.DateUtil;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;
import com.sh.vo.TreeNode;

@Controller
@RequestMapping(value="/admin/coupon")
public class CouponController {
	private Logger log = Logger.getLogger(CouponController.class);
	
	@Resource
	private CouponService couponService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey){
		ModelAndView model = new ModelAndView("/admin/coupon/coupon-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		int count = couponService.count(map);
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
		List<Coupon> list = couponService.list(map, start, Pager.PAGE_SIZE);
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id){
		ModelAndView model = new ModelAndView("/admin/coupon/coupon-edit");
		if(id!=null && id!=0){
			Coupon coupon = couponService.get(id);
			model.addObject("coupon", coupon);
		}
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(Coupon coupon, String startDateStr, String endDateStr){
		ResultData rd = new ResultData();
		Date startDate = DateUtil.strToDate(startDateStr);
		Date endDate = DateUtil.strToDate(endDateStr);
		if(coupon!=null){
			if(coupon.getId()!=null && coupon.getId()!=0){
				Coupon coupon_o = couponService.get(coupon.getId());
				coupon_o.setName(coupon.getName());
				coupon_o.setNumber(coupon.getNumber());
				coupon_o.setMoney(coupon.getMoney());
				coupon_o.setStartDate(startDate);
				coupon_o.setEndDate(endDate);
				couponService.update(coupon_o);
			}else{
				coupon.setStartDate(startDate);
				coupon.setEndDate(endDate);
				coupon.setCreateTime(new Date());
				couponService.save(coupon);
			}
			rd.setStatus(1);
		}
		return rd;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResultData delete(Integer id){
		ResultData rd = new ResultData();
		if(id!=null){
			Coupon coupon = couponService.get(id);
			if(coupon!=null){
				coupon.setDr(1);
				couponService.update(coupon);
				rd.setStatus(1);
			}
		}
		return rd;
	}
	
}
