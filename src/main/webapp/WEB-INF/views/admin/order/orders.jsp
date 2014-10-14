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
		<div class="well clearfix">
			<form action="<c:url value="/admin/products" />" method="get"
				class="form-inline content-search-form" role="form">
				<div class="form-group">
					<label class="control-label" for="productName">订单编号：</label> <input
						type="text" class="form-control" id="productName"
						name="productName" placeholder="输入订单编号"
						value="<c:out value="${productName}"></c:out>">
				</div>
				<div class="form-group">
					<label class="control-label" for="status">订单状态：</label> <select
						class="form-control" name="orderStatus" id="orderStatus">
						<option value="-1">全部状态</option>
						<option value="0">等待付款</option>
						<option value="1">等待收货</option>
						<option value="2">已完成</option>
						<option value="3">已取消</option>
					</select>
				</div>
				<div class="form-group">
					<label class="control-label">选择时间：</label>
					<div id="advanced-daterangepicker" class="btn btn-default">
						<i class="icon-calendar"></i> <span class="daterange"><span
							class="start-date-span">${startDate}</span> 至 <span
							class="end-date-span">${endDate}</span></span> <b class="caret"></b> <input
							id="startDate" type="hidden" name="startDate"
							value="${startDate}" /> <input id="endDate" type="hidden"
							name="endDate" value="${endDate}">
					</div>
				</div>
				<button type="submit" class="btn btn-success">
					<i class="icon-search"></i>查询
				</button>
			</form>
			<table class="table content-list-table">
				<thead>
					<tr>
						<th>#</th>
						<th><i class="icon-camera-retro"></i>缩略图</th>
						<th><i class="icon-book"></i>商品名称</th>
						<th><i class="icon-tag"></i>商品类型</th>
						<th><i class="icon-time"></i>添加时间</th>
						<th><i class="icon-bolt"></i>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>