var Tip = {
	"show" : function(message) {		
		if(message=="" || message==undefined){
			message = "系统正在处理...";
		}
		var html = "<img src=\"/images/loading.gif\" class=\"loading\" />" + message;		
		$('.msgtips').html(html).show();
	},
	"hide" : function() {
		$('.msgtips').hide();
	},

	"init" : function() {
		var wrapW = $('.wrap').width();
		$('.msgtips').css({
			width : wrapW / 2,
			marginTop : -$('.msgtips').height() / 2,
			marginLeft : -wrapW / 4
		});
	}

};
