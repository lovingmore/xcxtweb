package com.sh.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EncryptUtil {

	private static final Log LOGGER = LogFactory.getLog(EncryptUtil.class);

	/**
	 * md5加密</br>
	 * 
	 * @praram str 需要进行md5加密的字符
	 * @return 已进行md5的加密的字符
	 */
	public static String md5(String str) {
		String md5 = encode(str, "MD5").toLowerCase();
		return md5;
	}

	/**
	 * sha1 加密
	 * 
	 * @praram str 需要进行sha1加密的字符
	 * @return 已进行sha1的加密的字符
	 */
	public static String sha1(String str) {
		String sha1 = encode(str, "SHA-1");
		return sha1;
	}

	/**
	 * 按类型对字符串进行加密并转换成16进制输出</br>
	 * 
	 * @param str
	 *            字符串
	 * @param type
	 *            可加密类型md5, des , sha1
	 * @return 加密后的字符串
	 */
	private static String encode(String str, String type) {
		try {
			MessageDigest alga = MessageDigest.getInstance(type);
			alga.update(str.getBytes());
			byte digesta[] = alga.digest();
			String hex = byte2hex(digesta);
			return hex;
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
		}
		return "";
	}

	/**
	 * 将字节数组转换成16进制字符
	 * 
	 * @param b
	 *            需要转换的字节数组
	 * @return 转换后的16进制字符
	 */
	private static String byte2hex(byte b[]) {
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < b.length; n++) {
			String stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1) {
				sb.append("0");
			}
			sb.append(stmp);
		}

		return sb.toString().toUpperCase();
	}

	/**
	 * 将成16进制转换字符串字符</br>
	 * 
	 * @param s
	 *            需要转换的16进制字符串 return 转换后的字符
	 */
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/**
	 * 将字符串转换成16进制字符</br>
	 * 
	 * @param s
	 *            需要转换的字符串 return 转换后的16进制字符
	 */
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
		}
		return new String(baKeyword);// UTF-16le:Not
	}

	/**
	 * 将字节转换成16进制字符
	 * 
	 * @param ib
	 *            需要转换的字节
	 * @return 转换后的16进制字符
	 */
	protected static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0f];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
	
	public static String getSignature(String token, String timestamp, String nonce){
		ArrayList list = new ArrayList();  
		list.add(token);  
		list.add(timestamp);  
		list.add(nonce);  
		  /* 
		   * 运用Collections的sort（）方法对其进行排序 sort（）方法需要传 连个参数，一个是需要进行排序的Collection 另一个是一个Comparator 
		   */  
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String str = "";
		for (int i = 0; i < list.size(); i++) {  
			str = str + list.get(i);
		}  
		return SHA1(str);
	}
	
	public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
	
	public static void main(String[] args){
		System.out.println(getSignature("3171", "20151029151938", "123456"));
	}

}
