<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心</title>
<link href="css/personalpage.css" rel='stylesheet' type='text/css' />

<script type='text/javascript' src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/personalpage.js"></script>

</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="container top-btn">
		<div class="btn btn-success" id="myOrder-btn">我的订单</div>
		<div class="btn btn-success" id="myInfo-btn">个人信息</div>
	</div>
	
	<div class="container" id="myorder" >
		<!-- js添加 -->
	</div>


	<div id="per_ziliao" style="display:none">

		<div class="registration_person">
			<div class="registration_form" id="person">
				<form id="registration_form" action="user_update.action"
					method="post">
					<div>
						<input placeholder="用户名：" type="text" id="name" name="user.name"
							value="${sessionScope.user.name}" readonly="readonly"
							tabindex="1" required autofocus>
					</div>
					<div>
						<input placeholder="邮箱：" type="text" id="email" name="user.email"
							value="${sessionScope.user.email}" readonly="readonly"
							tabindex="3" required>
					</div>
					<div class="sky-form">
						<div class="sky_form1">
							<ul>
								<li><label class="radio left"><input type="radio"
										name="radio" readonly="readonly" value="0"
										${sessionScope.user.sex==0?'checked':''}><i></i>男</label></li>
								<li><label class="radio"> <input type="radio"
										name="radio" readonly="readonly" value="1"
										${sessionScope.user.sex==1?'checked':''}><i></i>女
								</label></li>
								<div class="clearfix"></div>
							</ul>
						</div>
					</div>
					<div>
						<input placeholder="电话：" type="text" id="phone" name="user.phone"
							value="${sessionScope.user.phone}" tabindex="4" readonly>

					</div>
					<div>
						<input placeholder="地址1：" type="text" id="address1"
							name="user.address1" value="${sessionScope.user.address1}"
							tabindex="4" readonly>
					</div>
					<div>
						<input placeholder="地址2：" type="text" id="address2"
							name="user.address2" value="${sessionScope.user.address2}"
							tabindex="4" readonly>
					</div>

					<div id="juzhong">
						<input type="button" value="修改" id="button12"
							onclick="onclick1();">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input
							type="submit" value="保存" id="register-submit"
							onclick="onclick2()">
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>