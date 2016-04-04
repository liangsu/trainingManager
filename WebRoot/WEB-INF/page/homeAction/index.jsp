<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <title>实习实训管理系统</title>
    <%@ include file="../public/css.jspf" %>
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

      <header class="main-header">

        <!-- Logo -->
        <a href="body.html"  target="main" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>实习</b></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>实习实训管理系统</b></span>
        </a>

        <!-- 导航栏 -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- 消息: style can be found in dropdown.less-->
              <%@ include file="./xiaoxi.jspf" %>
              <!-- 通知: style can be found in dropdown.less -->
              <%@ include file="./tongzhi.jspf" %>
              <!-- 任务: style can be found in dropdown.less -->
              <%@ include file="./task.jspf" %>
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                  <span class="hidden-xs">${user.username }</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                    <p>${user.username } - ${user.userType }
                      <small><fmt:formatDate value="${user.time }" pattern="yyyy-MM-DD HH:mm:ss"/></small>
                    </p>
                  </li>
                  <!-- Menu Body -->
                  <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="#">粉丝</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">关注</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">好友</a>
                    </div>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">个人中心</a>
                    </div>
                    <div class="pull-right">
                      <a href="${rootPath }/login_logout.action" class="btn btn-default btn-flat">退出</a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div>

        </nav>
      </header>
      <!-- 左侧的菜单栏 -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>${user.username }</p>
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>
          <!-- search form -->
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form>
          <!-- /.search form -->
          <!-- 菜单模块 -->
          <ul class="sidebar-menu">
            <li class="header">功能菜单</li>
            <c:forEach items="${menus }" var="menu">
              <li class="treeview">
                <a href="#"><i class="${menu.cssClass }"></i> <span>${menu.name }</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
              	<c:forEach items="${menu.children }" var="child">
              	  <li ><a href="${rootPath }${child.url }" target="main"><i class="${child.cssClass }"></i>${child.name }</a></li>
              	</c:forEach>
              </ul>
            </li>
            </c:forEach>
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <iframe id="main" name="main" src="message.jsp" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" width="100%" height="550" onload="setIframeHeight();"></iframe>
      </div><!-- /.content-wrapper -->

	  <!-- 页脚 -->
      <footer class="main-footer navbar-fixed-bottom">
        <div class="pull-right hidden-xs">
          <b>Version</b> 1.1.0
        </div>
        <strong>Copyright &copy; 2015-2016 <a href="javascript:void(0);">trainingmanager</a>.</strong> All rights reserved.
      </footer>

      <!-- Control Sidebar -->
	   <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
          <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
          <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
          <!-- Home tab content -->
          <div class="tab-pane" id="control-sidebar-home-tab">
            <h3 class="control-sidebar-heading">最近的活动</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-birthday-cake bg-red"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">德玛西亚的生日</h4>
                    <p>将在2015年10月20日</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-user bg-yellow"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">流浪的技能改版了</h4>
                    <p>Q技能伤害提高了20%</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">我的邮箱</h4>
                    <p>1129371784@qq.com</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-file-code-o bg-green"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">执行次数</h4>
                    <p>执行时间：5秒</p>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

            <h3 class="control-sidebar-heading">工作任务进度</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    实习实训基地填报情况
                    <span class="label label-danger pull-right">70%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    简历完善度
                    <span class="label label-success pull-right">95%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-success" style="width: 95%"></div>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    学生补助发放比例
                    <span class="label label-warning pull-right">50%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    拉动司法交流看法
                    <span class="label label-primary pull-right">68%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

          </div><!-- /.tab-pane -->

          <!-- Settings tab content -->
          <div class="tab-pane" id="control-sidebar-settings-tab">
            <form method="post">
              <h3 class="control-sidebar-heading">设置中心</h3>
              <div class="form-group">
                <label class="control-sidebar-subheading">
                  报表面板使用
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                 普通设置的信息
                </p>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  允许转发的邮件
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  其它可选的设置
                </p>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                 揭露作者姓名
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  允许作者在博客中显示姓名
                </p>
              </div><!-- /.form-group -->

              <h3 class="control-sidebar-heading">聊天设置</h3>

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  显示我的在线状态
                  <input type="checkbox" class="pull-right" checked>
                </label>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  切换为离线状态
                  <input type="checkbox" class="pull-right">
                </label>
              </div><!-- /.form-group -->

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  删除聊天历史
                  <a href="javascript::;" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
                </label>
              </div><!-- /.form-group -->
            </form>
          </div><!-- /.tab-pane -->
        </div>
      </aside><!-- /.control-sidebar -->
     
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>

    </div><!-- ./wrapper -->

    <%@ include file="../public/js.jspf" %>
	
	<script type="text/javascript">
		function setIframeHeight() {
			var iframe = document.getElementById('main');
			if (iframe) {
				var bHeight = document.body.scrollHeight;
				var subWeb = document.frames ? document.frames["main"].document : iframe.contentDocument;
				console.log(subWeb);
				if(subWeb){
					console.log("iframe height:"+subWeb.body.scrollHeight);
					iframe.height = subWeb.body.scrollHeight ;
				}else{
					iframe.height = bHeight;
				}
				
				//var height = document.documentElement.clientHeight - 50;
			}
		};
		//window.setInterval("setIframeHeight()",2000);
	</script>
  </body>
</html>
