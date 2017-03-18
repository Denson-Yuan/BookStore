$(function(){
	//显示顶部购物车数据
	updateHeadCart();
	
	//显示顶部类别列表中的数据
	$.post("book_getBookTypes.action",function(result){
		var typeNum = result.bookTypeList.length;
		var code = '<li class="active grid"><a class="color1" href="/BookStore">首页</a></li>';
		if(typeNum <= 9){
			for(var i=0;i<result.bookTypeList.length;i++){
				code += '<li><a class="color'+((i+2)%10+1)+'" href="book_setBookType.action?typeId='+result.bookTypeList[i].id+'">'+result.bookTypeList[i].typeName+'</a></li>'
			}
		}else{
			for(var i=0;i<9;i++){
				code += '<li><a class="color'+((i+2)%10+1)+'" href="book_setBookType.action?typeId='+result.bookTypeList[i].id+'">'+result.bookTypeList[i].typeName+'</a></li>'
			}
			code += '<li><a class="color2" href="book_setBookType.action?typeId=1">全部  >></a></li>';
		}
		$("#bookTypeList").empty().append(code);
	});
	
	//登录
	var emailPattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	$("#login").click(function(){
		var email = $("#email").val();
		var password = $("#password").val();
		var noProblem = true;

        if (email == "" || password == "") {
            $("#errorMsg").html("请将邮箱和密码填写完整！").css("display", "block");
            noProblem = false;
        }else if (!emailPattern.test(email)) {
            $("#errorMsg").html("邮箱格式不正确！").css("display", "block");
            noProblem = false;
        }
        
        if(noProblem){
        	$.post("user_login.action", {"user.email": email,"user.password":password}, function (result) {
    			if(result.isSuccess){
    				window.location.href = "/BookStore"
    			}else{
    				$("#errorMsg").html(result.msg).css("display","block");
    			}
    		})
        }
	});
})
//更新顶部购物车数据
function updateHeadCart(){
	$.post("cart_getTotalPriceAndNum.action",function(result){
		$(".simpleCart_total").html("&yen;"+result.totalPrice+".00");
		$("#simpleCart_quantity").html(result.totalNum);
		$(".total-price").html("合计： &yen;"+result.totalPrice+".00");
	});
}

function addToCart(bookId){
	$.post("cart_addToCart.action",{"bookId":bookId},function(result){
		alert(result.msg);
		//添加完成后更新顶部购物车数据
		updateHeadCart();
	});
}