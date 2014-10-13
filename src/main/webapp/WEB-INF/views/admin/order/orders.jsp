<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jspf"%>
<!DOCTYPE HTML>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>商城管理系统</title>
<%@ include file="/WEB-INF/views/includes/admin_head_scripts_links.jspf"%>
</head>
<body>
	<!-- 引用页面header模板 -->
	<%@ include file="/WEB-INF/views/includes/admin_header.jspf"%>

	<div id="container">
		<!-- 引用页面侧边栏模板 -->
		<%@ include file="/WEB-INF/views/includes/admin_aside.jspf"%>
	</div>
	<div id="content">
		<ul id="nav-info" class="clearfix">
			<li><a href="#"><i class="icon-gift"></i></a></li>
			<li class="active"><a href="#">订单管理</a></li>
		</ul>
		<h3 class="page-header page-header-top">订单列表</h3>
		<div class="well"></div>
	</div>
</body>
</html>