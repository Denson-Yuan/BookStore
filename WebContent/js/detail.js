$(function(){
	
	$("#addToCart-btn").click(function(){
		var bookId = $("#bookId-detail").val();
		addToCart(bookId);
	});
});