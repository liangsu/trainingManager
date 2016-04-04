<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>消息页面</title>
<%@ include file="./WEB-INF/page/public/css.jspf" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
     <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>消息页面<small>提示信息</small></h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li class="active">消息</li>
          </ol>
        </section>

        <section class="content">
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">标题</h3>
              <div class="box-tools pull-right">
                <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse"><i class="fa fa-minus"></i></button>
                <button class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
              ${message }<c:if test="${not empty url}">系统将在<span id="time" style="color:blue;">5</span>秒后自动跳转，如果页面没有跳转<a href="${rootPath }${url }">点击这里</a></c:if>
            </div><!-- /.box-body -->
            <div class="box-footer">
              页尾
            </div><!-- /.box-footer-->
          </div><!-- /.box -->

        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
  </div>
  <!-- /.content-wrapper -->
  <div class="control-sidebar-bg"></div>
<!-- ./wrapper -->
<%@ include file="./WEB-INF/page/public/js.jspf" %>
<script type="text/javascript">
	var url = "${rootPath}${url}";
	delayURL();
    function delayURL() {
        var delay = document.getElementById("time").innerHTML;
 		var t = setTimeout("delayURL()", 1000);
        if (delay > 0) {
            delay--;
            document.getElementById("time").innerHTML = delay;
        } else {
     		clearTimeout(t); 
            window.location.href = url;
        }        
    }
</script>
</body>
</html>
