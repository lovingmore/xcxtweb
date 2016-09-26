var IdCard = {
	// 检查号码是否符合规范，包括长度，类型
	"check" : function(card) {
		// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
		var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
		if (reg.test(card) === false) {
			return false;
		}
		return true;
	},
	"getSex" : function(idCard) {
		if (idCard.length == 15) {
			if (idCard.substring(14, 15) % 2 == 0) {
				return '女';
			} else {
				return '男';
			}
		} else if (idCard.length == 18) {
			if (idCard.substring(14, 17) % 2 == 0) {
				return '女';
			} else {
				return '男';
			}
		} else {
			return "男";
		}
	},
	"getAge" : function(idCard) {
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();

		var age = myDate.getFullYear() - idCard.substring(6, 10) - 1;
		if (idCard.substring(10, 12) < month
				|| idCard.substring(10, 12) == month
				&& idCard.substring(12, 14) <= day) {
			age++;
		}
		return age;
	}
};