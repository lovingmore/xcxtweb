var HeadPic = {
		"upload" : function() {
			//$("#type").val("thumb");
			//var elementIds = [ "type" ]; // flag为id、name属性名
			var relId = $("#relId").val();
			var now = new Date().getTime();
			$.ajaxFileUpload({
				url : '/headPic/upload.do?relId=' + relId + "&now=" + now,
				type : 'post',
				secureuri : false, // 一般设置为false
				fileElementId : 'picFile', // 上传文件的id、name属性名
				dataType : 'text', // 返回值类型，一般设置为json、application/json
				//elementIds : elementIds, // 传递参数到服务器
				success : function(data, status) {
					if(data != ""){
						$("#headPic").attr("src", data);
						Tip.hide();
					}
				},
				error : function(data, status, e) {
					alert(e);
				}
			});
		},
		
		"init":function(){
			$("#headPic").click(function() {
				$("#picFile").trigger("click");
			});
			$("#picFile").live("change", function() {
				Tip.show();
				//$("#from1").submit();
				HeadPic.upload();
			});
		}
};
