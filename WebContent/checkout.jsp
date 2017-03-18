<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home</title>

<script type='text/javascript' src="js/jquery-1.11.1.min.js"></script>
<script type='text/javascript' src="js/checkout.js"></script>
<link href="css/checkout.css" rel='stylesheet' type='text/css' />
</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>

	<div class="container" id="cart-main">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<td class="col-xs-1">图片</td>
						<td class="col-xs-3">名称</td>
						<td class="col-xs-2">数量</td>
						<td class="col-xs-1">库存量</td>
						<td class="col-xs-2">单价</td>
						<td class="col-xs-2">小计</td>
						<td class="col-xs-1"></td>
					</tr>
				</thead>
				<tbody id="cart_detail">
					
				</tbody>
			</table>
		<label class="col-xs-3 col-xs-offset-9 total-price">合计：</label>
		<div
			class="col-xs-3 col-xs-offset-9 btn btn-success btn-lg checkout-btn">结&nbsp;&nbsp;&nbsp;&nbsp;算</div>
	</div>
</body>
</html>