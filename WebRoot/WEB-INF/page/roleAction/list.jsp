<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>角色信息</title>
<%@ include file="../public/css.jspf" %>
<link rel="stylesheet" href="${rootPath }/plugins/datatables/dataTables.bootstrap.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 角色信息 </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i> 权限管理</li>
        <li class="active">角色信息</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            
            <!-- /.box-header -->
            <div class="box-body">
              <div id="example2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                <div class="row">
                	<div class="col-sm-1 col-sm-offset-11">
                		<a href="javascript:void(0);" class="btn btn-primary btn-sm pull-right">添加角色</a>
                	</div>
                </div>
                	
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <table class="table table-bordered table-striped table-hover dataTable" >
                      <thead>
                        <tr >
                          <th>角色名称</th>
                          <th>操作</th>
                        </tr>
                      </thead>
                      <tbody>
                       <c:forEach items="${pageBean.recordList }" var="role">
                        <tr role="row" class="event">
                          <td class="sorting_1">${role.name }</td>
                          <td style="text-align:center;margin-left:0;">
                          	<a class="btn btn-default btn-xs" href="role_manageRoleUI.action?id=${role.id }"><i class="fa fa-user-plus"></i>管理权限</a>
                          	&nbsp;&nbsp;&nbsp;
                          	<a class="btn btn-default btn-xs" href="#"><i class="fa fa-edit"></i> 编辑</a>
                          	&nbsp;&nbsp;&nbsp;
                          	<a class="btn btn-default btn-xs" href=""><i class="fa fa-trash-o"></i> 删除</a>
                          </td>
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                
                <form id="pageForm" action="${rootPath }/student_list.action" method="post">
                </form>
                <%@ include file="../public/page.jspf" %>
                
              </div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
    </section>
  </div>
</div>
<%@ include file="../public/js.jspf" %>
<script type="text/javascript" src="${rootPath }/js/role.js"></script>
</body>
</html>
