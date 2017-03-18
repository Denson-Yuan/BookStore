$(function(){
	
	$.post("cart_getCartItem.action",function(result){
		if(result.isSuccess){
			var data = result.data;
			var code = "";
			if(data.length == 0){
				code = "您没有任何订单";
				$(".placeorder-main").html(code);
				$(".submit-btn").css("display","none");
			}
			else
			{
				var total = 0;
				for(var i=0;i<data.length;i++){
					var subtotal = data[i].book.price * data[i].amount;
					total += subtotal;
					code += '<tr><td><img src="uploadimg/'+data[i].book.imgPath+'" class="img-responsive"/></td>'+
								'<td style="padding-top:4%">'+data[i].book.name+'</td>'+
								'<td style="padding-top:4%">'+data[i].amount+'</td>'+
								'<td style="padding-top:4%">&yen;'+data[i].book.price+'</td>'+
								'<td style="padding-top:4%">&yen;'+subtotal+'</td></tr>';
				}
				code += '<tr><td colspan="5">'+
							'<label class="total">合计：&yen;'+total+'.00</label>'+
						'</td></tr>';
				$("#order-detail").html(code);
			}
		}else{
			$("#placeorder-main").html("登录已超时，请重新登录.");
			$(".submit-btn").css("display","none");
		}
	});
	
	$.post("order_getUserInfo.action",function(result){
		if(result.isSuccess){
			$("#reciver").val(result.user.name);
			$("#phone").val(result.user.phone);
			$("#address").val(result.user.address1);
		}
	});
	
	$("#btn-submit-order").click(function(){
		var reciver = $("#reciver").val();
		var phone = $("#phone").val();
		var address = $("#address").val();
		var remark = $("#remark").val();
		$.post("order_submit.action",{"reciver":reciver,"phone":phone,"address":address,"remark":remark},function(result){
			alert(result.msg);
			window.location.href="user_personal.action";
		});
	});
});