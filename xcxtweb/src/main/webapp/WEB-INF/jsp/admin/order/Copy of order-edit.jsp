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
<title>订单详情</title>
</head>
<body>
<div class="page-container" >
	<form action="/admin/order/save.do" class="form form-horizontal" method="post" id="form" >
	<input type="hidden" id="id" name="id" value="${order.id }" />
    <table class="table">
       <tbody>
          <tr>
             <td>
					<td class="text-r" width="30%"><span class="text-warning">*</span>订单号：</td>
					<td class="text-l"><input type="text" class="input-text " readonly="readonly" required id="order_id" name="order_id"
						value="${order.order_id}" style="width: 220px;" /></td>
					<td class="text-r" width="30%"><span class="text-warning">*</span>订单状态：</td>
					<td class="text-l" colspan="3">${order.statusname }</td>

             </td>
          
          </tr>
       
       </tbody>
    
    
    
    </table>


	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">购买人：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="purchaser" name="purchaser" value="${order.purchaser }" placeholder="请输入购买人" class="input-text" type="text" />
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">电话：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="mobile" name="mobile" value="${order.mobile }" placeholder="请输入手机" class="input-text" type="text" />
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">收货地址：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="recAddress" name="recAddress" value="${order.recAddress }" placeholder="请输入收货地址" class="input-text" type="text" />
		</div>
	</div>
	<div class="row cl">
	
		<label class="form-label col-xs-2 col-sm-2">订单状态：</label>
		<div class="formControls col-xs-8 col-sm-8">
	    <select name='orderStatus' id='orderStatus' style="width: 90px;" disabled="true" >
			<option value="0" <c:if test="${order.orderStatus == 0 }">selected</c:if>>待支付</option>
			<option value="1" <c:if test="${order.orderStatus == 1 }">selected</c:if>>待发货</option>
			<option value="2" <c:if test="${order.orderStatus == 2 }">selected</c:if>>待收货</option>
			<option value="3" <c:if test="${order.orderStatus == 3 }">selected</c:if>>已完成</option>
		</select>	
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">订单编号：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input datatype="*" maxlength="30" id="orderNo" name="orderNo" value="${order.orderNo }" placeholder="" class="input-text" type="text" />
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">订单时间：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input class="value" type="date" id="orderdate" name="orderdate" onClick="WdatePicker()" value="<fmt:formatDate value="${order.orderDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">付款时间：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input class="value" type="date" id="paymentdate" name="paymentdate" value="<fmt:formatDate value="${order.payMentDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">发货时间：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input class="value" type="date" id="deliverydate" name="deliverydate" value="<fmt:formatDate value="${order.deliveryDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-2 col-sm-2">收货时间：</label>
		<div class="formControls col-xs-8 col-sm-8">
		<input class="value" type="date" id="receivingdate" name="receivingdate" value="<fmt:formatDate value="${order.receivingDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>">
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
<script type="text/javascript" src="/lib/ajaxfileupload.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="/lib/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/lib/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="/lib/My97DatePicker/WdatePicker.js"></script>

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
		"url" : "/admin/order/save.do",
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
		url : '/admin/foodInfo/uploadPic.do',
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
				var html = '<img width="300px" height="300px" src="/images/cache/'+fileName+'">' +
						'<a href="javascript:deleteFile(\''+fileName+'\')">删除图片</a>';
				div.html(html);
				$("#facePic").val(fileName);
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

</script>
</body>
</html>