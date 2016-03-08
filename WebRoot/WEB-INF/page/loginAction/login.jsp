<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>乐山师范学院实习实训管理系统</title>
    <%@ include file="../public/css.jspf" %>
    <link rel="stylesheet" href="${rootPath }/plugins/iCheck/square/blue.css">
    <script type="text/javascript">
	 	// 在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
    </script>
  </head>
  <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="javascript:void(0);"><b>实习实训</b>管理系统</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">用户登陆</p>
        <form action="?" method="post" onsubmit="return false;">
          <div class="form-group has-feedback">
            <input type="text" id="username" class="form-control" placeholder="用户名">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" id="password" class="form-control" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
          	<div class="row">
	          	<div class="col-xs-8 pull-left">
	          	  <input type="radio" name="type" class="form-control" value="0">学生
	          	</div>
	          	<div class="col-xs-4">
	              <input type="radio" name="type" class="form-control" value="1">教师
	          	</div>
          	</div>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox" id="remeber"> 记住用户名
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button class="btn btn-primary btn-block btn-flat" onclick="login();">登录</button>
            </div><!-- /.col -->
          </div>
        </form>

        <div class="social-auth-links text-center">
          <p>- OR -</p>
          <a href="javascript:void(0);" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> 只能毕业生登陆</a>
        </div>

        <a href="javascript:void(0);">用户名是学号</a><br>
        <a href="javascript:void(0);" class="text-center">若密码丢失，请练习相关负责人</a>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

<%@ include file="../public/js.jspf" %>
<script src="${rootPath }/plugins/iCheck/icheck.min.js"></script>
<script src="${rootPath }/js/login.js"></script>
<script>
$(function () {
  $('input').iCheck({
    checkboxClass: 'icheckbox_square-blue',
    radioClass: 'iradio_square-blue',
    increaseArea: '20%' // optional
  });
});
</script>
  </body>
</html>
