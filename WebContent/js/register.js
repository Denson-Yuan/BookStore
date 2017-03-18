$(function(){
	
	
	var emailPattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	
	$("#register-submit").click(function(){
		var role = $("#user-role").val();
		var name = $("#user-name").val();
		var email = $("#user-email").val();
		var password = $("#user-password").val();
		var conpassword = $("#con-password").val();
		alert(name);
		
		var noProblem = true;

        if (email == "" || password == "" || name=="" || conpassword=="") {
            alert("请将信息填写完整！");
            noProblem = false;
        }else if (!emailPattern.test(email)) {
            alert("邮箱格式不正确！");
            noProblem = false;
        }else if(password != conpassword){
        	alert("两次输入的密码不一致");
        	noProblem = false;
        }
        
        if(noProblem){
        	$.post("user_register.action", {"user.email": email,"user.password":password,"user.role":role,"user.name":name}, function (result) {
        		if(result.isSuccess){
    				window.location.href = "/BookStore";
    				alert("注册成功!");
    			}else{
    				alert(result.msg);
    			}
    		})
        }
	});
})