package com.sh.util;

/**
 * 密码工具类
 * @author hexu
 *
 */
public class PasswordUtil {

	/**
	 * 加密密码
	 * 
	 * @param password
	 * @return
	 */
	public static String encryt(String password) {
		return EncryptUtil.md5(password);
	}

}
