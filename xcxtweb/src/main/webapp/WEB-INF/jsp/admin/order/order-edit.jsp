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
					<td class="text-r" ><span class="text-warning">*</span>购买人：</td>
					<td><input datatype="*" maxlength="30" id="purchaser" name="purchaser" value="${order.purchaser }" placeholder="请输入购买人" class="input-text" type="text"  /></td>
					<td class="text-r"><span class="text-warning">*</span>电话：</td>
					<td><input datatype="*" maxlength="30" id="mobile" name="mobile" value="${order.mobile }" placeholder="请输入手机" class="input-text" type="text" /></td>
          </tr>
          <tr>
					<td class="text-r" ><span class="text-warning">*</span>收货地址：</td>
					<td><input datatype="*" maxlength="30" id="recAddress" name="recAddress" value="${order.recAddress }" placeholder="请输入购买人" class="input-text" type="text"  /></td>
					<td class="text-r"><span class="text-warning">*</span>订单状态：</td>
					<td>
					<select name='orderStatus' id='orderStatus' style="width: 90px;" disabled="true" >
			            <option value="0" <c:if test="${order.orderStatus == 0 }">selected</c:if>>待支付</option>
	            		<option value="1" <c:if test="${order.orderStatus == 1 }">selected</c:if>>待发货</option>
	            		<option value="2" <c:if test="${order.orderStatus == 2 }">selected</c:if>>待收货</option>
	            		<option value="3" <c:if test="${order.orderStatus == 3 }">selected</c:if>>已完成</option>
	            	</select>	
          </tr>
          <tr>
					<td class="text-r" ><span class="text-warning">*</span>订单编号：</td>
					<td><input datatype="*" maxlength="30" id="orderNo" name="orderNo" value="${order.orderNo}"  class="input-text" type="text"  /></td>
					<td class="text-r"><span class="text-warning">*</span>订单时间：</td>
		<td><input datatype="*" maxlength="30" id="orderdate" name="orderdate" onClick="WdatePicker()"  class="input-text" value="<fmt:formatDate type="both" value="${order.orderDate}"   />" /></td>
          </tr>
          <tr>
					<td class="text-r" ><span class="text-warning">*</span>付款时间：</td>
					<td><input maxlength="30" id="paymentdate" name="paymentdate"  class="input-text" value="<fmt:formatDate type="both" value="${order.payMentDate}" />" /></td>
					<td class="text-r"><span class="text-warning">*</span>发货时间：</td>
					<td><input maxlength="30" id="deliverydate" name="deliverydate"  class="input-text" value="<fmt:formatDate type="both" value="${order.deliveryDate}" />" /></td>
          </tr>
          <tr>
					<td class="text-r" ><span class="text-warning">*</span>收货时间：</td>
					<td><input maxlength="30" id="receivingdate" name="receivingdate"  class="input-text"  value="<fmt:formatDate type="both" value="${order.receivingDate}" />" /></td>
					<td class="text-r"></td>
					<td><input class="btn btn-primary radius" type="submit" value="提交" />
					<input class="btn btn-primary radius" type="button" value="返回"  onClick="OrderEdit.back()"></td>
          </tr>
       
       </tbody>
    </table>
   <!-- 订单明细 开始 -->
		<table class="table table-border table-bordered table-striped" id="J_table">
				<thead>
					<tr >
						<th class="col2">菜品名称</th>
						<th class="col2">单价（元）</th>
						<th class="col2">数量</th>
						<th class="col3">优惠</th>
						<th class="col2">总计（元）</th>
						<th class="col3" nowrap="nowrap" style="width: 100px;"><a href="#" onclick="OrderEdit.addOrderList()">+新增</a></th>
					</tr>
				</thead>
				<thead id="dataTable">
					<c:forEach items="${orderdetail }" var="detail" varStatus="var">
						<tr id='tr"+b${var.index}"'>
						<td class='col1' >
							<input   type="hidden" id="detailidb${var.index }" name="detailid" value="${detail.id }"  />
							<select class='editDisable' disabled="disabled"  style='width:150px;' onchange='OrderEdit.changeMerchandise("b${var.index }")' id='foodInfoIdb${var.index }' name='foodInfoId' value=''>
							<option value="${detail.foodInfoId }">${detail.foodInfoName }</option>
							</select>
							<input class='editDisable' id="merchandiseSelb${var.index }" type='button' value='请选择' onclick='OrderEdit.merchandiseSelect("b${var.index}")' />
							<td><input class='editDisable1' disabled="disabled" type="text" id="foodPriceb${var.index }" name="foodPrice" value="${detail.foodPrice }"  /></td>
							<td><input class='editDisable' disabled="disabled"  size='10' id='foodNumb${var.index }' name='foodNum' value='${detail.foodNum }' onchange='OrderEdit.changePrice("b${var.index }")'/></td>
							<td><input class='editDisable' disabled="disabled"  size='10' id='discountAmountb${var.index }' name='discountAmount' value='${detail.discountAmount }' onchange='OrderEdit.changePrice("b${var.index }")'/></td>
							<td class='col1'><span name='totalAmount' id='totalAmountb${var.index }'>${detail.totalAmount }</span></td>
							<td calss='col1'>
							<input type="button" id="editBtb${var.index }" value="编辑" onclick="OrderEdit.checkOrderList('b${var.index}')" />
							<span class=\"pipe\"></span>
							<input type="button" id="delBtb${var.index }" value="删除" onclick="OrderEdit.del('${detail.id}',${order.id })"  /> 
							</td>
						</tr>
					</c:forEach>
				</thead>
				<thead border="0">
					<tr>
						<td border="0" colspan="4"> </td>
						<td border="0" colspan="2">菜品总价： <span id="totalFoodAmount">${order.totalFoodAmount }</span>元</td>
					</tr>
					<tr>
						<td colspan="4"> </td>
						<td colspan="2">运费(快递)： <span id="totalFee">${order.freight }</span>0.00元</td>
					</tr>
					<tr>
						<td colspan="4"> </td>
						<td colspan="2">订单总价： <span id="totalOrderAmount">${order.totalOrderAmount }</span>元</td>
					</tr>
					<tr>
						<td colspan="4"> </td>
						<td colspan="2"><font size="3">实付款：</font> <span id="OrderAmount">${order.totalOrderAmount }</span>元</td>
					</tr>
				</thead>
			</table>
      <!-- 订单明细结束 -->			   
	
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
	$(document).ready(function(){
		//实例化编辑器
	
		$("#form").Validform({
			tiptype:3,
            label:"Validform_checktip", 
            showAllError:true,
	        //btnSubmit:"#bt_submit", 
	        tipSweep:true,  
	        beforeSubmit:function(){
	        	var isNum = true;
	        	$("input[name='num']").each(function(index){
		        	if(!OrderEdit.isNum($(this).val())){
		        		isNum = false;
		        	}
				});
	        	$("input[name='discount']").each(function(index){
		        	if(!OrderEdit.isDouble($(this).val())){
		        		isNum = false;
		        	}
				});
	        	if(!isNum){
	        		return false;
	        	}
	        	if(!$("#orderNo").val()){
	        		alert("订单编号不能为空");
	        		return false;
	        	}
	        	var count = 0;
	        	$("select[name='foodInfoId']").each(function(index){
	        		count++;
				});
	        	if(count==0){
	        		alert("请选择菜品");
	        		return false;
	        	}
	        	OrderEdit.save();
	            return false;  
	        }  
	    });
		
		$(".editDisable").attr("disabled","disabled");
		
		OrderEdit.getTotalFee();
				
	});

	var OrderEdit = {
		"isNum":function(val){
			var num = val.trim();
		    if(num!=null && num.length!=0){
		        var reg = /^\d{1,5}$/;
		        var r = num.match(reg);     
		        if(r==null){
		            alert('输入的数量格式不正确!');
					return false;
		        }
		        return true;
	        }else{
	        	alert("数量不能为空");
	        }
		    return false;
		},
		"isDouble":function(val){
			val = val.trim();
			if(val!=null && val.length!=0){
		        var reg = /^\d+(?:\.\d{1,2})?$/;
		        var r = val.match(reg);     
		        if(r==null){
		            alert('输入的价格格式不正确!');
					return false;
		        }
		        return true;
	        }else{
	        	alert("价格不能为空");
	        }
			return false;
		},
		"back" : function() {
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				parent.layer.close(index); //执行关闭
				location.href = "/admin/order/list.do";
		},
		"orderListNum":0,
		"addOrderList":function(){
			var id = $("#id").val();
			var html="";
			html += "<tr id='tr"+OrderEdit.orderListNum+"'>";
			html += "<td class='col1'>";
			//html += "<input disabled='disabled' size='10' id='detailid"+OrderEdit.orderListNum+"' name='detailid' value='1' />";	
			html += "<select style='width:150px;' onchange='OrderEdit.changeMerchandise(\""+OrderEdit.orderListNum+"\")' id='foodInfoId"+OrderEdit.orderListNum+"' name='foodInfoId' value=''></select><input type='button' value='请选择' onclick='OrderEdit.merchandiseSelect(\""+OrderEdit.orderListNum+"\")' />";
			html += "</td>";
			html += "<td class='col1'><input size='20' readonly='readonly' id='foodPrice"+OrderEdit.orderListNum+"' name='foodPrice' /></td>";
			html += "<td class='col1'><input size='10' id='foodNum"+OrderEdit.orderListNum+"' name='foodNum' value='1' onchange='OrderEdit.changePrice(\""+OrderEdit.orderListNum+"\")'/></td>";	
			var discountVal = 0;
			html += "<td class='col1'><input size='10' id='discountAmount"+OrderEdit.orderListNum+"' name='discountAmount' value='"+discountVal+"' onchange='OrderEdit.changePrice(\""+OrderEdit.orderListNum+"\")'/></td>";
			html += "<td class='col1'><span name='totalAmount' id='totalAmount"+OrderEdit.orderListNum+"'></span></td>";
			html += "<td class='col1'>";
			if(id){
				html += "<input type='button' id='editBt"+OrderEdit.orderListNum+"' value='保存' onclick='OrderEdit.checkOrderList(\""+OrderEdit.orderListNum+"\",\"save\")' /><span class=\"pipe\">|</span>";
			}
			html += "<input type='button' value='删除' onclick='OrderEdit.removeTr(\""+OrderEdit.orderListNum+"\")' />";
			html += "</td>";
			html += "</tr>";
			OrderEdit.orderListNum++;
			$("#dataTable").append(html);
		},
		"merchandiseSelect":function(post){
			//var orderType = $("#orderType").val();
			//var sysType = $("#sysType").val();
			// if(orderType==0){
			// 	window.open("/admin/order/toseleclist.do?post="+post+"&sysType="+sysType, null, "height=800px, width=1000px, top=250px, left=300px,status=no");
			// }else{
			// 	window.open("/admin/merchandise/toSelVip.do?post="+post, null, "height=800px, width=1000px, top=250px, left=300px,status=no");
			// }
			
			var title="选择菜品",
			url = "/admin/order/toseleclist.do?post="+post;//+"&sysType="+sysType;
			var index = layer.open({
				type: 2,
				area: ['750px', '500px'],
		        maxmin: true,
				title: title,
				content: url
			});
			layer.full(index);
		},
		"patientSelect":function(post){
			var index = layer.open({
				type: 2,
				title: "选择购买人",
				content: "/admin/merchandise/toSelPatient.do?post="+post
			});
			layer.full(index);
			//swindow.open("/admin/merchandise/toSelPatient.do?post="+post, null, "height=800px, width=1000px, top=250px, left=300px,status=no");
		},
		"changePrice":function(post){
			var orderId = $("#id").val();
			var price = $("#foodPrice"+post).val();
			var num = $("#foodNum"+post).val();
			var discount = $("#discountAmount"+post).val();
			if(!price)price=0;
			if(!num)num=1;
			if(!discount)discount=0;
			if(!OrderEdit.isNum($("#foodNum"+post).val())){
			   return;
			}
			if(!OrderEdit.isDouble($("#discountAmount"+post).val())){
       		return;
            }
        	$("#totalAmount"+post).html(price*num-discount);
        	
        	OrderEdit.getTotalFee();
		},
		"getTotalFee":function(){
			var totalDiscount=0;totalAmount=0;
			$("input[name='discountAmount']").each(function(index){
				totalDiscount += parseFloat($(this).val());
			});
			$("span[name='totalAmount']").each(function(index){
				totalAmount += parseFloat($(this).html());
			});
			$("#totalFoodAmount").html((totalDiscount+totalAmount).toFixed(2));
			$("#totalOrderAmount").html(totalAmount.toFixed(2));
			$("#OrderAmount").html(totalAmount.toFixed(2));
			
		},
		"save":function(){
			var url = "/admin/order/save.do";
			$.ajax({
				"url" : url,
				"data" : $("#form").serialize(),
				"type" : "POST",
				"dataType" : "json",
				"async" : true,
				"success" : function(data) {
					if(data.status ==1){
						alert("保存成功！");
					}else{
						alert("保存失败！");
					}
				}
			});
		},

		"changeOrderType":function(){
			if(OrderEdit.orderListNum>0){
				if(window.confirm("修改订单类型将清空所选商品或套餐，确定修改？")){
					//清空商品列表
					$("select[name='foodInfoId']").each(function(index){
						$(this).empty();
					});
					//清空医生列表
					$("select[name='doctorUserId']").each(function(index){
						$(this).empty();
						var classStatus = $(this).attr("class");
						if(classStatus=='hideSelect'){
							$(this).attr("class","showSelect");
						}else{
							$(this).attr("class","hideSelect");
						}
					});
					//清空价格
					$("input[name='price']").each(function(index){
						$(this).val(0);
					});
					//清空数量
					$("input[name='num']").each(function(index){
						$(this).val(0);
					});
					//清空优惠
					$("input[name='discount']").each(function(index){
						$(this).val(0);
					});
					//清空运费
					$("input[name='transportPrice']").each(function(index){
						$(this).html('0');
					});
					//清空总价
					$("input[name='total']").each(function(index){
						$(this).html('0');
					});
					
				}else{
					if($("#orderType").val()==0){
						$("#orderType").val(1);
					}else if($("#orderType").val()==1){
						$("#orderType").val(0);
					}
				}
			}
		},
		"checkOrderList":function(post,saveType){
			if(!saveType){
				saveType="";
			}
			if(!$("#foodInfoId"+post).val()){
				alert("菜品不能为空");
				return;
			}
//			if(!OrderEdit.isDouble($("#price"+post).val()))
//        		return;
//			if(!OrderEdit.isNum($("#num"+post).val()))
//				return;
//			if(!OrderEdit.isDouble($("#discount"+post).val()))
//       		return;
        		
						if(saveType=="save"){
							$("#editBt"+post).val("编辑");
							$(".editDisable").attr("disabled","disabled");
                            $("#editBt"+post).attr("onclick","OrderEdit.saveOrderList('"+post+"','"+saveType+"')");
							OrderEdit.saveOrderList(post,saveType);
						    saveType="";
						}else{
						saveType="save";
							$("#editBt"+post).val("保存");
							$(".editDisable").removeAttr("disabled");
							$("#editBt"+post).attr("onclick","OrderEdit.saveOrderList('"+post+"','"+saveType+"')");
						}

		},
		"saveOrderList":function(post,saveType){
			if(!post){
				return;
			}
			
            var id=$("#detailid"+post).val();
			var orderId = $("#id").val();
			var foodInfoId = $("#foodInfoId"+post).val();
			var foodPrice = $("#foodPrice"+post).val();
			var foodNum = $("#foodNum"+post).val();
			var discountAmount = $("#discountAmount"+post).val();

			$.ajax({
				"url" : "/admin/order/saveOrderDetail.do",
				"data" : {
				    "id":id,
					"orderId":orderId,
					"foodInfoId":foodInfoId,
					"foodPrice":foodPrice,
					"foodNum":foodNum,
					"discountAmount":discountAmount
				},
				"type" : "POST",
				"dataType" : "json",
				"async" : true,
				"success" : function(data) {
					if(data.data.status == 1){
						alert(data.data.message);
						//window.location.href="/admin/order/orderOffLineEdit.do?id="+$("#id").val();
						alert("dddd");
					}else{
						alert(data.data.message);
						}
					}
					
			});
		},
		"del":function(id,orderid){
			if(!id){
				return;
			}
			if(!orderid){
			   return
			}

			$.ajax({
				"url" : "/admin/order/deldetail.do",
				"data" : {
					"id":id
				},
				"type" : "POST",
				"dataType" : "json",
				"async" : true,
				"success" : function(data) {
					if(data.status ==1){
						alert("删除成功！");
						window.location.href="/admin/order/toEdit.do?id="+orderid;
						
					}else{
						alert("删除失败！");
					}
				}
			});
		},
	   "removeTr":function(post){
		    var tr=document.getElementById('tr'+post);
		    tr.parentNode.removeChild(tr);
		    OrderEdit.getTotalFee();
	   }
	}

</script>
</body>
</html>