var Ck = {
	"setCookie" : function(cookieName, cookieValue, nHours) {
		var today = new Date();
		var expire = new Date();
		if (nHours == null || nHours == 0)
			nHours = 1;
		expire.setTime(today.getTime() + 1000 * 60 * 60 * 24 * nHours);
		document.cookie = cookieName + "=" + escape(cookieValue) + ";expires="
				+ expire.toGMTString();
	},
	"getCookie" : function(cookieName) {
		var theCookie = "" + document.cookie;
		var ind = theCookie.indexOf(cookieName);
		if (ind == -1 || cookieName == "")
			return "";
		var ind1 = theCookie.indexOf(';', ind);
		if (ind1 == -1)
			ind1 = theCookie.length;
		return unescape(theCookie.substring(ind + cookieName.length + 1, ind1));
	}
};
