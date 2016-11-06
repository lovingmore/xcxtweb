package com.sh.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sh.util.ExcelUtil;
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
	
	@RequestMapping("/exportExcel")
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, 
			String searchKey, String startDate, String endDate, 
			String foodCategoryName, Integer foodCategoryId, String purchaser){
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
		List<PurchaseOrderVo> projects = orderService.findPurchaseOrderByMap(map);
		
		String fileName="销售统计报表";
		//定义列名
		String[] columnNames=new String[]{"菜品","采购重量(斤)","采购金额(元)","销售金额(元)","盈利"};
		//生成每一行的数据，该数据为Excel数据结构Map集合的key值  ↑↓对应
		String[] keys = new String[]{"foodInfoname","totalWeight","foodPurchasePrice","totalfoodamount","profit"};
        //转化为Excel的数据结构
        List<Map<String,Object>> list=createExcelRecord(projects);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
        	//5.调用生成Excel方法
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        ServletOutputStream out = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
        	response.reset();
        	response.setContentType("application/vnd.ms-excel;charset=utf-8");
        	response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        	out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            log.error(e.getMessage(), e);
        } finally {
			try {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
        }
        return null;
	}
	
	private List<Map<String, Object>> createExcelRecord( List<PurchaseOrderVo> orderVos) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        PurchaseOrderVo orderVo=null;
        for (int j = 0; j < orderVos.size(); j++) {
        	orderVo=orderVos.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            Double totalWeight = 0d;
            String regex = "\\[\\d+]|[\\d+\\.\\d+]";
    		Pattern p = Pattern.compile(regex);
    		Matcher m = p.matcher(orderVo.getNorms());
    		String resultStr = "";
    		while (m.find()) {
    			if (!"".equals(m.group())){
    				resultStr += m.group();
    			}
    		}
    		if(StringUtils.isEmpty(resultStr)){
    			totalWeight = orderVo.getFoodnum();
    		}else{
    			totalWeight = orderVo.getFoodnum()*Double.valueOf(resultStr);
    		}
    		
    		mapValue.put("foodInfoname", orderVo.getFoodInfoname());
    		mapValue.put("totalWeight", totalWeight);
    		mapValue.put("foodPurchasePrice", orderVo.getFoodPurchasePrice());
    		mapValue.put("totalfoodamount", orderVo.getTotalfoodamount());
    		mapValue.put("profit", orderVo.getTotalfoodamount()-orderVo.getFoodPurchasePrice()-orderVo.getDiscountamount());
            listmap.add(mapValue);
        }
        return listmap;
	}
	
}
