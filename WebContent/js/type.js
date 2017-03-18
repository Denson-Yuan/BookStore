$(function() {
	var typeId = $("#typeId").val();
	//更新左侧分类列表内容
	$.post("book_getBookTypes.action",function(result){
		var code = "";
		for(var i=0;i<result.bookTypeList.length;i++){
			code += '<li><a href="book_setBookType.action?typeId='+result.bookTypeList[i].id+'">'+result.bookTypeList[i].typeName+'</a></li>';
		}
		$("#allTypeList").empty().append(code);
	});
	
	var pageNum = 1;
	var pageSize = 8;
	$.post("book_getBooksByType.action",{"typeId":typeId,"pageNum":pageNum,"pageSize":pageSize},function(result){
		var length = result.data.pageList.length;
		if(length <= 4){
			var code = "";
			for(i=0;i<length;i++){
				code += '<div class="grid1_of_4">'+
							'<div class="content_box" style="width: 100%">'+
								'<div class="book-img"><a href="book_bookDetail.action?bookId='+ result.data.pageList[i].id +'">'+
									'<img src="uploadimg/'+ result.data.pageList[i].imgPath +'" class="img-responsive" alt="" />'+
								'</a></div>'+
								'<h4><a href="book_bookDetail.action?bookId='+ result.data.pageList[i].id +'">'+result.data.pageList[i].name+'</a></h4>'+
								'<div class="grid_1 simpleCart_shelfItem">'+
									'<div class="item_add">'+
										'<span class="item_price"><h6>&yen'+ result.data.pageList[i].price +'</h6></span>'+
									'</div>'+
								'<div class="item_add">'+
									'<span class="item_price"><button class="btn btn-info" onclick="addToCart('+result.data.pageList[i].id+')">加入购物车</button></span>'+
								'</div>'+
							'</div>'+
						'</div></div>';
			}
			code += '<div class="clearfix"></div>';
			$("#first_grid").empty().append(code);
		}else{
			var code = "";
			for(i=0;i<4;i++){
				code += '<div class="grid1_of_4">'+
							'<div class="content_box" style="width: 100%">'+
								'<div class="book-img"><a href="book_bookDetail.action?bookId='+ result.data.pageList[i].id +'">'+
									'<img src="uploadimg/'+ result.data.pageList[i].imgPath +'" class="img-responsive" alt="" />'+
								'</a></div>'+
								'<h4><a href="book_bookDetail.action?bookId='+ result.data.pageList[i].id +'">'+result.data.pageList[i].name+'</a></h4>'+
								'<div class="grid_1 simpleCart_shelfItem">'+
									'<div class="item_add">'+
										'<span class="item_price"><h6>&yen'+ result.data.pageList[i].price +'</h6></span>'+
									'</div>'+
									'<div class="item_add">'+
										'<span class="item_price"><button class="btn btn-info" onclick="addToCart('+result.data.pageList[i].id+')">添加到购物车</button></span>'+
									'</div>'+
								'</div>'+
							'</div></div>';
			}
			code += '<div class="clearfix"></div>';
			$("#first_grid").empty().append(code);
			
			var code = "";
			for(i=4;i<length;i++){
				code += '<div class="grid1_of_4">'+
						'<div class="content_box" style="width: 100%">'+
							'<div class="book-img"><a href="book_bookDetail.action?bookId='+ result.data.pageList[i].id +'">'+
								'<img src="uploadimg/'+ result.data.pageList[i].imgPath +'" class="img-responsive" alt="" />'+
							'</a></div>'+
							'<h4><a href="book_bookDetail.action?bookId='+ result.data.pageList[i].id +'"> '+result.data.pageList[i].name+'</a></h4>'+
							'<div class="grid_1 simpleCart_shelfItem">'+
								'<div class="item_add">'+
									'<span class="item_price"><h6>&yen'+ result.data.pageList[i].price +'</h6></span>'+
								'</div>'+
								'<div class="item_add">'+
									'<span class="item_price"><button class="btn btn-info" onclick="addToCart('+result.data.pageList[i].id+')">添加到购物车</button></span>'+
								'</div>'+
							'</div>'+
						'</div></div>';
			}
			code += '<div class="clearfix"></div>';
			$("#second_grid").empty().append(code);
		}
	});
});

