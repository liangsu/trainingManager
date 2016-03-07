$(function(){
	//设置登陆类型
	var type = getCookie("type");
	if(!type){
		type = 0;
	}
	$("input[name='type'][value="+type+"]").attr("checked",true);
	//设置登陆用户
	var username = getCookie("username");
	if(username){
		$("#username").val(username);
	}
	//设置是否记住密码
	var remeber = getCookie("remeber");
	if(remeber){
		$("#remeber").attr("checked",true);
	}
});

/**
 * 登陆操作
 */
function login(){
	//1.获取数据
	var username = $("#username").val();
	var password = $("#password").val();
	var type = $("input[name='type']:checked").val();
	var remeber = $("#remeber").is(":checked");
	
	//2.校验数据
	console.log(remeber);
	if(!username){
		alert("用户名不能为空!");
		return ;
	}
	if(!password){
		alert("密码不能为空!");
		return ;
	}
	setCookie("type",type);
	if(remeber){
		setCookie("username",username);
		setCookie("remeber",remeber);
	}

	//3.封装数据
	var data = {
		username:username,
		password:password,
		type:type
	};
	
	//4.提交数据
	var url = rootPath + "/login_login.action"; 
	$.post(url,data,function(text){
		if(text == "ok"){
			window.location.href = rootPath + "/home_index.action";
		}else if(text == "error"){
			alert("登陆失败");
		}else{
			alert(text);
		}
	});
}

/**
 * 读取cookie
 */
function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

/**
 * 删除cookie
 */
function delCookie(name){
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null)
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

/**
 * 写cookie
 */
function setCookie(name,value){
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
