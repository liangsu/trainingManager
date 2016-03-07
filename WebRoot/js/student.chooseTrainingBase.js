$(function(){
	//1.单选按钮点击事件
	$(":radio").click(function(){
		var val = $(this).val();
		if(val == 0){
			$(".zizhu").hide();
			$(".jizhong").show();
		}else if(val == 1){
			$(".zizhu").show();
			$(".jizhong").hide();
		}
	});
	//2.页面加载完成促发一次单选按钮点击事件
	$("input[name='trainingType']:checked").click();
});

//3.提交表单
function submitForm(){
	var areaForm = $("#areaForm");
	var url = areaForm.attr("action");
	var data = areaForm.serialize();
	console.log(data);
	
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