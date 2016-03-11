//搜索按钮 点击事件
$("#btn-search").click(function(){
	var gradeYear = $("#search-gradeYear").val();
	var keyword = $("#search-keyword").val();
	
	$("#pageForm > input[name='gradeYear']").val(gradeYear);
	$("#pageForm > input[name='keyword']").val(keyword);
	movePage();
});

/**
 * 删除
 * @param id
 */
function remove(id){
	if(!id){
		alert("删除信息的id不能为空");
		return;
	}
	
	var url = rootPath+"/student_delete.action";
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