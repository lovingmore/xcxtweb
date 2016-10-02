<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/lib/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="/lib/h-ui.admin/css/style.css" />
<style type="text/css">
.modal-backdrop {
	background-color: transparent;
}

.modal.fade.in {
	margin-top: -120px !important;
	top: 180px;
	width: 450px;
}

table.option td:first-child {
	width: 25%;
}

table.option select {
	width: 100%;
}

.fr {
	float: right;
	margin-right: 10px;
}

.fl {
	float: left;
	margin-left: 10px;
}

.dis {
	display: none;
}

.disabled {
	color: #8a8a8a;
	border-color: #8a8a8a;
}

#dataTable a {
	color: blue;
}
</style>
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>角色信息</title>
</head>
<body>
	<div class="page-container">
		<form action="/admin/pics/save.do" class="form form-horizontal"
			method="post" id="form">
			<input type="hidden" id="id" name="id" value="${pics.id }" />
			<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2">标题：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input datatype="*" maxlength="30" id="title" name="title"
						value="${pics.title }" placeholder="请输入标题" class="input-text"
						type="text" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2">图片类型：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<select id="type" name="type" class="input-text">
						<option value="0">首页</option>
					</select>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2">图片：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input type="hidden" id="url" name="url"
						value="${pics.url }" /> <i
						class="Hui-iconfont Hui-iconfont-tuku"
						style="font-size: 20px; margin-left: 15px; color: #76c325"></i> <a
						style="font-size: 14px; color: blue"
						href="javascript:selectFiles()">上传图片</a>
					<div id="pictureDiv">
						<c:if test="${!empty pics.url }">
							<img width="300px" height="300px"
								src="${cachePath }${pics.url}">
							<a href="javascript:deleteFile('${pics.url }')">删除图片</a>
						</c:if>
					</div>
				</div>
			</div>
			<input type="file" style="display: none" accept="image/*"
				class="input-file" id="picture_file" name="picture_file"
				onchange="uploadPicture()" />
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
	<script type="text/javascript"
		src="/lib/bootstrap-modal/2.2.4/bootstrap-modalmanager.js"></script>
	<script type="text/javascript"
		src="/lib/bootstrap-modal/2.2.4/bootstrap-modal.js"></script>
	<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript"
		src="/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="/lib/ajaxfileupload.js"></script>
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
	$.ajax({
		"url" : "/admin/pics/save.do",
		"data" : $("form").serialize(),
		"type" : "POST",
		"dataType" : "json",
		"async" : false,
		"success" : function(data) {
			if(data && data.status==1){
				parent.layer.msg("保存成功");
				parent.location.reload();
			}else{
				parent.layer.msg("保存失败");
			}
		},
		"error" : function() {
			alert("网络异常，请检测网络后重新操作!");
		}
	});
}

function selectFiles(id){
	$("#picture_file").click();
}
function uploadPicture(){
	var tip = layer.load();
	$.ajaxFileUpload({
		url : '/admin/pics/uploadPic.do',
		type : 'post',
		secureuri : false, // 一般设置为false
		fileElementId : 'picture_file', // 上传文件的id、name属性名
		dataType : 'json', // 返回值类型，一般设置为json、application/json
		data : {
			"fileId" : 'picture_file',
		},
		success : function(data, status) {
			if(data && data.status==1){
				parent.layer.alert('上传成功');
				var div = $("#pictureDiv");
				var fileName = data.result;
				var html = '<img width="300px" height="300px" src="${cachePath}'+fileName+'">' +
						'<a href="javascript:deleteFile(\''+fileName+'\')">删除图片</a>';
				div.html(html);
				$("#url").val(fileName);
			}else{
				if(data.message){
					parent.layer.alert(data.message);
				}else{
					parent.layer.alert("上传失败");
				}
			}
		},
		error : function(data, status, e) {
			parent.layer.alert(e);
		},
		complete:function(){
			layer.close(tip);
		}
	});
}

function deleteFile(fileName){
	$.ajax({
		"url" : "/admin/pics/deleteFile.do",
		"data" : {
			"fileName":fileName
		},
		"type" : "POST",
		"dataType" : "json",
		"async" : false,
		"success" : function(data) {
			if(data && data.status==1){
				parent.layer.msg("删除成功");
			}else{
				parent.layer.alert("删除失败");
			}
			$("#pictureDiv").html("");
			$("#url").val("");
		},
		"error" : function() {
			parent.layer.alert("网络异常，请检测网络后重新操作!");
		}
	});
}

</script>
</body>
</html>