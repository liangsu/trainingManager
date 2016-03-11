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
      <h1> 评价基地  </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>个人中心</li>
        <li class="active">评价基地</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"></div>
        <form id="evaluateForm" class="form-horizontal" action="${rootPath }/student_evaluate.action" method="post" onsubmit="return false;">
          <div class="box-body">
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label">你的学号：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" id="id" name="id" value="${info.id }" readonly="readonly" "学生学号，如：12330401">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="address" class="col-sm-2 control-label">你的实训基地：</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" value="${info.trainingType eq 0 ? info.trainingBase.name : (info.trainingType eq 1 ? info.freeTrainingBase.name : '') }" readonly="readonly" >
              </div>
			  <div class="col-sm-2"></div>
            </div>
            
            <div class="form-group">
              <label class="col-sm-2 control-label">满意程度</label>
              <div class="col-sm-2">
                <label class="radio-inline">
				  <input type="radio" name="estimate" value="50" ${empty info.estimate ? 'checked="checked"' : info.estimate eq 50 ? 'checked="checked"' : '' } > 非常满意
				</label>
              </div>
              <div class="col-sm-2">
                <label class="radio-inline">
				  <input type="radio" name="estimate" value="25" ${info.estimate eq 25 ? 'checked="checked"' : '' }> 满意
				</label>
              </div>
              <div class="col-sm-2">
                <label class="radio-inline">
				  <input type="radio" name="estimate" value="0" ${info.estimate eq 0 ? 'checked="checked"' : '' }> 不满意
				</label>
              </div>
            </div>
            <div class="form-group">
              <label for="evaluate" class="col-sm-2 control-label">实训心得</label>
              <div class="col-sm-6">
                <textarea id="evaluate" name="evaluate" class="form-control" rows="6" maxlength="200" placeholder="实训心得...">${info.evaluate }</textarea>
              </div>
			  <div class="col-sm-2">200字以内</div>
            </div>
            
            
            
           </div>
          </div>
          <div class="box-footer">
		    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
			  <button type="reset" class="btn btn-default">重置</button>
			  <button onclick="submitForm();" class="btn btn-info pull-right">保存</button>
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
function submitForm(){
	var areaForm = $("#evaluateForm");
	var url = areaForm.attr("action");
	var data = areaForm.serialize();
	console.log(data);
	
	$.post(url,data,function(text){
		if(text == "ok"){
			window.location.href = rootPath + "/student_evaluateUI.action";
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