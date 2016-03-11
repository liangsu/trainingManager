<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>添加学生</title>
<%@ include file="../public/css.jspf" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> ${model.id gt 0 ? "修改" : "添加" }学生信息  </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>学生管理</li>
        <li><a href="/student_list.action" target="main"> 学生中心</a></li>
        <li class="active">${model.id gt 0 ? "修改" : "添加" }学生信息</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"></div>
        <form id="studentForm" class="form-horizontal" action="${rootPath }/student_edit.action" method="post" onsubmit="return false;">
          <input type="hidden" name="oldId" value="${model.id }">
          <div class="box-body">
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label">学号：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="id" name="id" value="${model.id }" placeholder="学生学号，如：12330401">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="address" class="col-sm-2 control-label">姓名：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="username" name="username" value="${model.username }" placeholder="学生姓名，如：张三">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <%-- <div class="form-group">
              <label for="grade" class="col-sm-2 control-label">年级：</label>
              <div class="col-sm-4">
                <select class="" name="grade" id="grade">
                	<option value="2015" ${model.grade eq 2015 ? "selected" : "" }>2015</option>
                	<option value="2016" ${model.grade eq 2016 ? "selected" : "" }>2016</option>
                </select> &nbsp;级
              </div>
			  <div class="col-sm-2"></div>
            </div> --%>
            <div class="form-group">
              <label for="className" class="col-sm-2 control-label">班级：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="className" name="className" value="${model.className }" placeholder="学生所在班级,如：2012级软工班">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">允许登陆</label>
              <div class="col-sm-2">
                <label class="radio-inline">
				  <input type="radio" name="state" value="1" ${model.state eq 1 ? 'checked="checked"' : '' }> 允许登陆
				</label>
              </div>
              <div class="col-sm-2">
                <label class="radio-inline">
				  <input type="radio" name="state" value="0" ${model eq null ? 'checked="checked"' : (model.id eq 0 ? 'checked="checked"' : '')  } > 禁止登陆
				</label>
              </div>
            </div>
           </div>
          </div>
          <div class="box-footer">
		    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
			  <button type="reset" class="btn btn-default">重置</button>
			  <button onclick="submitStudentForm();" class="btn btn-info pull-right">保存</button>
			</div>
          </div>
        </form>
      </div>
    </section>
  </div>
  <!-- /.content-wrapper -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<%@ include file="../public/js.jspf" %>
<script src="${rootPath }/js/student.edit.js"></script>
</body>
</html>
