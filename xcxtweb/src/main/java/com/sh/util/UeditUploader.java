package com.sh.util;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * UEditor文件上传辅助工具类
 * 
 */
public class UeditUploader {

	// 输出文件地址
	private String url = "";
	// 上传文件名
	private String fileName = "";
	// 状态
	private String state = "";
	// 文件类型
	private String type = "";
	// 原始文件名
	private String originalName = "";
	// 文件大小
	private String size = "";

	private HttpServletRequest request = null;
	private String title = "";

	// 文件允许格式
	private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png",
			".jpg", ".jpeg", ".bmp" };
	// 文件大小限制，单位KB
	private int maxSize = 10000;

	private HashMap<String, String> errorInfo = new HashMap<String, String>();

	public UeditUploader(HttpServletRequest request) {
		this.request = request;
		HashMap<String, String> tmp = this.errorInfo;
		tmp.put("SUCCESS", "SUCCESS"); // 默认成功
		tmp.put("NOFILE", "未包含文件上传域");
		tmp.put("TYPE", "不允许的文件格式");
		tmp.put("SIZE", "文件大小超出限制");
		tmp.put("ENTYPE", "请求类型ENTYPE错误");
		tmp.put("REQUEST", "上传请求异常");
		tmp.put("IO", "IO异常");
		tmp.put("DIR", "目录创建失败");
		tmp.put("UNKNOWN", "未知错误");
	}

	public void uploadFile() throws Exception {
		uploadMultiFile();
	}

	private void uploadMultiFile() throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
		if (!isMultipart) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}
		CommonsMultipartFile file = (CommonsMultipartFile) ((MultipartHttpServletRequest) request).getFile("upfile");
		if (file == null) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}
		this.originalName = file.getOriginalFilename();
		if (StringUtils.isEmpty(this.originalName)) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}
		if (!this.checkFileType(this.originalName)) {
			this.state = this.errorInfo.get("TYPE");
			return;
		}
		if (file.getSize() > maxSize * 1024) {
			this.state = this.errorInfo.get("SIZE");
			return;
		}
		this.fileName = this.getName(this.originalName);
		this.type = this.getFileExt(this.fileName);

		String fileName = file.getOriginalFilename();
		String docName = "";
		if (fileName.lastIndexOf(".") != -1) { // avc.dddd.dddd
			docName = fileName.substring(fileName.lastIndexOf("."));// a.png;
		}
		if (StringUtils.isNotEmpty(docName)) {
			fileName = this.getName(fileName);
		}

		// 上传文件
		FileUtil.saveFileFromInputStream(file.getInputStream(), Global.getFileSaveDirUeditor(), fileName);
		// 返回的URL
		String cachePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(Global.getFileCacheDirUeditor());
		String sourceFile = Global.getFileSaveDirUeditor()+File.separator+fileName;
		String targetFile = cachePath+File.separator+fileName;
		FileUtil.resetCacheFile(sourceFile, targetFile);
		this.url = "/admin/ueditor/getFileUrl.do?type=cacheUeditor&filename=" + fileName;
		this.title = request.getParameter("pictitle");
		this.state = this.errorInfo.get("SUCCESS");
	}

	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		for (String allowFile : allowFiles) {
			if (fileName.toLowerCase().endsWith(allowFile)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展�?
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 依据原始文件名生成新文件名
	 * 
	 * @return
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return this.fileName = "" + random.nextInt(10000) + System.currentTimeMillis() + this.getFileExt(fileName);
	}

	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

	public void setMaxSize(int size) {
		this.maxSize = size;
	}

	public String getSize() {
		return this.size;
	}

	public String getUrl() {
		return this.url;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getState() {
		return this.state;
	}

	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public String getOriginalName() {
		return this.originalName;
	}

}
