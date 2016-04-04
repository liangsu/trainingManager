/**
 * 提交学生表单
 */
function submitStudentForm(){
	var areaForm = $("#studentForm");
	var url = areaForm.attr("action");
	var data = areaForm.serialize();
	
	$.post(url,data,function(text){
		if(text == "ok"){
			window.location.href = rootPath + "/student_list.action";
		}else if(text == "error"){
			alert("保存失败!");
		}else{
			alert(text);
		}
	});
}