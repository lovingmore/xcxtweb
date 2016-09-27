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
<link rel="stylesheet" type="text/css" href="/lib/zTree/css/demo.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/zTree/css/zTreeStyle/zTreeStyle.css" />
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
		<form action="/admin/role/saveAllotMenu.do"
			class="form form-horizontal" method="post" id="form">
			<input type="hidden" id="id" name="id" value="${id }" />
			<div class="row cl">
				<label class="form-label col-xs-2 col-sm-2">选择菜单：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-8  col-xs-offset-2 col-sm-offset-2">
					<input class="btn btn-primary radius" type="button" value="保存"
						onclick="save()" />
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
	<script type="text/javascript" src="/lib/zTree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript"
		src="/lib/zTree/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript">
var setting = {
	view: {
		selectedMulti: false
	},
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		beforeCheck: beforeCheck
	}
};

var code, log, className = "dark";
function beforeCheck(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	return (treeNode.doCheck !== false);
}

function checkNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	type = e.data.type,
	nodes = zTree.getSelectedNodes();
	if (type.indexOf("All")<0 && nodes.length == 0) {
		alert("请先选择一个节点");
	}

	if (type == "checkAllTrue") {
		zTree.checkAllNodes(true);
	} else if (type == "checkAllFalse") {
		zTree.checkAllNodes(false);
	} else {
		var callbackFlag = $("#callbackTrigger").attr("checked");
		for (var i=0, l=nodes.length; i<l; i++) {
			if (type == "checkTrue") {
				zTree.checkNode(nodes[i], true, false, callbackFlag);
			} else if (type == "checkFalse") {
				zTree.checkNode(nodes[i], false, false, callbackFlag);
			} else if (type == "toggle") {
				zTree.checkNode(nodes[i], null, false, callbackFlag);
			}else if (type == "checkTruePS") {
				zTree.checkNode(nodes[i], true, true, callbackFlag);
			} else if (type == "checkFalsePS") {
				zTree.checkNode(nodes[i], false, true, callbackFlag);
			} else if (type == "togglePS") {
				zTree.checkNode(nodes[i], null, true, callbackFlag);
			}
		}
	}
}

function setAutoTrigger(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.setting.check.autoCheckTrigger = $("#autoCallbackTrigger").attr("checked");
	$("#autoCheckTriggerValue").html(zTree.setting.check.autoCheckTrigger ? "true" : "false");
}

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, ${treeNode});
	$("#checkTrue").bind("click", {type:"checkTrue"}, checkNode);
	$("#checkFalse").bind("click", {type:"checkFalse"}, checkNode);
	$("#toggle").bind("click", {type:"toggle"}, checkNode);
	$("#checkTruePS").bind("click", {type:"checkTruePS"}, checkNode);
	$("#checkFalsePS").bind("click", {type:"checkFalsePS"}, checkNode);
	$("#togglePS").bind("click", {type:"togglePS"}, checkNode);
	$("#checkAllTrue").bind("click", {type:"checkAllTrue"}, checkNode);
	$("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNode);
	$("#autoCallbackTrigger").bind("change", {}, setAutoTrigger);
});



function save(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getCheckedNodes(true);
	if (!nodes || nodes.length == 0) {
		parent.layer.alert("请先选择一个菜单");
	}
	var ids=new Array();
	for(var i=0;i<nodes.length;i++){
		console.log(nodes[i].id);
		ids.push(nodes[i].id);
	}
	$.ajax({
		"url" : "/admin/role/saveAllotMenu.do",
		"data" : {
			"ids":ids,
			"id":$("#id").val()
		},
		traditional: true,
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

</script>
</body>
</html>