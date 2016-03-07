$(function(){
	
	/** 点击图片切换验证码 */
	$("#codeImage").click(function(){
		var url = $("#codeImage").attr("src");
		url = url + "?t="+ new Date().toString();
		$("#codeImage").attr("src",url);
		//alert(url);
	});
	
	/** 点击超链接切换图片 */
	$("#btn_changeImage").click(function(){
		var url = $("#codeImage").attr("src");
		url = url + "?t="+ new Date().toString();
		$("#codeImage").attr("src",url);
	});
	
	/** 表单提交 */
	$("#btn_sub").click(function(){
		$form = $("form");
		var actionUrl = $form.attr("action");
		var type = $("input[name=type]:checked").val();
		if(type == "student"){
			actionUrl = actionUrl + "/" + "student_login.action";
		}else{
			actionUrl = actionUrl + "/admin/" + "adminUser_login.action";
		}
		$form.attr("action",actionUrl);
//		alert("type:"+type + "\nactionUrl:"+actionUrl);
		$form.submit();
	});
	
});