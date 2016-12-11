<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
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
<title>客户信息</title>
</head>
<body>
<div class="page-container" >
	<form action="/admin/customer/save.do" class="form form-horizontal" method="post" id="form" >
	<input type="hidden" id="id" name="id" value="${customer.id }" />
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">店标：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input type="hidden" id="shopBrand" name="shopBrand" value="${customer.shopBrand }" />
		<i class="Hui-iconfont Hui-iconfont-tuku" style="font-size:20px;margin-left: 15px;color: #76c325"></i>
		<a style="font-size:14px;color:blue" href="javascript:selectFiles()">上传图片</a>
		<div id="pictureDiv" >
			<c:if test="${!empty customer.shopBrand }">
			<img width="100px" height="100px" src="/images/cache/${customer.shopBrand }">
			<a href="javascript:deleteFile('${customer.shopBrand}')">删除图片</a>
			</c:if>
		</div>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">账号：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="userAccount" name="userAccount" value="${customer.userAccount }" placeholder="请输入手机号" class="input-text" type="text" />
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">门店名称：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="shopName" name="shopName" value="${customer.shopName }" placeholder="请输入门店" class="input-text" type="text" />
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">负责人：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="name" name="name" value="${customer.name }" placeholder="请输入负责人" class="input-text" type="text" />
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">门店地址：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="shopAddr" name="shopAddr" value="${customer.shopAddr }" placeholder="请输入门店地址" class="input-text" type="text" />
		</div>
	</div>
	
	<input type="hidden" id="orderNum" name="orderNum" value="${customer.orderNum }" />
	<input type="hidden" id="orderAmount" name="orderAmount" value="${customer.orderAmount }" />
	<input type="hidden" id="status" name="status" value="${customer.status }" />
		
	<input type="file" style="display:none" class="input-file" id="picture_file" name="picture_file" onchange="uploadPicture()" />
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
<script type="text/javascript" src="/lib/ajaxfileupload.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="/lib/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/lib/ueditor/ueditor.all.js"></script>
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
	
	var ue = UE.getEditor('container');
});

function save(){
	var ue = UE.getContent();
	//对编辑器的操作最好在编辑器ready之后再做
	ue.ready(function() {
	    //设置编辑器的内容
	    //ue.setContent('hello');
	    //获取html内容，返回: <p>hello</p>
	    var html = ue.getContent();
	    //获取纯文本内容，返回: hello
	    //var txt = ue.getContentTxt();
	});
	$.ajax({
		"url" : "/admin/customer/save.do",
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
		url : '/admin/customer/uploadPic.do',
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
				var html = '<img width="100px" height="100px" src="/images/cache/'+fileName+'">' +
						'<a href="javascript:deleteFile(\''+fileName+'\')">删除图片</a>';
				div.html(html);
				$("#shopBrand").val(fileName);
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
		"url" : "/admin/customer/deleteFile.do",
		"data" : {
			"fileName":fileName
		},
		"type" : "POST",
		"dataType" : "json",
		"async" : false,
		"success" : function(data) {
			if(data && data.status==1){
				parent.layer.msg("删除成功");
				$("#pictureDiv").html("");
				$("#facePic").val("");
			}else{
				parent.layer.alert("删除失败");
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