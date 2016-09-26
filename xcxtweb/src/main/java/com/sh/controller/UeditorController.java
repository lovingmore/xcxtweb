package com.sh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sh.util.Global;

/**
 * 处理html编辑器的Controller
 * 
 * @author hexu
 *
 */
@Controller
@RequestMapping("/admin/ueditor")
public class UeditorController {

	private Logger log = Logger.getLogger(UeditorController.class);

	/**
	 * 服务器缓存图片
	 */
	private final static String CACHE_IMAGES = "cacheImages";
	/**
	 * 服务器缓存文件
	 */
	private final static String CACHE_FILE = "cacheFile";
	/**
	 * 服务器缓存ueditor文件
	 */
	private final static String CACHE_UEDITOR = "cacheUeditor";

	@RequestMapping("getLocalFile.do")
	public String getLocalFile(String fileName, String fileType, HttpServletResponse response) {
		if (StringUtils.isEmpty(fileType) || StringUtils.isEmpty(fileName)) {
			return null;
		}
		String localPath = "";
		File file = null;
		if (CACHE_IMAGES.equals(fileType)) {
			localPath = Global.getFileSaveDirImages();
			file = new File(localPath + File.separator + fileName);
		} else if (CACHE_FILE.equals(fileType)) {
			localPath = Global.getFileSaveDirFile();
			file = new File(localPath + File.separator + fileName);
		} else if (CACHE_UEDITOR.equals(fileType)) {
			localPath = Global.getFileSaveDirUeditor();
			file = new File(localPath + File.separator + fileName);
		}
		if(file==null || !file.exists()){
			return null;
		}
		FileInputStream fis = null;
		try {
			String contentType = Files.probeContentType(Paths.get(file.getPath()));
			log.info("contentType:"+contentType);
			response.setContentType(contentType);
			OutputStream out = response.getOutputStream();
			fis = new FileInputStream(file);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}
		return null;
	}
	
}
