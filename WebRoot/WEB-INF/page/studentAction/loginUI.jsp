<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${rootPath }/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${rootPath }/js/loginUI.js"></script>
  </head>
  
  <body>
    <form action="${rootPath }" method="get">
		用户名：<input type="text" name="username" /><br/> 
		密&nbsp;码：<input type="password" name="password" /><br/>
		验证码：<input type="text" name="code" /><img id="codeImage" src="${rootPath }/validateImage.action" /><a id="btn_changeImage" href="javascript:;">点击图片切换验证码</a><br/>
		<input type="radio" name="type" value="student" checked="checked"/>学生<input type="radio" name="type" value="admin" />管理员<br/>
		<button id="btn_sub">提交</button>
    </form>
  </body>
</html>
