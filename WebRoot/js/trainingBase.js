//搜索按钮 点击事件
$("#btn-search").click(function(){
	var year = $("#search-year").val();
	var keyword = $("#search-keyword").val();
	
	$("#pageForm > input[name='year']").val(year);
	$("#pageForm > input[name='keyword']").val(keyword);
	movePage();
});