<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/mytag" prefix="my" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	<my:aa cssClass="abc" href="abc">这是abc标签的内容</my:aa><br/>
	<my:aa cssClass="SKIP_PAGE" href="SKIP_PAGE">这是SKIP_PAGE标签的内容</my:aa><br/>
	<my:aa cssClass="SKIP_BODY" href="SKIP_BODY">这是SKIP_BODY标签的内容</my:aa><br/>
	<my:aa cssClass="EVAL_BODY_AGAIN" href="EVAL_BODY_AGAIN">这是EVAL_BODY_AGAIN标签的内容</my:aa><br/>
	<my:aa cssClass="EVAL_BODY_INCLUDE" href="EVAL_BODY_INCLUDE">这是EVAL_BODY_INCLUDE标签的内容</my:aa><br/>    
  </body>
</html>
