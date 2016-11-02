package com.sh.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sh.entity.FoodCategory;
import com.sh.entity.FoodInfo;
import com.sh.entity.Order;
import com.sh.entity.OrderDetail;
import com.sh.service.FoodInfoService;
import com.sh.service.OrderDetailService;
import com.sh.service.OrderService;
import com.sh.util.FileUtil;
import com.sh.util.Global;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;
import com.sh.vo.FoodCategoryVo;
import com.sh.vo.OrderDetailVo;
import com.sh.vo.OrderVo;

@Controller
@RequestMapping(value="/admin/order")
public class OrderController {
	private Logger log = Logger.getLogger(OrderController.class);
	
	private String cacheFilePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images/cache");
	
	@Resource
	private OrderService orderService;
	@Resource
	private OrderDetailService orderdetailService;
	@Resource
	private FoodInfoService foodinfoService;
	
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey){
		ModelAndView model = new ModelAndView("/admin/order/order-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		int count = orderService.count(map);
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
		List<Order> list = orderService.list(map, start, Pager.PAGE_SIZE);
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id){
		ModelAndView model = new ModelAndView("/admin/order/order-edit");
		OrderVo orVo=null;
		try {
			if(id!=null && id!=0){
				Order order = orderService.get(id);
				orVo = new OrderVo();
				BeanUtils.copyProperties(order, orVo);
			}
			
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", 0);
			List<OrderDetail> list_fc = orderdetailService.list(map);

			if(list_fc!=null && list_fc.size()>0){
				List<OrderDetailVo> list_vo = new ArrayList<OrderDetailVo>();
				OrderDetailVo odVo= null;
				Double totalFoodAmount=0.0;
				Double totalOrderAmount =0.0;
				
				for(OrderDetail od : list_fc){
						odVo = new OrderDetailVo();
						BeanUtils.copyProperties(od, odVo);
						FoodInfo foodinfo =  foodinfoService.get(od.getFoodInfoId());
						odVo.setFoodInfoName(foodinfo.getName());
						Double totalAmount =0.0;
						totalAmount = odVo.getFoodNum() * odVo.getFoodPrice() -odVo.getDiscountAmount();
						totalFoodAmount+= totalAmount;
						totalOrderAmount+=totalAmount;
						odVo.setTotalAmount(totalAmount);
						odVo.setTotalFoodAmount(totalFoodAmount);
						odVo.setTotalOrderAmount(totalOrderAmount);
						list_vo.add(odVo);
			    	}
				if(orVo.getFreight()!=null){
				     totalOrderAmount-=orVo.getFreight();
			     }
				orVo.setTotalFoodAmount(totalFoodAmount);
				orVo.setTotalOrderAmount(totalOrderAmount);
				
				model.addObject("orderdetail", list_vo);
				model.addObject("order", orVo);
				
				}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(Order order, HttpServletRequest request){
		ResultData rd = new ResultData();
		String orderdate = request.getParameter("orderdate");
		String paymentdate = request.getParameter("paymentdate");
		String deliverydate = request.getParameter("deliverydate");
		String receivingdate = request.getParameter("receivingdate");
		String formdate ="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formdate);  
		try {
			if(order!=null){
				if(order.getId()!=null && order.getId()!=0){
					Order order_o = orderService.get(order.getId());
				    
				    	order_o.setOrderNo(order.getOrderNo());
				    	order_o.setPurchaser(order.getPurchaser());
				    	order_o.setMobile(order.getMobile());
				    	order_o.setRecAddress(order.getRecAddress());
				    	order_o.setPaymentType(order.getPaymentType());
				    	order_o.setOrderDate(dateFormat.parse(orderdate));
				    	order_o.setPayMentDate(dateFormat.parse(paymentdate));
				    	order_o.setDeliveryDate(dateFormat.parse(deliverydate));
				    	order_o.setReceivingDate(dateFormat.parse(receivingdate));
						
					
					orderService.update(order_o);
				}else{
			    	order.setOrderDate(new Date());
//			    	order.setPayMentDate(dateFormat.parse(paymentdate));
//			    	order.setDeliveryDate(dateFormat.parse(deliverydate));
//			    	order.setReceivingDate(dateFormat.parse(receivingdate));

					orderService.save(order);
				}
				rd.setStatus(1);
			}
		} catch (ParseException e) {
			log.error(e.getMessage(),e);
			rd.setMessage(e.getMessage());						
		}
		return rd;
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public ResultData delete(Integer id){
		ResultData rd = new ResultData();
		try {
			if(id!=null){
				orderService.delete(id);
				rd.setStatus(1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}

	
	@RequestMapping("/deldetail.do")
	@ResponseBody
	public ResultData delDetail(Integer id){
		ResultData rd = new ResultData();
		try {
			if(id!=null){
				orderdetailService.delete(id);
				rd.setStatus(1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}

	
	@RequestMapping("/changeStatus.do")
	@ResponseBody
	public ResultData changeStatus(Integer id){
		ResultData rd = new ResultData();
		try {
			if(id!=null){
				Order order = orderService.get(id);
				order.setOrderStatus(order.getOrderStatus()==0?1:0);
				orderService.update(order);
				rd.setStatus(1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}

	
	@RequestMapping("/checkOrderList.do")
	@ResponseBody
	public ResultData checkOrderList(String id) {
		ResultData rd = new ResultData();
		try{
		if(id!=null){
		   Order od= orderService.get(Integer.parseInt(id));
		   // 待评价和完成状态无法修改
		   if ("3".equals(od.getOrderStatus().toString()) || "4".equals(od.getOrderStatus().toString())) {
		      rd.setMessage("当前状态下无法修改");
		      rd.setStatus(1);
		   }
		}
		}catch(Exception e){
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		
		return rd;
	}

//	public ResultData saveOrderDetail(Integer orderId,Integer foodInfoId,Double foodPrice,Double foodNum,Double discountAmount) {
	@RequestMapping("/saveOrderDetail.do")
	@ResponseBody
	public ResultData saveOrderDetail(OrderDetail orderdetail, HttpServletRequest request) {
		ResultData rd = new ResultData();
		try{
		if(orderdetail!=null){
			if(orderdetail.getId()!=null && orderdetail.getId()!=0){
				OrderDetail od_o = orderdetailService.get(orderdetail.getId());
			    od_o.setOrderId(orderdetail.getOrderId());
				od_o.setFoodInfoId(orderdetail.getFoodInfoId());
				od_o.setFoodNum(orderdetail.getFoodNum());
				od_o.setFoodPrice(orderdetail.getFoodPrice());
				od_o.setDiscountAmount(orderdetail.getDiscountAmount());
                orderdetailService.update(od_o);				
			}else{
                orderdetailService.save(orderdetail); 				
				
			}
    	    rd.setMessage("保存成功！");
		    rd.setStatus(1);
		}  
		}catch(Exception e ){
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}

	@RequestMapping("/toseleclist.do")
	public ModelAndView toseleclist(Integer pageId, String searchKey, String post){
		ModelAndView model = new ModelAndView("/admin/order/selec-foodinfo-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		int count = orderService.count(map);
		model.addObject("count", count);
		model.addObject("page", new Pager(count));
		model.addObject("searchKey", searchKey);
		model.addObject("post", post);
		return model;
	}

	@RequestMapping("/selefoodinfo.do")
	@ResponseBody
	public ResultData selefoodinfo(Integer id){
		ResultData rd = new ResultData();
		try {
			if(id!=null){
				FoodInfo fi = foodinfoService.get(id);
				rd.setResult(fi);
				rd.setStatus(1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}

	
	
	@RequestMapping("/uploadPic.do")
	@ResponseBody
	public ResultData uploadPic(HttpServletRequest request, String fileId) {
		ResultData rd = new ResultData();
		System.out.println("fileId:"+fileId);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile(fileId);
		try {
			if(file!=null && !file.isEmpty()){
				String fileName = file.getOriginalFilename();
				fileName = FileUtil.getNewFile(fileName);
				// 上传文件
				System.out.println("文件长度: " + file.getSize()); 
				System.out.println("文件类型: " + file.getContentType()); 
				System.out.println("文件名称: " + file.getName()); 
				System.out.println("文件原名: " + file.getOriginalFilename()); 
				String fileDir = Global.getFileSaveDir();
				System.out.println("fileDir:"+fileDir);
				/* 构建文件目录 */
				File fd = new File(fileDir);
				if (!fd.exists()) {
					fd.mkdirs();
				}
				// 获取图片的文件名
				String newFileName = FileUtil.getNewFile(fileName);
				FileUtil.saveFileFromInputStream(file.getInputStream(), fileDir, newFileName);
				String cachePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images/cache");
				FileUtil.createDir(cachePath);
				FileUtil.saveFileFromInputStreamToCache(file.getInputStream(), cachePath, newFileName);
				rd.setStatus(1);
				rd.setResult(newFileName);
			}else{
				rd.setMessage("接收的文件为空");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}
	
	@RequestMapping("/deleteFile.do")
	@ResponseBody
	public ResultData deleteFile(String fileName){
		ResultData rd = new ResultData();
		try {
			if(!StringUtils.isEmpty(fileName)){
				boolean flag = false;
				//本地目录
				String fileDir = Global.getFileSaveDir();
				flag = FileUtil.deleteFile(fileDir+File.separator+fileName);
				if(flag){
					rd.setStatus(1);
				}else{
					rd.setMessage("删除本地图片失败");
				}
				//tomcat缓存目录
				String cachePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images/cache");
				flag = FileUtil.deleteFile(cachePath+File.separator+fileName);
				if(flag){
					rd.setStatus(1);
				}else{
					rd.setMessage("删除缓存图片失败");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}
	
}
