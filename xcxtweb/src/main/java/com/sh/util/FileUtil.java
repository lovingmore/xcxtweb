package com.sh.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;


public class FileUtil {
	private final static String path = FileUtil.class.getResource("/")
			.getPath();

	public static String getNewFile(String fileName) {
		String docName = "";
		if (fileName.lastIndexOf(".") != -1) { // avc.dddd.dddd
			docName = fileName.substring(fileName.lastIndexOf("."));
		}
		if (StringUtils.isNotEmpty(docName)) {
			fileName = "" + new Random().nextInt(10000) + System.currentTimeMillis() + docName;// xxxxdddd.png
		}
		return fileName;
	}

	/**
	 * 读取txt文件内容
	 * 
	 * @param txtFileName
	 * @return
	 */
	public static List<String> getTxt(String txtFileName) {
		List<String> list = new ArrayList<String>();
		String encoding = "utf-8";
		File file = new File(path + txtFileName);
		try {
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					list.add(lineTxt);
				}
				read.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String getRootPath() {
		String classPath = FileUtil.class.getClassLoader().getResource("/")
				.getPath();
		String rootPath = "";
		// windows下
		if ("\\".equals(File.separator)) {
			rootPath = classPath.substring(1,
					classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("/", "\\");
		}
		// linux下
		if ("/".equals(File.separator)) {
			rootPath = FileUtil.class.getProtectionDomain().getCodeSource()
					.getLocation().getPath();
		}
		return rootPath;
	}

	public static void saveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException {
		createDir(path);
		FileOutputStream fs = new FileOutputStream(path + File.separator + filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件，可以是文件或文件夹
	 * 
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 * 
	 * @param dir
	 *            要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i]
						.getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}
	
	public static void saveFileFromInputStreamToCache(InputStream stream, String path,
			String filename) throws IOException {
		createDir(path);
		FileOutputStream fs = new FileOutputStream(path + File.separator + filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
	
	public static void resetCacheFile(String sourceFile, String targetFile) throws IOException{
		if(StringUtils.isEmpty(sourceFile) || StringUtils.isEmpty(targetFile)){
			return;
		}
		File sFile = new File(sourceFile);
		if(!sFile.exists()){
			return;
		}
		File tFile = new File(targetFile);
		if(!tFile.exists()){
			Files.copy(sFile.toPath(), tFile.toPath());
		}
	}
	
	public static void main(String[] args) {

//		System.out.println(getTxt(path + "agreement.txt"));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
}
