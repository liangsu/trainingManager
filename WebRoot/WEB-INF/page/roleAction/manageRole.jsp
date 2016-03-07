<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>管理角色</title>
<%@ include file="../public/css.jspf" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<form action="${rootPath }/role_manageRole.action" method="post">
<input type="hidden" name="id" value="${model.id }">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1>角色管理 </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>权限管理</li>
        <li><a href="/role_list.action" target="main"> 角色信息</a></li>
        <li class="active">角色管理</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"> ${model.name }管理${menuList.size() }</div>
          <div class="box-body">
             <ul>
             	<c:forEach items="${menuTree }" var="first">
             		<li>
             		  <input type="checkbox" name="mid" value="${first.id }" ${model.menus.contains(first) ? 'checked="checked"' : '' }/>${first.name }
             		  <ul>
             		    <c:forEach items="${first.children }" var="second">
             			<li>
             			  <input type="checkbox" name="mid" value="${second.id }" ${model.menus.contains(second) ? 'checked="checked"' : '' }/>${second.name }
             			  <c:forEach items="${second.children }" var="third">
             			  <li><input type="checkbox" name="mid" value="${third.id }" ${model.menus.contains(third) ? 'checked="checked"' : '' }/>${third.name }</li>
             			  </c:forEach>
             			</li>
             		    </c:forEach>
             		  </ul>
             		</li>
             	</c:forEach>
             </ul>
           </div>
          </div>
          <div class="box-footer">
		    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
			  <button type="reset" class="btn btn-default">重置</button>
			  <button type="submit" class="btn btn-info pull-right">保存</button>
			</div>
          </div>
      </div>
    </section>
</div>
</form>
<!-- /.content-wrapper -->
<div class="control-sidebar-bg"></div>

<%@ include file="../public/js.jspf" %>
</body>
</html>