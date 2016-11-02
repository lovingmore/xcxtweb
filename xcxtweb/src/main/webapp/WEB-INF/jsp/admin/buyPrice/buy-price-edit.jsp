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
<title>采购价格信息</title>
</head>
<body>
	<div class="page-container">
		<form action="/admin/buyPrice/save.do" class="form form-horizontal"
			method="post" id="form">
			<input type="hidden" id="id" name="id" value="${id }" />
			<div class="row cl" style="padding-bottom: 5px;">
				<label class="form-label col-xs-3 col-sm-3">采购日期：</label>
				<div class="formControls col-xs-4 col-sm-4">
					<input class="input-text" id="buyDate" readonly="readonly" onClick="WdatePicker()" value="${buyDate }" name="buyDate" datatype="*" placeholder="请输入采购日期" />
				</div>
				<div class="fr">
				<input class="btn btn-primary radius" type="button" onclick="addPrice()" value="新增" />
				</div>
			</div>
			<div>
			<table class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th class="col2">菜品分类</th>
						<th class="col2">菜品名称</th>
						<th class="col2">采购单价(元/斤)</th>
						<th class="col2">操作</th>
					</tr>
				</thead>
				<tbody id="dataTable">
				<c:forEach items="${list }" var="buyPrice">
					<tr id="_id${buyPrice.id }">
						<td>
						<div class='left'>
						<ul >
							<li class='title'><input class='input-text' id='foodCategoryName_id${buyPrice.id }' value="${buyPrice.foodCategoryName }" type='text' readonly onclick='showMenu("_id${buyPrice.id }"); return false;' />
							<input type='hidden' id='foodCategory_id${buyPrice.id }' name='foodCategory' value="${buyPrice.foodCategory }" />
							</li>
						</ul>
						</div>
						</td>
						<td>
						<select datatype='*' id='foodId_id${buyPrice.id }' t_value="${buyPrice.foodId }" name='foodId' class='input-text'>
						<option value=''>请选择</option>
						</select>
						</td>
						<td>
						<input type='number' class='input-text' datatype='*' placeholder='请输入采购单价' value="${buyPrice.price }" id='price_id${buyPrice.id }' name='price' style='width:150px;' />
						</td>
						<td><a href='#' onclick='deleteTr("_id${buyPrice.id }")'>删除</a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			<div class="row cl">
				<div class="col-xs-6 col-sm-6  col-xs-offset-4 col-sm-offset-4">
					<input class="btn btn-primary radius" type="submit" value="提交" />
				</div>
			</div>
		</form>
	</div>
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
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
	<script type="text/javascript" src="/lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/lib/zTree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript">
	$("input[id^='foodCategory_id']").each(function(i){
		var val = $(this).attr("value");
		if(val){
			var idVal = $(this).attr("id");
			var idPost = idVal.substring(idVal.indexOf("_id"));
			getFoodByCategory(idPost,$("#foodId"+idPost).attr("t_value"));
		}
		
	});
	
	var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			//beforeClick: beforeClick,
			onClick: onClick
		}
	};

	var zNodes =${treeNode};
	var currId = "";

	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		if (!check) alert("只能选择城市...");
		return check;
	}
	
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		tid="",v = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			tid += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (tid.length > 0 ) tid = tid.substring(0, tid.length-1);
		var cityObj = $("#foodCategoryName"+currId);
		cityObj.attr("value", v);
		$("#foodCategory"+currId).val(tid);
		hideMenu();
		getFoodByCategory(currId);
	}

	function showMenu(id) {
		var cityObj = $("#foodCategoryName"+id);
		currId = id;
		var cityOffset = $("#foodCategoryName"+id).offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	
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
	var id = '${list}';
	if(!id){
		addPrice();
	}
});

function save(){
	$.ajax({
		"url" : "/admin/buyPrice/save.do",
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

var tableIndex = 0;

function addPrice(){
	var trHtml = "";
	trHtml += "<tr id='"+tableIndex+"''>";
	trHtml += "<td><div class='left'><ul ><li class='title'><input class='input-text' id='foodCategoryName"+tableIndex+"' type='text' readonly onclick='showMenu(\""+tableIndex+"\"); return false;' /><input type='hidden' id='foodCategory"+tableIndex+"' name='foodCategory' /></li></ul></div></td>";
	trHtml += "<td><select datatype='*' id='foodId"+tableIndex+"' name='foodId' class='input-text'><option value=''>请选择</option></select></td>";
	trHtml += "<td><input type='number' class='input-text' datatype='*' placeholder='请输入采购单价' id='price"+tableIndex+"' name='price' style='width:150px;' /></td>";
	trHtml += "<td><a href='#' onclick='deleteTr(\""+tableIndex+"\")'>删除</a></td>";
	trHtml += "</tr>";
	$("#dataTable").append(trHtml);
	tableIndex++;
}

function deleteTr(trId){
	if(!trId){
		return;
	}
	$("#"+trId).remove();
}

function getFoodByCategory(currId,foodId){
	$.ajax({
		"url" : "/admin/buyPrice/getFoodByCategory.do",
		"data" : {
			"categoryId":$("#foodCategory"+currId).val()
		},
		"type" : "POST",
		"dataType" : "json",
		"async" : false,
		"success" : function(data) {
			if(data && data.status==1){
				$("#foodId"+currId).empty();
				$("#foodId"+currId).append("<option value=''>请选择</option>");
				for(var i = 0; i<data.result.length; i++){
					var name = data.result[i].name == null ? "" : data.result[i].name;
					var id = data.result[i].id == null ? "" : data.result[i].id;
					var selectedStr = "";
					if(foodId && foodId==id){
						selectedStr = "selected";
					}
					$("#foodId"+currId).append("<option value='"+id+"' "+selectedStr+">"+name+"</option>");
				} 
			}else{
				$("#foodId"+currId).empty();
				$("#foodId"+currId).append("<option value=''>请选择</option>");
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