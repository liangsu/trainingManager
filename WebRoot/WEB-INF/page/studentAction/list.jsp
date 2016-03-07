<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>学生中心</title>
<%@ include file="../public/css.jspf" %>
<link rel="stylesheet" href="${rootPath }/plugins/datatables/dataTables.bootstrap.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 学生信息 </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i> 学生管理</li>
        <li class="active">学生中心</li>
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
                  <div class="col-sm-6"></div>
                  <div class="col-sm-6"></div>
                </div>
                <div class="row">
                	 <div class="col-sm-6">
                	 	年级:&nbsp;
                				<select id="search-gradeYear" name="search-gradeYear" >
                					<option value="2015" ${gradeYear eq 2015 ? 'selected="selected"' : '' }>2015</option>
                					<option value="2016" ${gradeYear eq 2016 ? 'selected="selected"' : '' }>2016</option>
                				</select> 级,
                				关键字: &nbsp;<input id="search-keyword" type="text" value="${keyword }" placeholder="姓名" >&nbsp;
                				<button class="btn btn-primary btn-sm" id="btn-search">搜索</button>
                	</div>
                	<div class="col-sm-6">
                		<a href="student_editUI.action" class="btn btn-primary btn-sm pull-right">添加学生</a>
                	</div>
                </div>
                	
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <table class="table table-bordered table-striped table-hover dataTable" >
                      <thead>
                        <tr >
                          <th>学号</th>
                          <th>姓名</th>
                          <th>班级</th>
                          <th>联系方式</th>
                          <th>实习基地</th>
                          <th>操作</th>
                        </tr>
                      </thead>
                      <tbody>
                       <c:forEach items="${pageBean.recordList }" var="stu">
                        <tr role="row" class="event">
                          <td class="sorting_1">${stu.id }</td>
                          <td>${stu.username }</td>
                          <td>${stu.className }</td>
                          <td>${stu.phone }</td>
                          <td>${stu.trainingBase.name }</td>
                          <td style="text-align:center;margin-left:0;">
                          	<a class="btn btn-default btn-xs" href="${rootPath }/student_editUI.action?id=${stu.id }"><i class="fa fa-edit"></i> 编辑</a>
                          	&nbsp;&nbsp;&nbsp;
                          	<a class="btn btn-default btn-xs" href="${rootPath }/student_delete.action?id=${stu.id }"><i class="fa fa-trash-o"></i> 删除</a>
                          </td>
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                
                <form id="pageForm" action="${rootPath }/student_list.action" method="post">
                	<input type="hidden" name="gradeYear" value="${gradeYear }">
                	<input type="hidden" name="keyword" value="${keyword }">
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
<script type="text/javascript" src="${rootPath }/js/student.js"></script>
</body>
</html>
