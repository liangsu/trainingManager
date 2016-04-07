<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>学生详细信息</title>
<%@ include file="../public/css.jspf" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 学生详细信息  </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>个人中心</li>
        <li>学生中心</li>
        <li class="active">学生详细信息</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"></div>
        	
          <div class="box-body">
	        <form id="studentForm" class="form-horizontal" action="" method="post" onsubmit="return false;">
	          <input type="hidden" name="oldId" value="${model.id }">
	          <div class="box-body">
	            <div class="form-group">
	              <label for="name" class="col-sm-2 control-label">学号：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" id="id" name="id" value="${model.id }" placeholder="学生学号，如：12330401" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label for="address" class="col-sm-2 control-label">姓名：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" id="username" name="username" value="${model.username }" placeholder="学生姓名，如：张三" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label for="className" class="col-sm-2 control-label">班级：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" id="className" name="className" value="${model.className }" placeholder="学生所在班级,如：2012级软工班" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">地址：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" value="${model.address }" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">邮箱：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" value="${model.email }" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">联系电话：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" value="${model.phone }" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">银行卡号：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" value="${model.bank }" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">实习方式</label>
	              <div class="col-sm-2">
	                <label class="radio-inline">
					  <input type="radio" name="trainingType" value="0" ${model.trainingType eq 0 ? 'checked="checked"' : '' } > 集中实习
					</label>
	              </div>
	              <div class="col-sm-2">
	                <label class="radio-inline">
					  <input type="radio" name="trainingType" value="1" ${model.trainingType eq 1 ? 'checked="checked"' : '' } > 自主实习
					</label>
	              </div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">实习基地：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" value="${model.trainingType eq 0 ? model.trainingBase.name : (model.trainingType eq 1 ? model.freeTrainingBase.name : '') }" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">对基地评分</label>
	              <div class="col-sm-1">
	                <label class="radio-inline">
					  <input type="radio" name="estimate" value="0" ${model.estimate eq 50 ? 'checked="checked"' : '' } > 非常满意
					</label>
	              </div>
	              <div class="col-sm-1">
	                <label class="radio-inline">
					  <input type="radio" name="estimate" value="1" ${model.estimate eq 25 ? 'checked="checked"' : '' } > 满意
					</label>
	              </div>
	              <div class="col-sm-1">
	                <label class="radio-inline">
					  <input type="radio" name="estimate" value="1" ${model.estimate eq 0 ? 'checked="checked"' : '' } > 不满意
					</label>
	              </div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">实训心得：</label>
	              <div class="col-sm-4">
	                <textarea id="evaluate" name="evaluate" class="form-control" rows="6" maxlength="200" placeholder="实训心得..." readonly="readonly">${model.evaluate }</textarea>
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">应补金额：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" value="${model.subsidyMoney }元" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">实补金额：</label>
	              <div class="col-sm-4">
	                <input type="text" class="form-control" value="${model.actualMoney }元" readonly="readonly">
	              </div>
				  <div class="col-sm-2"></div>
	            </div>
	            <div class="form-group">
	              <label class="col-sm-2 control-label">允许登陆</label>
	              <div class="col-sm-2">
	                <label class="radio-inline">
					  <input type="radio" name="state" value="1" ${model.state eq 1 ? 'checked="checked"' : '' } > 允许登陆
					</label>
	              </div>
	              <div class="col-sm-2">
	                <label class="radio-inline">
					  <input type="radio" name="state" value="0" ${model.state eq 0 ? 'checked="checked"' : '' } > 禁止登陆
					</label>
	              </div>
	            </div>
	           </div>
	          </div>
	          <div class="box-footer">
			    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
				  <button onclick="javascript:history.go(-1);" class="btn btn-info pull-right">返回</button>
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
</body>
</html>