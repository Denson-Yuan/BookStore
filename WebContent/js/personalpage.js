


$(function(){
	
	var pageNum = 1;
	var pageSize = 8;
	$.post("order_findOrdersByUser.action",{"pageNum":pageNum,"pageSize":pageSize},function(result){
		if(result.isSuccess){
			
			var code = '<div class="panel panel-success">'+
			'<table class="table">'+
			'<thead><tr><td class="col-xs-1">图片</td>'+
					'<td class="col-xs-4">名称</td>'+
					'<td class="col-xs-2">数量</td>'+
					'<td class="col-xs-2">单价</td>'+
					'<td class="col-xs-2">小计</td>'+
					'<td class="col-xs-1">操作</td>'+
				'</tr></thead></table></div>';
			if(result.data.pageList.length==0){
				code = "你当前没有任何订单，快去购物吧~";
				$("#myorder").html(code);
			}
			else{
				
			
			for(var i=0;i<result.data.pageList.length;i++){
				var orderState="";
				if(result.data.pageList[i].orderState==1){
					orderState = "未完成";
				}else if(result.data.pageList[i].orderState==2){
					orderState = "已取消";
				}else{
					orderState = "已完成";
				}
				
				code += '<div class="panel panel-success">'+
				'<div class="panel-heading col-xs-12">'+
					'<label class="col-xs-3">下单时间：'+result.data.pageList[i].orderTime+'</label>'+ 
					'<label class="col-xs-3">订单号：'+result.data.pageList[i].id+'</label>'+ 
					'<label class="col-xs-2">状态：'+orderState+'</label>'+
					'<a title="查看详情" class="show-detail-btn glyphicon glyphicon-chevron-down" onclick="showDetail('+result.data.pageList[i].id +','+ i +')"></a>'+
				'</div><table class="table table-bordered order-detail'+i+'"></table></div>';
				$("#myorder").html(code);
			}
		}
		}
		
	});
	
	
	
	$("#myOrder-btn").click(function(){
		$("#myorder").css("display","block");
		$("#per_ziliao").css("display","none");
	});
	
	$("#myInfo-btn").click(function(){
		$("#myorder").css("display","none");
		$("#per_ziliao").css("display","block");
	});
})

function showDetail(id,i){
	$.post("order_findOrderDetailsByOrderId.action",{"orderId":id},function(result1){
		if(result1.isSuccess){
			var length = result1.data.length;
			var code1 = "";
			for(var k = 0;k < length;k++){
				code1 += '<tr><td class="col-xs-1"><img src="uploadimg/'+result1.data[k].book.imgPath+'" class="img-responsive" alt="" /></td>'+
				'<td class="col-xs-4">'+result1.data[k].book.name+'</td>'+
				'<td class="col-xs-2">'+result1.data[k].count+'</td>'+
				'<td class="col-xs-2">'+result1.data[k].book.price+'</td>'+
				'<td class="col-xs-2">'+(result1.data[k].book.price * result1.data[k].count)+'</td>'+
				'<td class="col-xs-1"><a class="btn btn-info">评价</a></td></tr>';
			}
		}
		$(".order-detail"+i).html(code1)
	});
}