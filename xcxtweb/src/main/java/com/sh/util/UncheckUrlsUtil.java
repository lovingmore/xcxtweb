package com.sh.util;

import java.util.ArrayList;
import java.util.List;

public class UncheckUrlsUtil {
	public static List<String> getUncheckUrls(){
		List<String> list = new ArrayList<String>();
		list.add("/admin/login.do");
		list.add("/admin/logout.do");
		list.add("/admin/checkLogin.do");
		return list;
	}
}
