<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>密码修改</title>
<%@ include file="../public/css.jspf" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 密码修改  </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>系统设置</li>
        <li><a href="/student_list.action" target="main"> 修改密码</a></li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"></div>
        <form id="pwdForm" class="form-horizontal" action="${rootPath }/login_updatePwd.action" method="post" onsubmit="return false;return savePwd();">
          <div class="box-body">
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label">请出入现在的密码：</label>
              <div class="col-sm-4">
                <input type="password" class="form-control" id="oldPassword" name="oldPassword" value="" placeholder="" maxlength="16">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="address" class="col-sm-2 control-label">请输入新密码：</label>
              <div class="col-sm-4">
                <input type="password" class="form-control" id="newPassword" name="newPassword" value="" maxlength="16" placeholder="只能输入6-16位，包括字母、数字、“_”、“.”的字串">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="className" class="col-sm-2 control-label">请重新输入新密码：</label>
              <div class="col-sm-4">
                <input type="password" class="form-control" id="rePassword" name="rePassword" value="" maxlength="16" placeholder="只能输入6-16位，包括字母、数字、“_”、“.”的字串">
              </div>
			  <div class="col-sm-2"></div>
            </div>
           </div>
          </div>
          <div class="box-footer">
		    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
			  <button type="reset" class="btn btn-default">重置</button>
			  <button onclick="savePwd()" class="btn btn-info pull-right">保存</button>
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
function savePwd(){
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();	
	var rePassword = $("#rePassword").val();
	
	if(!oldPassword){
		alert("旧密码不能为空!");
		return false;
	}
	if(!newPassword){
		alert("新密码不能为空!");
		return false;
	}
	if(oldPassword && oldPassword == newPassword){
		alert("新旧密码是一样的！");
		return false;
	}
	if(!checkPassword(newPassword)){
		alert("新密码不合法");
		return false;
	}
	if(newPassword != rePassword){
		alert("前后密码不一致！");
		return false;
	}
	
	var areaForm = $("#pwdForm");
	var url = areaForm.attr("action");
	var data = areaForm.serialize();
	$.post(url,data,function(text){
		if(text == "ok"){
			alert("密码修改成功！请牢记密码");
			window.location.href = rootPath + "/login_loginUI.action";
		}else if(text == "error"){
			alert("密码修改失败!");
		}else{
			alert(text);
		}
	});
}
</script>
</body>
</html>
