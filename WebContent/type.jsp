<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>分类</title>

<script type='text/javascript' src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/type.js"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<input type="hidden" id="typeId" value="${typeId }">
	<div class="container">
		<div class="women_main">
			<div class="col-md-3 s-d">
				<div class="w_sidebar">
					<div class="w_nav1">
						<h4>所有分类</h4>
						<ul id="allTypeList">
							<!-- js动态添加 -->
						</ul>
					</div>
				</div>
			</div>
			<!-- start content -->
			<div class="col-xs-9 w_content">
				<div class="women">
					<h4>${typeName }类</h4>
					<div class="clearfix"></div>
				</div>
				<!--grids_of_4 -->
				<div class="grids_of_4" id="first_grid">
					<!-- js动态添加 -->
				</div>
				<div class="grids_of_4" id="second_grid">
					<!-- js动态添加 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>