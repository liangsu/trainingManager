//搜索按钮 点击事件
$("#btn-search").click(function(){
	var year = $("#search-year").val();
	var keyword = $("#search-keyword").val();
	
	$("#pageForm > input[name='year']").val(year);
	$("#pageForm > input[name='keyword']").val(keyword);
	movePage();
});

function remove(id){
	if(!id){
		alert("删除信息的id不能为空");
		return;
	}
	
	var url = rootPath+"/trainingBase_delete.action";
	var data = {id:id};
	$.post(url,data,function(text){
		if(text == "ok"){
			location.reload();
		}else if(text == "error"){
			alert("保存失败!");
		}else{
			alert(text);
		}
	});
}