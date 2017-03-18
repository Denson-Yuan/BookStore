$(function(){
	showCartItems();
	$(".checkout-btn").click(function(){
		window.location.href = "cart_checkout.action";
	});
});

function deleteCartItem(cartItemId){
	$.post("cart_deleteCartItem.action",{"cartItemId":cartItemId},function(result){
		if(result.isSuccess){
			showCartItems();
			updateHeadCart();
		}else{
			alert(result.msg);
		}
	});
}

function showCartItems(){
	$.post("cart_getCartItem.action",function(result){
		if(result.isSuccess){
			var data = result.data;
			var code = "";
			if(data.length == 0){
				code = "您的购物车空空如也，快去购物吧~";
				$("#cart-main").html(code);
				$(".total-price,.checkout-btn").css("display","none");
			}
			else
			{
				for(var i=0;i<data.length;i++){
					code += '<tr><td><img src="uploadimg/'+data[i].book.imgPath+'" class="img-responsive" alt="" /></td>'+
						'<td style="padding-top:4%"><a href="#">'+data[i].book.name+'</a></td>'+
						'<td style="padding-top:4%">'+
							'<button class="glyphicon glyphicon-minus" onclick="minus('+i+','+data[i].id+')">'+
							'</button> <input class="bookNum bookNum'+i+'" value="'+data[i].amount+'" disabled/>'+
							' <button class="glyphicon glyphicon-plus" onclick="add('+i+','+data[i].book.amount+','+data[i].id+')"></button></td>'+
						'<td style="padding-top:4%">'+data[i].book.amount+'</td>'+
						'<td style="padding-top:4%">&yen;'+data[i].book.price+'.00</td>'+
						'<td style="padding-top:4%">&yen;'+(data[i].book.price * data[i].amount)+'.00</td>'+
						'<td style="padding-top:4%"><button class="btn btn-info" onclick="deleteCartItem('+data[i].id+')">删除</button></td></tr>';
						
				}
			}
			$("#cart_detail").html(code);
		}else{
			$("#cart-main").html(result.msg);
			$(".total-price,.checkout-btn").css("display","none");
		}
	});
}

function minus(i,id){
	var num = $(".bookNum"+i).val();
	if(num > 1){
		$(".bookNum"+i).val(num-1);
		updateCartItemNum(id,num-1);
	}
}

function add(i,amount,id){
	var num = $(".bookNum"+i).val();
	if(num < amount){
		$(".bookNum"+i).val(num-(-1));
		updateCartItemNum(id,num-(-1));
	}else{
		alert("库存不足");
	}
}

function updateCartItemNum(cartItemId,num){
	$.post("cart_updateCartItemNum.action",{"cartItemId":cartItemId,"num":num},function(result){
		if(result.isSuccess){
			showCartItems();
			updateHeadCart();
		}else{
			alert(result.msg);
		}
	});
}