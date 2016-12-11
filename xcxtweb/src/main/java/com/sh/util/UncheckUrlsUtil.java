package com.sh.util;

import java.util.ArrayList;
import java.util.List;

public class UncheckUrlsUtil {
	public static List<String> getUncheckUrls(){
		List<String> list = new ArrayList<String>();
		list.add("/admin/login.do");
		list.add("/admin/logout.do");
		list.add("/admin/checkLogin.do");
		list.add("/file/getLocalFile.do");
		list.add("/phone/index.do");
		list.add("/phone/login.do");
		list.add("/phone/logout.do");
		list.add("/phone/checkLogin.do");
		list.add("/phone/couponList.do");
		list.add("/phone/category/index.do");
		list.add("/phone/category/foodList.do");
		list.add("/phone/category/foodDetail.do");
		list.add("/phone/user/index.do");
		return list;
	}
}
