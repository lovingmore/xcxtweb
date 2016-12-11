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

import com.sh.entity.FoodCategory;
import com.sh.entity.FoodInfo;
import com.sh.service.FoodCategoryService;
import com.sh.service.FoodInfoService;
import com.sh.util.DateUtil;
import com.sh.util.FileUtil;
import com.sh.util.Global;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;

@Controller
@RequestMapping(value="/admin/foodInfo")
public class FoodInfoController {
	private Logger log = Logger.getLogger(FoodInfoController.class);
	
	
	@Resource
	private FoodInfoService foodInfoService;
	@Resource
	private FoodCategoryService foodCategoryService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey){
		ModelAndView model = new ModelAndView("/admin/foodInfo/food-info-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		int count = foodInfoService.count(map);
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
		List<FoodInfo> list = foodInfoService.list(map, start, Pager.PAGE_SIZE);
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id){
		ModelAndView model = new ModelAndView("/admin/foodInfo/food-info-edit");
		try {
			if(id!=null && id!=0){
				FoodInfo foodInfo = foodInfoService.get(id);
				model.addObject("foodInfo", foodInfo);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", 0);
			List<FoodCategory> list_fc = foodCategoryService.list(map);
			model.addObject("categorys", list_fc);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(FoodInfo foodInfo, HttpServletRequest request, String redemptionDateStr){
		ResultData rd = new ResultData();
		if(foodInfo!=null){
			if(!StringUtils.isEmpty(redemptionDateStr)){
				foodInfo.setRedemptionDate(DateUtil.strToDate(redemptionDateStr));
			}
			if(foodInfo.getId()!=null && foodInfo.getId()!=0){
				FoodInfo foodInfo_o = foodInfoService.get(foodInfo.getId());
				foodInfo_o.setName(foodInfo_o.getName());
				foodInfo_o.setNorms(foodInfo.getNorms());
				foodInfo_o.setPrice(foodInfo.getPrice());
				foodInfo_o.setRedemptionPrice(foodInfo.getRedemptionPrice());
				foodInfo_o.setUnit(foodInfo.getUnit());
				foodInfo_o.setStatus(foodInfo.getStatus());
				foodInfo_o.setCategoryId(foodInfo.getCategoryId());
				foodInfo_o.setFacePic(foodInfo.getFacePic());
				foodInfo_o.setContent(foodInfo.getContent());
				foodInfo_o.setRedemptionDate(foodInfo.getRedemptionDate());
				foodInfoService.update(foodInfo_o);
			}else{
				foodInfoService.save(foodInfo);
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
				FoodInfo foodInfo = foodInfoService.get(id);
				foodInfo.setStatus(foodInfo.getStatus()==0?1:0);
				foodInfoService.update(foodInfo);
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
				String fileDir = Global.getFileSaveDirImages();
				System.out.println("fileDir:"+fileDir);
				/* 构建文件目录 */
				File fd = new File(fileDir);
				if (!fd.exists()) {
					fd.mkdirs();
				}
				// 获取图片的文件名
				String newFileName = FileUtil.getNewFile(fileName);
				FileUtil.saveFileFromInputStream(file.getInputStream(), fileDir, newFileName);
				rd.setStatus(1);
				String fileUrl = Global.getServiceUrl()+"/file/getLocalFile.do?fileType=cacheImages&fileName="+newFileName;
				log.info("fileUrl:"+fileUrl);
				rd.setResult(fileUrl);
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
				if(fileName.indexOf("fileName=")>0){
					fileName = fileName.substring(fileName.indexOf("fileName=")+9);
				}
				String fileDir = Global.getFileSaveDirImages();
				flag = FileUtil.deleteFile(fileDir+File.separator+fileName);
				if(flag){
					rd.setStatus(1);
				}else{
					rd.setMessage("删除本地图片失败");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rd.setMessage(e.getMessage());
		}
		return rd;
	}
	
}
