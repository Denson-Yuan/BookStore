<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home</title>

<script type='text/javascript' src="js/jquery-1.11.1.min.js"></script>
<script type='text/javascript' src="js/placeorder.js"></script>
<link href="css/placeorder.css" rel='stylesheet' type='text/css' />
</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>
	<div class="container" id="placeorder-main">
		<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<td class="col-xs-1">图片</td>
				<td class="col-xs-5">名称</td>
				<td class="col-xs-2">数量</td>
				<td class="col-xs-2">单价</td>
				<td class="col-xs-2">小计</td>
			</tr>
		</thead>
		<tbody id="order-detail">
			<!-- js动态添加 -->
		</tbody>
		</table>
		
		<table class="table table-bordered table-reciver-info">
		<thead>
			<tr>
				<td class="col-xs-2">收货人</td>
				<td class="col-xs-2">联系电话</td>
				<td class="col-xs-8">地址</td>
			</tr>
		</thead>
		<tbody id="reciver-info">
			<tr>
				<td><input class="form-control" id="reciver" value="" /></td>
				<td><input class="form-control" id="phone" value=""/></td>
				<td><input class="form-control" id="address" value=""/></td>
			</tr>
			<tr>
				<td>备注</td>
				<td colspan="3"><input class="form-control" id="remark" value="" /></td>
			</tr>
		</tbody>
		</table>
		<div class="col-xs-3 col-xs-offset-9 btn btn-success btn-lg submit-order" id="btn-submit-order">提&nbsp;&nbsp;&nbsp;&nbsp;交</div>
	</div>
</body>
</html>