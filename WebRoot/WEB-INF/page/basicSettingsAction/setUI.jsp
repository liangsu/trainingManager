<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>系统配置</title>
<%@ include file="../public/css.jspf" %>
<link rel="stylesheet" href="${rootPath }/plugins/datepicker/datepicker3.css" type="text/css"></link>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 系统配置  </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>系统设置</li>
        <li class="active">系统配置</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"></div>
        <form id="setForm" class="form-horizontal" action="${rootPath }/basicSettings_set.action" onsubmit="submitForm();return false;" method="post">
        	
          <div class="box-body">
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label">实习实训开始时间：</label>
              <div class="col-sm-4">
              	<div class="input-group">
                      <div class="input-group-addon" id="startDateBtn">
                        <i class="fa fa-calendar"></i>
                      </div>
                      <input type="text" class="form-control pull-right active" id="startDate" name="startDate" value="${map.startDate }" readonly="readonly">
                 </div>
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="address" class="col-sm-2 control-label">实习实训结束时间：</label>
              <div class="col-sm-4">
	              <div class="input-group">
	                      <div class="input-group-addon" id="endDateBtn">
	                        <i class="fa fa-calendar"></i>
	                      </div>
	                      <input type="text" class="form-control pull-right active" id="endDate" name="endDate" value="${map.endDate }" readonly="readonly">
	               </div>
               </div>
            </div>
            <%-- <div class="form-group">
              <label for="address" class="col-sm-2 control-label">今年级别：</label>
              <div class="col-sm-4">
	              <div class="input-group">
	                      <div class="input-group-addon">
	                        <i class="fa fa-calendar"></i>
	                      </div>
	                      <input type="text" class="form-control pull-right active" id="grade" name="endDate" value="${map.grade }" readonly="readonly">
	               </div>
               </div>
            </div> --%>
            <div class="form-group">
              <label for="address" class="col-sm-2 control-label">学生评价时间：</label>
              <div class="col-sm-4">
	              <div class="input-group">
	                      <div class="input-group-addon" id="evaluateDateBtn">
	                        <i class="fa fa-calendar"></i>
	                      </div>
	                      <input type="text" class="form-control pull-right active" id="evaluateDate" name="evaluateDate" value="${map.evaluateDate }" readonly="readonly">
	               </div>
               </div>
            </div>
            
           </div>
          </div>
          <div class="box-footer">
		    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
			  <button type="reset" class="btn btn-default">重置</button>
			  <button type="submit" class="btn btn-info pull-right">保存</button>
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
<script type="text/javascript" src="${rootPath }/plugins/datepicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${rootPath }/js/basicSettings.js"></script>
<script type="text/javascript">

</script>
</body>
</html>