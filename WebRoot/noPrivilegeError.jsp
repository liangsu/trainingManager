<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
    <TITLE>没有权限</TITLE>
    <%@include file="/WEB-INF/page/public/css.jspf" %>
</HEAD>   
<BODY>

<DIV ID="Title_bar">
    <DIV ID="Title_bar_Head">
        <DIV ID="Title_Head"></DIV>
        <DIV ID="Title"><!--页面标题-->
            <IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="${rootPath}/dist/img/title_arrow.gif"/> 提示
        </DIV>
        <DIV ID="Title_End"></DIV>
    </DIV>
</DIV>


<!--显示表单内容-->
<DIV ID="MainArea">
		<DIV CLASS="ItemBlock_Title1">
        </DIV> 
        
        <DIV CLASS="ItemBlockBorder" STYLE="margin-left: 15px;">
            <DIV CLASS="ItemBlock" STYLE="text-align: center; font-size: 16px;">
				出错了，您没有权限访问此功能！
            </DIV>
        </DIV>
        
        <!-- 操作 -->
        <DIV ID="InputDetailBar">
            <A HREF="javascript:history.go(-1);"><IMG SRC="${rootPath}/dist/img/goBack.png"/></A>
        </DIV>
</DIV>

</BODY>
</HTML>
