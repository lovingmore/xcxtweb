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

import com.sh.service.OrderDetailService;
import com.sh.service.OrderService;
import com.sh.util.ExcelUtil;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;
import com.sh.vo.PurchaseOrderVo;

@Controller
@RequestMapping(value="/admin/purchase")
public class PurchaseController {
	private Logger log = Logger.getLogger(PurchaseController.class);
	
	@Resource
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderService orderService;
	
	private static String PURCHASE_TYPE_DETAIL_LIST = "detailList";//采购清单
	private static String PURCHASE_TYPE_SORTING_LIST = "sortingList";//分拣清单
	private static String PURCHASE_TYPE_SHIP_ORDER = "shipOrder";//客户发货单
	
	
	@RequestMapping("/purchaseList.do")
	public ModelAndView purchaseList(Integer pageId, String searchKey, String purchaseDate, String purchaseType){
		String url = "/admin/purchase/purchase-list";
		if(PURCHASE_TYPE_DETAIL_LIST.equals(purchaseType)){
			url = "/admin/purchase/purchase-detail-list";
		}else if(PURCHASE_TYPE_SORTING_LIST.equalsIgnoreCase(purchaseType)){
			url = "/admin/purchase/purchase-sorting-list";
		}else if(PURCHASE_TYPE_SHIP_ORDER.equalsIgnoreCase(purchaseType)){
			url = "/admin/purchase/purchase-ship-order-list";
		}
		
		ModelAndView model = new ModelAndView(url);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		if(!StringUtils.isEmpty(purchaseDate)){
			map.put("purchaseDate", purchaseDate);
		}
		int count = orderService.countPurchaseOrderByDate(purchaseDate);
		model.addObject("count", count);
		model.addObject("page", new Pager(count));
		model.addObject("searchKey", searchKey);
		model.addObject("purchaseDate", purchaseDate);
		return model;
	}
	
	@RequestMapping("/purchaseListAjax.do")
	@ResponseBody
	public ResultData purchaseListAjax(Integer pageId, String searchKey, String purchaseDate){
		int start = PageUtil.getStart(pageId, Pager.PAGE_SIZE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		if(!StringUtils.isEmpty(purchaseDate)){
			map.put("purchaseDate", purchaseDate);
		}
		List<PurchaseOrderVo> list = orderService.findPurchaseOrderByDate(purchaseDate, start, Pager.PAGE_SIZE);
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/exportExcel")
	public String exportExcel(HttpServletRequest request, HttpServletResponse response, 
			String searchKey, String purchaseDate, String purchaseType){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		if(!StringUtils.isEmpty(purchaseDate)){
			map.put("purchaseDate", purchaseDate);
		}
		List<PurchaseOrderVo> projects = orderService.findPurchaseOrderByDate(purchaseDate);
		
		String fileName="随访任务统计表";
		//定义列名
		String[] columnNames=new String[]{"菜品","采购重量(斤)"};
		//生成每一行的数据，该数据为Excel数据结构Map集合的key值  ↑↓对应
		String[] keys = new String[]{"foodInfoname","totalWeight"};
		if(PURCHASE_TYPE_DETAIL_LIST.equals(purchaseType)){
			fileName = "采购清单";
			columnNames=new String[]{"菜品","采购重量(斤)"};
			keys=new String[]{"foodInfoname","totalWeight"};
		}else if(PURCHASE_TYPE_SORTING_LIST.equalsIgnoreCase(purchaseType)){
			fileName = "分拣清单";
			columnNames=new String[]{"菜品","规格","数量","合计重量(斤)"};
			keys=new String[]{"foodInfoname","norms","foodnum","totalWeight"};
		}else if(PURCHASE_TYPE_SHIP_ORDER.equalsIgnoreCase(purchaseType)){
			fileName = "客户发货清单";
			columnNames=new String[]{"客户名称","菜品","规格","数量","单价","合计重量(斤)"};
			keys=new String[]{"purchaser","foodInfoname","norms","foodnum","foodPurchasePrice","totalWeight"};
		}
        //转化为Excel的数据结构
        List<Map<String,Object>> list=createExcelRecord(projects,purchaseType);
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
	
	private List<Map<String, Object>> createExcelRecord( List<PurchaseOrderVo> orderVos, String purchaseType) {
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
    		
            if(PURCHASE_TYPE_DETAIL_LIST.equals(purchaseType)){
            	mapValue.put("foodInfoname", orderVo.getFoodInfoname());
            	mapValue.put("totalWeight", totalWeight);
    		}else if(PURCHASE_TYPE_SORTING_LIST.equalsIgnoreCase(purchaseType)){
    			mapValue.put("foodInfoname", orderVo.getFoodInfoname());
    			mapValue.put("norms", orderVo.getNorms());
    			mapValue.put("foodnum", orderVo.getFoodnum());
    			mapValue.put("totalWeight", totalWeight);
    		}else if(PURCHASE_TYPE_SHIP_ORDER.equalsIgnoreCase(purchaseType)){
    			mapValue.put("purchaser", orderVo.getPurchaser());
    			mapValue.put("foodInfoname", orderVo.getFoodInfoname());
    			mapValue.put("norms", orderVo.getNorms());
    			mapValue.put("foodnum", orderVo.getFoodnum());
    			mapValue.put("foodPurchasePrice", orderVo.getFoodPurchasePrice());
    			mapValue.put("totalWeight", totalWeight);
    		}
            listmap.add(mapValue);
        }
        return listmap;
	}
	
}
