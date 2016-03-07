//搜索按钮 点击事件
$("#btn-search").click(function(){
	var gradeYear = $("#search-gradeYear").val();
	var keyword = $("#search-keyword").val();
	
	$("#pageForm > input[name='gradeYear']").val(gradeYear);
	$("#pageForm > input[name='keyword']").val(keyword);
	movePage();
});