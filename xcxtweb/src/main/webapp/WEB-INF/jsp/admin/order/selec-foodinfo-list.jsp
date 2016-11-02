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
.tab_query{
	font-size: 12px;
	padding:2px 10px 5px;
	cursor: pointer;
}
.tqselected{
	border-bottom: solid 2px #00CC99;
}
</style>
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>菜品列表</title>
</head>
<body>
<div class="pd-20">
	<form action="/admin/foodInfo/list.do" method="post" id="form">
	<input type="hidden" id="orderDefind" name="orderDefind" value="${orderDefind}" />
	<div class="cl pd-5 bg-1 bk-gray">
		<div id="options" class="fl">
		<%-- <span class="select-box radius" style="width:150px;background-color:#FFFFFF">
			<select class="select" name="createType" id="createType" size="1">
				<option value="-1">全部数据</option>
				<option value="0" <c:if test="${createType == 0}">selected</c:if>>在线预约</option>
				<option value="1" <c:if test="${createType == 1}">selected</c:if>>电话预约</option>
				<option value="2" <c:if test="${createType == 2}">selected</c:if>>短信预约</option>
			</select>
		</span> --%>
		<input type="text" placeholder="搜索角色名称" class="input-text ac_input" value="${searchKey}" id="searchKey" name="searchKey" autocomplete="off" style="width:200px">
		<a data-toggle="modal" href="#myModal">
			<i class="Hui-iconfont Hui-iconfont-arrow2-bottom"></i>
		</a>
		<span class="btn btn-primary radius" style="margin:0 10px;" onclick="submit()"><i class="Hui-iconfont Hui-iconfont-search2"></i> 查询</span>
		<span><a class="btn btn-primary radius fr" href="javascript:Data.selMerchandise('${post }');"><i class="Hui-iconfont Hui-iconfont-add"></i>选择</a></span>
	</div>
	</form>
	<table class="table table-border table-bordered table-bg table-hover table-sort">
	<thead>
	  <tr class="text-c">

		<th width="25">#</th>
		<th class="col2">菜品名称</th>
		<th class="col2">规格</th>
		<th class="col2">销售价(元)</th>
		<th class="col2">换购价(元)</th>
		<th class="col2">单位</th>
		<th class="col2">菜品分类</th>
		<th class="col2">状态</th>
		<th class="col2">操作</th>
	  </tr>
    </thead>
	<tbody id="dataTable">
	</tbody>
	</table>
  <div id="page" style="padding-top: 20px;margin-left: auto;margin-right: auto"></div>
  <div style="padding-top: 20px;float:left;line-height: 30px;">总计：<span style="color:red;">${count}</span>&nbsp;&nbsp;条记录</div>
  <input id="backArgs" type="hidden" value="${backArgs}">
  <input id="pageId" type="hidden">
</div>
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="/lib/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="/lib/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="/lib/bootstrap-modal/2.2.4/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="/lib/bootstrap-modal/2.2.4/bootstrap-modal.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="/lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/lib/date/date.js"></script>
<script type="text/javascript">
	$(function(){
		laypage({
			  cont: $('#page'), //容器。值支持id名、原生dom对象，jquery对象,
			    pages: ${page.pages}, //总页数
			    skin: 'molv', //皮肤
			    first: '首页', //若不显示，设置false即可
			    last: '尾页', //若不显示，设置false即可
			    prev: '<', //若不显示，设置false即可
			    next: '>', //若不显示，设置false即可
                skip: 'true',		    
			    //skin: '#AF0000',
			    //groups: 3, //连续显示分页数
			    jump:function(obj){
			    	$("#pageId").val(obj.curr);
			    	var searchKey = $("#searchKey").val();
			    	Data.get(obj.curr, searchKey);
			    }
		});
	})

	var Data = {
		"get" : function(pageId,searchKey){
			var tip = layer.msg('加载中', {icon: 16, time: 0, area: ['180px', '64px']});
			var url = "/admin/foodInfo/listAjax.do";
			$.ajax({
				"url" : url,
				"data" : {
					"pageId" : pageId,
					"searchKey" : searchKey
				},
				"type" : "POST",
				"dataType" : "json",
				"async" : true,
				"success" : function(data) {
					if(data && data.status==1){
						var html = "";
						for(var i = 0; i<data.result.length; i++){
							var id = data.result[i].id == null ? "" : data.result[i].id;
							var name = data.result[i].name == null ? "" : data.result[i].name;
							var norms = data.result[i].norms == null ? "" : data.result[i].norms;
							var price = data.result[i].price == null ? "" : data.result[i].price;
							var redemptionPrice = data.result[i].redemptionPrice == null ? "" : data.result[i].redemptionPrice;
							var unit = data.result[i].unit == null ? "" : data.result[i].unit;
							var categoryId = data.result[i].categoryId == null ? "" : data.result[i].categoryId;
							var status = data.result[i].status == null ? "" : data.result[i].status;
							var statusName,statusNameShow;
							var transportPrice = 0;
							if(status==1){
								statusName="已下架";
								statusNameShow="上架";
							}else{
								statusName="已上架";
								statusNameShow="下架";
							}
							html += "<tr>";
							html += "<td class='col1'><input type='checkbox' name='t_id' value='"+ id+"_"+ name
							+"_"+price+"_"+transportPrice+"'></td>";
							html += "<td class='col1'><a href='javascript:Data.edit(\""+id+"\");' >" + name + "</a></td>";
							html += "<td class='col1'>" + norms + "</td>";
							html += "<td class='col1'>" + price + "</td>";
							html += "<td class='col1'>" + redemptionPrice + "</td>";
							html += "<td class='col1'>" + unit + "</td>";
							html += "<td class='col1'>" + categoryId + "</td>";
							html += "<td class='col1'>" + status + "</td>";
							html += "<td class='col1'>";
							html += "<a href='javascript:Data.changeStatus(\""+id+"\");' >"+statusNameShow+"</a> ";
							html += "<a href='javascript:Data.delete(\""+id+"\");' >删除</a>";
							html += "</td>";
							html += "</tr>";
						}
// 						alert(html);
						$("#dataTable").html(html);
					}else{
					    $("#dataTable").html("");
					}
				},
                "error" : function() {
				    alert("数据有误");
			    },
			    "complete": function(){
					layer.close(tip);
				}
			});
		},
		"edit":function(id){
			if(!id){
				id=0;
			}
			var layerIndex = layer.open({
		      type: 2,
		      title: "角色信息",
		      area: ['650px', '500px'],
		        //skin: 'layui-layer-rim', //加上边框
		        maxmin: true,
		        shade: false,
		      content: '/admin/foodInfo/toEdit.do?id='+id
		    });
			layer.full(layerIndex);
		},
		"changeStatus":function(id){
			if(confirm("确定更改状态？")){
				$.ajax({
					url:"/admin/foodInfo/changeStatus.do",
					data:{
						"id" : id
					},
					"dataType" : "json",
					"type" : "POST",
					"async" : true,
					"traditional" :true,  
					"success" : function(data) {
						if(data && data.status==1){
							layer.msg("更改状态成功");
							submit();
						}else{
							if(data.message){
								layer.alert(data.message);
							}else{
								layer.alert("更改状态失败");
							}
						}
					}
				})
			}
		},
		"selMerchandise" : function(post) {
			 var str = document.getElementsByName("t_id");
	         var chestr = "";
	         var j = 0;
	         for (i=0; i<str.length; i++){
				  if(str[i].checked == true){
				      chestr+=str[i].value+",";
		              j++;
				  }
	         }
	
	         if(j == 0){
	             alert('请选择一条记录');
	             return;
	         }
	         if(j > 1){
	             alert('只能选择一条记录');
	             return;
	         }
	          if(chestr.length > 0){
	               chestr = chestr.substr(0,chestr.length-1);
	          }
	          $.ajax({
					"url" : "/admin/order/selefoodinfo.do",
					"data" : {
						"id" : chestr.split("_")[0]
					},
					"type" : "POST",
					"dataType" : "json",
					"async" : true,
					"success" : function(data) {
						if(data && data.status==1){
						 window.parent.document.getElementById("foodInfoId"+post).options.remove(0);
				         window.parent.document.getElementById("foodInfoId"+post).options.add(new Option(chestr.split("_")[1],chestr.split("_")[0]));
				         window.parent.document.getElementById("foodPrice"+post).value=data.result.price;
				         window.parent.document.getElementById("foodNum"+post).value='1';
				         $("#totalAmount"+post,window.parent.document).html(data.result.price);
				         //window.parent.document.getElementById("totalAmount"+post).innerHTML=data.result.price;
				         //window.parent.document.getElementById("transportPrice"+post).innerHTML="0.00";
				         Data.getTotalFee();
				         if(parent.layer){
				         	parent.layer.closeAll();
				         }
				         window.close();
						}else{
							if(data.data.errMsg){
								alert(data.data.errMsg);
							}
						}
					}
				})
      	},
      	"getTotalFee":function(){
      		var totalDiscount=0;totalAmount=0;
			$("input[name='discountAmount']",window.parent.document).each(function(index){
				totalDiscount += parseFloat($(this).val());
			});
			$("span[name='totalAmount']",window.parent.document).each(function(index){
				totalAmount += parseFloat($(this).html());
			});
			$("#totalFoodAmount",window.parent.document).html((totalDiscount+totalAmount).toFixed(2));
			$("#totalOrderAmount",window.parent.document).html(totalAmount.toFixed(2));
			$("#OrderAmount",window.parent.document).html(totalAmount.toFixed(2));
		},

		"delete": function(){
			if(confirm("确定删除记录？")){
				$.ajax({
					url:"/admin/foodInfo/delete.do",
					data:{
						"id" : id
					},
					"dataType" : "json",
					"type" : "POST",
					"async" : true,
					"traditional" :true,  
					"success" : function(data) {
						if(data && data.status==1){
							parent.layer.alert("删除成功");
							submit();
						}else{
							if(data.data.errMsg){
								parent.layer.alert(data.data.errMsg);
							}else{
								parent.layer.alert("删除失败");
							}
						}
					}
				})
			}
		}
	}

	function submit(){
		var tip = layer.load(2);
		//因为弹出框会加载在form标签之外
		$("input").attr('form','form');
		$("select").attr('form','form');
		$("#form").submit();
	}

	$("#searchKey1").bind("change",function(){
		$("#searchKey").val($("#searchKey1").val());
	});

	$("#searchKey").bind("change",function(){
		$("#searchKey1").val($("#searchKey").val());
	});

	$(".tab_query").click(function(){
		$(".tab_query").each(function(index){
			$(this).attr("class", "tab_query");
		});
		$(this).attr("class", "tab_query tqselected");
		$("#status").val($(this).attr("value"));
		submit();
	});

	function changeOrder(orderName){
		if(orderName){
			$("#orderDefind").val(orderName);
			$("#"+orderName).html("（√）");
			if(orderName=='createAtOrder'){
				$("#nameOrder").html("");
			}else{
				$("#createAtOrder").html("");
			}
			submit();
		}
	}


</script>
</body>
</html>