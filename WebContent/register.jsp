<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type='text/javascript' src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/register.js"></script>

<title>注册</title>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="container">
<div class="main">
	<!-- start registration -->
	<div class="registration">
		<div class="registration_left">
		<h2>新用户？ <span> 创建一个账户 </span></h2>
		<script>
			(function() {
		
			// Create input element for testing
			var inputs = document.createElement('input');
			
			// Create the supports object
			var supports = {};
			
			supports.autofocus   = 'autofocus' in inputs;
			supports.required    = 'required' in inputs;
			supports.placeholder = 'placeholder' in inputs;
		
			// Fallback for autofocus attribute
			if(!supports.autofocus) {
				
			}
			
			// Fallback for required attribute
			if(!supports.required) {
				
			}
		
			// Fallback for placeholder attribute
			if(!supports.placeholder) {
				
			}
			
			// Change text inside send button on submit
			var send = document.getElementById('register-submit');
			if(send) {
				send.onclick = function () {
					this.innerHTML = '...Sending';
				}
			}
		
		})();
		</script>
		 <div class="registration_form">
		 <!-- Form -->
			<div id="registration_form">
				<input type="hidden" id="user-role" value="1">
				<div>
					<label>
						<input placeholder="姓名：" type="text" tabindex="1" id="user-name" required autofocus>
					</label>
				</div>
				<div>
					<label>
						<input placeholder="邮箱地址：" type="email" tabindex="3" id="user-email" required>
					</label>
				</div>
				<div class="sky-form">
					<div class="sky_form1">
						
					</div>
				</div>
				<div>
					<label>
						<input placeholder="密码：" type="password" id="user-password" tabindex="4" required>
					</label>
				</div>						
				<div>
					<label>
						<input placeholder="确认密码：" type="password" id="con-password" tabindex="4" required>
					</label>
				</div>	
				<div>
					<input type="submit" value="注册" id="register-submit">
				</div>
			</div>
			<!-- /Form -->
		</div>
	</div>
	<div class="registration_left">
		<h2>已有账户</h2>
		 <div class="registration_form">
		 <!-- Form -->
			<form id="registration_form" action="contact.php" method="post">
				<div>
					<label>
						<input placeholder="邮箱：" type="email" tabindex="3" required>
					</label>
				</div>
				<div>
					<label>
						<input placeholder="密码：" type="password" tabindex="4" required>
					</label>
				</div>						
				<div>
					<input type="submit" value="登录" id="register-submit">
				</div>
				<div class="forget">
					<a href="#">忘记密码</a>
				</div>
			</form>
			<!-- /Form -->
			</div>
	</div>
	<div class="clearfix"></div>
	</div>
	<!-- end registration -->
</div>
</div>
</body>
</html>