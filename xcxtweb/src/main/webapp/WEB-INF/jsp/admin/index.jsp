<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html style="overflow-y: hidden;">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/images/logo_pic.ico">
<LINK rel="Shortcut Icon" href="/images/logo_pic.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="/lib/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="/lib/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="/lib/h-ui.admin/css/style.css" />

<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>鲜菜管理系统</title>
</head>
<body style="overflow: hidden">
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<!-- <img class="l" src="/images/logo_pic.ico" style="margin: 10px 10px 0 0;width: 30px;">  -->
				<span class="logo navbar-logo f-l mr-10 hidden-xs">鲜菜管理系统</span>
				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs" style="right: 40px">
					<ul class="cl">
						<li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A">${user.name}<i class="Hui-iconfont"></i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:toUpdatePwd()">修改密码</a></li>
							</ul></li>
						<li id="Hui-skin" class="dropDown right dropDown_hover"><a
							href="javascript:;" class="dropDown_A" title="换肤"><i
								class="Hui-iconfont" style="font-size: 18px"></i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default"
									title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
							</ul></li>
						<li class="dropDown dropDown_hover"><a
							href="javascript:logout()">退出</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside" style="padding-top: 0">
		<div id="info" class="menu_dropdown bk_2">
			<c:forEach var="menu" items="${menus }">
				<dl>
					<dt class="">
						<i class="Hui-iconfont Hui-iconfont-userid"></i> ${menu.name }<i
							class="Hui-iconfont menu_dropdown-arrow Hui-iconfont-arrow2-bottom"></i>
					</dt>
					<dd>
						<c:forEach items="${menu.cmenus }" var="cmenu">
							<ul>
								<li><a id="a_default" _href="${cmenu.url }"
									data-title="${cmenu.name }" href="javascript:void(0)">${cmenu.name }</a></li>
							</ul>
						</c:forEach>
					</dd>
				</dl>
			</c:forEach>
		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onclick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl"
					style="width: 133px; left: 0px;">
					<!-- <li class="active"><span title="消息提醒" data-href="welcome.html">消息提醒</span><em></em></li> -->
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group" style="display: none;">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont"></i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont"></i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<!-- <div class="show_iframe">
      <div style="display:none" class="loading"></div>
      <iframe scrolling="yes" frameborder="0" src="/flup/crm/tab.do"></iframe>
    </div> -->
		</div>
	</section>
	<!-- <section class="Hui-article-box">
  <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
    <div class="Hui-tabNav-wp">
      <ul id="min_title_list" class="acrossTab cl" style="width: 133px; left: 0px;">
      </ul>
    </div>
    <div class="Hui-tabNav-more btn-group" style="display: none;"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont"></i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont"></i></a></div>
  </div>
  <div id="iframe_box" class="Hui-article">
  </div>
</section> -->
	<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="/lib/layer/2.1/layer.js"></script>
	<script type="text/javascript" src="/lib/h-ui/js/H-ui.js"></script>
	<script type="text/javascript" src="/lib/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript"
		src="/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript">
  $(document).ready(function(){
    //$("#a_default").click();
  });
  function logout(){ 
    location.href = "/admin/logout.do";
  }
  function toUpdatePwd(){
    parent.layer.open({
      type: 2,
      title: "修改密码",
      area: ['650px', '500px'],
        //skin: 'layui-layer-rim', //加上边框
        maxmin: true,
        shade: false,
      content: '/admin/user/toUpdatePwd.do'
    });
    
  }
</script>
</body>
</html>
