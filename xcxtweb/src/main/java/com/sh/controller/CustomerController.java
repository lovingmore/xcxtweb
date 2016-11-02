package com.sh.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sh.entity.Customer;
import com.sh.service.CustomerService;
import com.sh.util.FileUtil;
import com.sh.util.Global;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;

@Controller
@RequestMapping(value="/admin/customer")
public class CustomerController {
	private Logger log = Logger.getLogger(CustomerController.class);
	
	private String cacheFilePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images/cache");
	
	@Resource
	private CustomerService customerService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey){
		ModelAndView model = new ModelAndView("/admin/customer/cust-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		int count = customerService.count(map);
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
		List<Customer> list = customerService.list(map, start, Pager.PAGE_SIZE);
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id){
		ModelAndView model = new ModelAndView("/admin/customer/cust-edit");
		try {
			if(id!=null && id!=0){
				Customer customer = customerService.get(id);
				model.addObject("customer", customer);
				if(!StringUtils.isEmpty(customer.getShopBrand())){
					String sourceFile = Global.getFileSaveDir()+File.separator+customer.getShopBrand();
					String targetFile = cacheFilePath+File.separator+customer.getShopBrand();
					FileUtil.resetCacheFile(sourceFile, targetFile);
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", 0);
//			List<FoodCategory> list_fc = foodCategoryService.list(map);
//			model.addObject("categorys", list_fc);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(Customer customer, HttpServletRequest request){
		ResultData rd = new ResultData();
			if(customer!=null){
				if(customer.getId()!=null && customer.getId()!=0){
					Customer customer_o = customerService.get(customer.getId());
					customer_o.setOrderAmount(customer_o.getOrderAmount());
					customer_o.setOrderNum(customer_o.getOrderNum());
					customer_o.setShopAddr(customer.getShopAddr());
					customer_o.setShopBrand(customer.getShopBrand());
					customer_o.setShopName(customer.getShopName());
					customer_o.setStatus(customer.getStatus());
					customer_o.setUserAccount(customer.getUserAccount());
					customer_o.setName(customer.getName());
					customerService.update(customer_o);
				}else{
					customer.setOrderAmount(0.0);
					customer.setOrderNum(0);
					customer.setStatus(0);
					customerService.save(customer);
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
				Customer customer = customerService.get(id);
				customer.setStatus(customer.getStatus()==0?1:0);
				customerService.update(customer);
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
