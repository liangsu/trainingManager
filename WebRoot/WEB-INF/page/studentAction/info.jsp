<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>我的信息</title>
<%@ include file="../public/css.jspf" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 我的信息  </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>个人中心</li>
        <li class="active">我的信息</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"></div>
        <form id="areaForm" class="form-horizontal" action="${rootPath }/student_selfEdit.action" method="post">
        	
          <div class="box-body">
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label">学号：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="id" name="id" value="${info.id }" readonly="readonly" "学生学号，如：12330401">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="address" class="col-sm-2 control-label">姓名：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="username" name="username" value="${info.username }" readonly="readonly" placeholder="学生姓名，如：张三">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="grade" class="col-sm-2 control-label">年级：</label>
              <div class="col-sm-4">
                	<input type="text" class="form-control" id="grade" name="grade" value="${info.grade }" readonly="readonly" placeholder="学生姓名，如：张三">
              </div>
			  <div class="col-sm-2 notice">级</div>
            </div>
            <div class="form-group">
              <label for="className" class="col-sm-2 control-label">班级：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="className" name="className" value="${info.className }" readonly="readonly" placeholder="学生所在班级,如：2012级软工班">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="subsidyMoney" class="col-sm-2 control-label">实习应补金额：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="subsidyMoney" name="subsidyMoney" value="${info.subsidyMoney }" readonly="readonly" placeholder="学生所在班级,如：2012级软工班">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="actualMoney" class="col-sm-2 control-label">实习实补金额：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="actualMoney" name="actualMoney" value="${info.actualMoney }" readonly="readonly" placeholder="学生所在班级,如：2012级软工班">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="phone" class="col-sm-2 control-label">联系电话：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="phone" name="phone" value="${info.phone }" placeholder="练习电话，如：15520900123">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="email" class="col-sm-2 control-label">邮箱：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="email" name="email" value="${info.email }" placeholder="邮箱地址">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="bank" class="col-sm-2 control-label">银行卡号：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="bank" name="bank" value="${info.bank }" placeholder="银行卡号，用于发放实习补助金额">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="address" class="col-sm-2 control-label">地址：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="address" name="address" value="${info.address }" placeholder="家庭地址">
              </div>
			  <div class="col-sm-2"></div>
            </div>
           </div>
          </div>
          <div class="box-footer">
		    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
			  <button type="reset" class="btn btn-default">重置</button>
			  <button type="button" onclick="saveInfo()" class="btn btn-info pull-right">保存</button>
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
<script type="text/javascript">
function saveInfo(){
	var areaForm = $("#areaForm");
	var url = areaForm.attr("action");
	var data = areaForm.serialize();
	
	$.post(url,data,function(text){
		if(text == "ok"){
			window.location.reload();
		}else if(text == "error"){
			alert("保存失败!");
		}else{
			alert(text);
		}
	});
}
</script>
</body>
</html>