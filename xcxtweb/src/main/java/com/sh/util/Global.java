package com.sh.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Global {
	public final static String INIT_PWD = "123456";
	
	public final static String USERID_SESSION = "userId";
	
	private static String FILE_SAVE_DIR = "";
	
	/**
	 * 返回上传文件总目录
	 * @return
	 */
	public static String getFileSaveDir(){
		if(StringUtils.isEmpty(FILE_SAVE_DIR)){
			try {
				Properties properties = PropertiesLoaderUtils.loadAllProperties("local.properties");
				FILE_SAVE_DIR = properties.getProperty("fileSaveDir");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return FILE_SAVE_DIR;
	}
	
	/**
	 * 返回上传图片目录
	 * @return
	 */
	public static String getFileSaveDirImages(){
		return getFileSaveDir()+File.separator+"images";
	}
	
	/**
	 * 返回上传文件附件目录
	 * @return
	 */
	public static String getFileSaveDirFile(){
		return getFileSaveDir()+File.separator+"file";
	}
	
	/**
	 * 返回ueditor上传的总文件目录
	 * @return
	 */
	public static String getFileSaveDirUeditor(){
		return getFileSaveDir()+File.separator+"ueditor";
	}
	
	/**
	 * 返回服务器的缓存图片目录
	 * @return
	 */
	public static String getFileCacheDirImages(){
		return "/cache/images";
	}
	
	/**
	 * 返回服务器的缓存文件目录
	 * @return
	 */
	public static String getFileCacheDirFile(){
		return "/cache/file";
	}
	
	
	/**
	 * 返回ueditor的缓存图片目录
	 * @return
	 */
	public static String getFileCacheDirUeditor(){
		return "/cache/ueditor";
	}
	
}
