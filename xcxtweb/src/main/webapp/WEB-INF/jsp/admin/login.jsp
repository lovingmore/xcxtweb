<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="/lib/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/lib/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="/lib/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/lib/h-ui.admin/css/style.css" />
</head>
<body style="background: url('/images/background.jpg');">
<div style="margin:0 auto;width:300px;padding-top:150px">
	<h2>用户登录</h2>
	<br>
	<div class="row cl">
		<label class="form-label col-xs-3 col-sm-2" style="padding-right: 0;line-height: 30px;">帐号:</label>
		<div class="formControls col-xs-9 col-sm-10" style="padding-left: 0">
			<input type="text" id="username" class="input-text" placeholder="帐号">
		</div>
	</div>
	<br>
	<div class="row cl">
		<label class="form-label col-xs-3 col-sm-2" style="padding-right: 0;line-height: 30px;">密码:</label>
		<div class="formControls col-xs-9 col-sm-10" style="padding-left: 0">
			<input type="password" id="password" class="input-text" placeholder="密码">
		</div>
	</div>
	<br>
	<div class="checkbox">
		<label>
			<input type="checkbox" id="save" >记住帐号
		</label>
	</div>
	<br>
	<div class="btn btn-lg btn-primary btn-block" onclick="Login.login()">登陆</div>
</div>
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="/lib/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="/lib/bootstrap-modal/2.2.4/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="/lib/bootstrap-modal/2.2.4/bootstrap-modal.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="/js/common/cookie.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//监听回车按钮
		document.onkeydown=keyDownSearch;
		function keyDownSearch(e) {    
			// 兼容FF和IE和Opera
			var theEvent = e || window.event;
			var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
			if (code == 13){
				Login.login();//具体处理函数    
				return false;
			}
			return true;
		}

		//读取保存的内容
		var username = Ck.getCookie("op_user_username");
		var password = Ck.getCookie("op_user_password");
		var save = Ck.getCookie("op_user_save");
		if(username && username!="null" && typeof(username) != "undefined"){
			$("#username").val(username);
		}
		if(password != null){
			$("#password").val(password);
		}
		if(save == 1){
			$("#save").attr("checked","checked");
		}
	});

	function toList(){
		location.href = "/admin/index.do";
	}
	var Login={
		"login":function(){
			var username = $("#username").val();
			var password = $("#password").val();
			if(!username){
				layer.alert("账号不能为空！");
				return;
			}
			if(!password){
				layer.alert("密码不能为空！");
				return;
			}
			$.ajax({
	    		url:"/admin/checkLogin.do",
				data:{
					"username":username,
					"password":password
				},
				"dataType" : "json",
				"type" : "POST",
				"async" : true,
				"success" : function(data) {
					if(data.status==1){
						//记住帐号
						Ck.setCookie("op_user_username", username, 365);
						//记住密码
						if($("#save:checked").length>0){
							Ck.setCookie("op_user_password", password, 365);
							Ck.setCookie("op_user_save", 1, 365);
						}else{
							Ck.setCookie("op_user_password", "", -1);
							Ck.setCookie("op_user_save", 0, -1);
						}
						location.href = "/admin/index.do";
					}else{
						if(data.message){
							layer.alert(data.message);
						}else{
							layer.alert("登录失败！");
						}
					}
				},
				"complete": function(){
					
				}
			});
		}
	}
</script>
</body>
</html>