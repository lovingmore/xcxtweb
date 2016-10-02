package com.sh.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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

import com.sh.entity.FoodInfo;
import com.sh.entity.Pics;
import com.sh.service.PicsService;
import com.sh.util.FileUtil;
import com.sh.util.Global;
import com.sh.util.PageUtil;
import com.sh.util.Pager;
import com.sh.util.ResultData;

@Controller
@RequestMapping(value="/admin/pics")
public class PicsController {
	private Logger log = Logger.getLogger(PicsController.class);
	
	@Resource
	private PicsService picsService;
	
	@RequestMapping("/list.do")
	public ModelAndView list(Integer pageId, String searchKey){
		ModelAndView model = new ModelAndView("/admin/pics/pics-list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		int count = picsService.count(map);
		model.addObject("count", count);
		model.addObject("page", new Pager(count));
		model.addObject("searchKey", searchKey);
		return model;
	}
	
	@RequestMapping("/listAjax.do")
	@ResponseBody
	public ResultData listAjax(Integer pageId, String searchKey){
		ResultData rd = new ResultData();
		int start = PageUtil.getStart(pageId, Pager.PAGE_SIZE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		List<Pics> list = picsService.list(map, start, Pager.PAGE_SIZE);
		try {
			if(list!=null && list.size()>0){
				for(Pics pics : list){
					if(!StringUtils.isEmpty(pics.getUrl())){
						resetCacheFile(pics.getUrl());
						pics.setUrl(Global.getFileCacheDirImages()+"/"+pics.getUrl());
					}
				}
			}
			rd.setStatus(1);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rd.setMessage(e.getMessage());
		}
		rd.setResult(list);
		return rd;
	}
	
	@RequestMapping("/toEdit.do")
	public ModelAndView toEdit(Integer id){
		ModelAndView model = new ModelAndView("/admin/pics/pics-edit");
		try {
			if(id!=null && id!=0){
				Pics pics = picsService.get(id);
				resetCacheFile(pics.getUrl());
				model.addObject("pics", pics);
			}
			model.addObject("cachePath", Global.getFileCacheDirImages()+"/");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return model;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public ResultData save(Pics pics){
		ResultData rd = new ResultData();
		if(pics!=null){
			if(pics.getId()!=null && pics.getId()!=0){
				Pics pics_o = picsService.get(pics.getId());
				pics_o.setTitle(pics.getTitle());
				pics_o.setType(pics.getType());
				pics_o.setUrl(pics.getUrl());
				picsService.update(pics_o);
			}else{
				pics.setCreateTime(new Date());
				picsService.save(pics);
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
			picsService.delete(id);
			rd.setStatus(1);
		}else{
			rd.setMessage("缺少图片id参数");
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
				String cachePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(Global.getFileCacheDirImages());
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
				String fileDir = Global.getFileSaveDirImages();
				flag = FileUtil.deleteFile(fileDir+File.separator+fileName);
				if(flag){
					rd.setStatus(1);
				}else{
					rd.setMessage("删除本地图片失败");
				}
				//tomcat缓存目录
				String cachePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(Global.getFileCacheDirImages());
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
	
	private void resetCacheFile(String fileName) throws IOException{
		if(!StringUtils.isEmpty(fileName)){
			String sourceFile = Global.getFileSaveDirImages()+File.separator+fileName;
			String cacheImagePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(Global.getFileCacheDirImages());
			String targetFile = cacheImagePath+File.separator+fileName;
			FileUtil.createDir(cacheImagePath);
			FileUtil.resetCacheFile(sourceFile, targetFile);
		}
	}
	
}
