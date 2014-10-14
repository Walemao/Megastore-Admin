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
		<div id="content">
			<ul id="nav-info" class="clearfix">
				<li><a href="#"><i class="icon-gift"></i></a></li>
				<li><a href="../users">用户管理</a></li>
				<li class="active"><a href="#">用户信息</a></li>
			</ul>

			<!-- 用户基本信息 -->
			<h3 class="page-header page-header-top">用户信息</h3>
			<ul class="nav nav-tabs push" role="tablist" data-toggle="tabs">
				<li role="presentation" class="active"><a
					href="#dash-tabs-user-base">基本信息</a></li>
				<li role="presentation"><a href="#dash-tabs-user-info">详细信息</a></li>
				<li role="presentation"><a href="#dash-tabs-user-extra">拓展资料</a></li>
			</ul>
			<div class="tab-content tab-content-default">
				<div class="tab-pane active" id="dash-tabs-user-base">
					<div class="row">
						<div class="col-md-7">
							<table class="table user-base-info-table">
								<tr>
									<td width="20"><label><i class="icon-user"></i>用户名：</label></td>
									<td width="30"><c:out value="${user.username}" /></td>
									<td width="20"><label><i class="icon-envelope"></i>邮箱：</label></td>
									<td width="30"><c:out value="${user.email}" /></td>
								</tr>
								<tr>
									<td><label><i class="icon-phone-sign"></i>电话：</label></td>
									<td><c:out value="${user.mobilephone}" /></td>
									<td><label><i class="icon-flag"></i>是否启用：</label></td>
									<td><c:if test="${user.enabled==true}">
							      启用中
							   </c:if> <c:if test="${user.enabled==false}">
							      已禁用
							   </c:if></td>
								</tr>
								<tr>
									<td><label><i class="icon-time"></i>注册时间：</label></td>
									<td><c:out value="${user.createTime}" /></td>
									<td><label><i class="icon-star"></i>用户等级：</label></td>
									<td><c:out value="${user.level}" /></td>
								</tr>
							</table>
						</div>
						<div class="col-md-1">
							<!-- 空间占位标签 -->
						</div>
						<div class="col-md-2">
							<h5 class="page-header-sub" style="padding-left: 10px;">
								<i class="icon-camera-retro"></i>用户头像
							</h5>
							<a class="thumbnail" style="width: 120px;"> <img
								src="<c:url value="/resources-admin/images/user_image.png"/>"
								width="120" height="120" />
							</a>
						</div>
					</div>
				</div>

				<div class="tab-pane" id="dash-tabs-user-info">asdsdfsdfghh</div>

				<div class="tab-pane" id="dash-tabs-user-extra">nmhyrty</div>
			</div>

			<!-- 用户订单信息 -->
			<div class="page-header" style="margin-top: 50px;">
				用户订单
				<form action="<c:url value="/admin/user/orders" />" method="get"
					class="form-inline user-order-search-form" role="form">
					<input type="hidden" name="username" value="${user.username}"/>
					<div class="form-group">
						<label class="control-label" for="status">订单状态：</label> <select
							class="form-control" name="status" id="orderStatus">
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
			</div>

			<table class="table content-list-table">
				<thead>
					<tr>
						<th><i class="icon-list-ol"></i>订单编号</th>
						<th><i class="icon-user"></i>收货人</th>
						<th><i class="icon-time"></i>下单时间</th>
						<th><i class="icon-lightbulb"></i>订单状态</th>
						<th><i class="icon-bolt"></i>操作</th>
					</tr>
				</thead>
				<tbody class="user-orders-tbody">
				</tbody>

				<tfoot class="user-orders-tfoot">
				</tfoot>
			</table>


			<!-- 用户收货地址信息 -->
			<h3 class="page-header">用户收货地址</h3>
		</div>

	</div>
	<%@ include file="/WEB-INF/views/includes/admin_footer.jspf"%>
</body>
<%@ include file="/WEB-INF/views/includes/admin_foot_scripts_links.jspf"%>
<script type="text/javascript">
	/** 获取用户订单信息 **/
	$('.user-order-search-form').submit(function(e){
		e.preventDefault();
		var params = $(this).serialize();
		$.get('<c:url value="/admin/user/orders" />', params, function(data){
			if(data.pageItems.length == 0){
				$('.user-orders-tbody').html('<tr><td colspan="5"><span class="no-message">没查到订单！</span></td></tr>');
			}else{
				appendUserOrders(data, '<c:url value="/admin/order/" />');
			}			
		});		
	});
	$('.user-order-search-form').submit();
	
	
</script>
</html>