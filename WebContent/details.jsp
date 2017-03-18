<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Details</title>

<script type='text/javascript' src="js/jquery-1.11.1.min.js"></script>
<script src="js/detail.js"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<input type="hidden" id="bookId-detail" value="${book.id }">
	<div class="container">
		<div class="women_main">
			<!-- start content -->
			<div class="row single">
				<div class="col-md-12 det">
					<div class="single_left">
						<div class="grid images_3_of_2">
							<img src="uploadimg/${book.imgPath}" />
						</div>
						<div class="desc1 span_3_of_2">

							<h3>${book.name}</h3>
							<br/> <span class="code">著者/译者：</span>${book.author}<br/>
							<br/> <span class="code">出版社：<a href="#">${book.press}</a></span><br/>
							<br/> <span class="code">ISBN：</span>${book.isbn}<br/>
							<br/> <span class="code">出版日期：</span>${book.publishTime}<br/>
							<br/>
							<div class="price">
								<span class="">价格:</span> <span class="price-new">${book.price}</span>

							</div>
							<div class="btn_form">
								<a id="addToCart-btn"><label class="glyphicon glyphicon-shopping-cart"></label>&nbsp;加入购物车</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="single-bottom1">
						<h6>书籍详情</h6>
						<p class="prod-desc">${book.introduction}</p>
					</div>
					<div class="single-bottom2">
						<h6>累计评价</h6>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>