package com.sh.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Global {
	public final static String INIT_PWD = "123456";
	
	public final static String USERID_SESSION = "userId";
	/**
	 * 本地文件存储目录
	 */
	private static String FILE_SAVE_DIR = "";
	/**
	 * 服务器访问地址
	 */
	private static String SERVICE_URL = "";
	/**
	 * 新闻抓取的urls文件保存目录
	 */
	private static String SPIDER_FILE_PATH = "";
	
	/**
	 * 服务器缓存图片
	 */
	public final static String CACHE_IMAGES = "cacheImages";
	/**
	 * 服务器缓存文件
	 */
	public final static String CACHE_FILE = "cacheFile";
	/**
	 * 服务器缓存ueditor文件
	 */
	public final static String CACHE_UEDITOR = "cacheUeditor";
	/**
	 * 定时任务运行标识
	 */
	public static Boolean TASK_JOB_FLAG = true;
	/**
	 * 定时任务运行标识
	 */
	public static Boolean TASK_JOB_REDPACKET_FLAG = true;
	/**
	 * 发布接口分页条数
	 */
	public static int PAGE_SIZE = 15;
	/**
	 * 自定义短信验证码的session过期时间 3*60,单位秒
	 */
	public static final int SESSION_CHECKCODE_EXPIRE = 3*60;
	/**
	 * token过期时间,单位天
	 */
	public static final long TOKEN_EXPIRATION_TIME = 7;
	
	/**
	 * 返回上传文件总目录
	 * @return
	 */
	public static String getFileSaveDir(){
		if(StringUtils.isEmpty(FILE_SAVE_DIR)){
			try {
				Properties properties = PropertiesLoaderUtils.loadAllProperties("local.properties");
				FILE_SAVE_DIR = properties.getProperty("fileSaveDir");
				SERVICE_URL = properties.getProperty("serviceUrl");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return FILE_SAVE_DIR;
	}
	
	/**
	 * 返回上传文件总目录
	 * @return
	 */
	public static String getServiceUrl(){
		if(StringUtils.isEmpty(SERVICE_URL)){
			try {
				Properties properties = PropertiesLoaderUtils.loadAllProperties("local.properties");
				FILE_SAVE_DIR = properties.getProperty("fileSaveDir");
				SERVICE_URL = properties.getProperty("serviceUrl");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SERVICE_URL;
	}
	
	/**
	 * 返回新闻抓取的urls文件保存目录
	 * @return
	 */
	public static String getSpiderFilePath() {
		if(StringUtils.isEmpty(SPIDER_FILE_PATH)){
			try {
				Properties properties = PropertiesLoaderUtils.loadAllProperties("local.properties");
				FILE_SAVE_DIR = properties.getProperty("fileSaveDir");
				SERVICE_URL = properties.getProperty("serviceUrl");
				SPIDER_FILE_PATH = properties.getProperty("spiderFilePath");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SPIDER_FILE_PATH;
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
