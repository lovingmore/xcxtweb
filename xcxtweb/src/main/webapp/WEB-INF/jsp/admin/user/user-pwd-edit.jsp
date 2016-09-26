<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/lib/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/lib/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="/lib/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/lib/h-ui.admin/css/style.css" />
<style type="text/css">
.modal-backdrop {
	background-color: transparent;
}
.modal.fade.in {
	margin-top: -120px !important;
	top: 180px;
	width: 450px;
}
table.option td:first-child{
	width: 25%;
}
table.option select{
	width: 100%;
}
.fr{
	float: right;
	margin-right: 10px;
}
.fl{
	float: left;
	margin-left: 10px;
}
.dis{
	display: none;
}
.disabled{
	color: #8a8a8a;
	border-color: #8a8a8a;
}
#dataTable a{
	color: blue;
}
</style>
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>修改密码</title>
</head>
<body>
<div class="page-container" >
	<form action="/admin/user/updatePwd.do" class="form form-horizontal" method="post" id="form">
	<input type="hidden" id="id" name="id" value="${id }" />
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">原密码：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="oldPassword" name="oldPassword" placeholder="请输入原密码" class="input-text" type="password" />
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">新密码：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="newPassword" name="newPassword" placeholder="请输入新密码" class="input-text" type="password" />
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">再次输入：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="confirmPassword" name="confirmPassword" placeholder="请再次输入密码" class="input-text" type="password" />
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-8  col-xs-offset-2 col-sm-offset-2">
		<input class="btn btn-primary radius" type="submit" value="提交" />
		</div>
	</div>
	</form>
</div>
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="/lib/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="/lib/bootstrap-modal/2.2.4/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="/lib/bootstrap-modal/2.2.4/bootstrap-modal.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#form").Validform({
		tiptype:3,
        label:"Validform_checktip", 
        showAllError:true,
        //btnSubmit:"#bt_submit", 
        tipSweep:true,  
        beforeSubmit:function(){
        	save();
            return false;
        }  
    });
});

function save(){
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword").val();
	if (oldPassword == "") {
		alert("当前密码不能为空");
		return false;
	}
	if (newPassword == "") {
		alert("新密码不能为空");
		return false;
	}
	if (confirmPassword == "") {
		alert("确认密码不能为空");
		return false;
	}
	if(newPassword!=confirmPassword){
		alert("密码不一致！");
		return;
	}
	$.ajax({
		"url" : "/admin/user/updatePwd.do",
		"data" : $("form").serialize(),
		"type" : "POST",
		"dataType" : "json",
		"async" : false,
		"success" : function(data) {
			if(data && data.status==1){
				parent.layer.msg("修改成功");
				parent.location.reload();
			}else{
				if(data.message){
					parent.layer.alert(data.message);
				}else{
					parent.layer.alert("修改失败");
				}
			}
		},
		"error" : function() {
			parent.layer.alert("网络异常，请检测网络后重新操作!");
		}
	});
}

</script>
</body>
</html>