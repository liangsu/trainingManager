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
<!-- 弹出框 -->
<div class="modal modal-info fade" id="editMenuModal">
  <div class="modal-dialog" style="width:400px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h4 class="modal-title">实训补助设置</h4>
      </div>
      <div class="modal-body">
          <div class="box-body">
          	<div class="row">
	              <label class="col-sm-4 control-label">学号：</label>
	              <div class="col-sm-8">
	              <input type="text" class="form-control" id="id" name="id" readonly="readonly">
	              </div>
            </div>
            <div class="clearfix" style="margin-bottom:10px;"></div>
          	<div class="row">
	              <label for="name" class="col-sm-4 control-label">姓名：</label>
	              <div class="col-sm-8">
	              <input type="text" class="form-control" id="username" name="username" readonly="readonly">
	              </div>
            </div>
            <div class="clearfix" style="margin-bottom:10px;"></div>
          	<div class="row">
	              <label for="url" class="col-sm-4 control-label">班级：</label>
	              <div class="col-sm-8">
	              <input type="text" class="form-control" id="className" name="className" readonly="readonly">
	              </div>
            </div>
            <div class="clearfix" style="margin-bottom:10px;"></div>
          	<div class="row">
	              <label for="cssClass" class="col-sm-4 control-label">应补金额：</label>
	              <div class="col-sm-8">
	              <input type="text" class="form-control" id="subsidyMoney" name="subsidyMoney" placeholder="应该补助的金额，单位（元）">
	              </div>
            </div>
            <div class="clearfix" style="margin-bottom:10px;"></div>
          	<div class="row">
	              <label for="theOrder" class="col-sm-4 control-label">实补金额：</label>
	              <div class="col-sm-8">
	              <input type="text" class="form-control" id="actualMoney" name="actualMoney" placeholder="实际补助金额，单位（元）">
	              </div>
            </div>
         </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-outline" onclick="saveSubsidy()">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 实训补助 </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i> 学生管理</li>
        <li class="active">实训补助</li>
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
                          <th>应补金额</th>
                          <th>实补金额</th>
                          <th>操作</th>
                        </tr>
                      </thead>
                      <tbody>
                       <c:forEach items="${pageBean.recordList }" var="stu">
                        <tr role="row" class="event">
                          <td class="sorting_1">${stu.id }</td>
                          <td>${stu.username }</td>
                          <td>${stu.className }</td>
                          <td>${stu.subsidyMoney }元</td>
                          <td>${stu.actualMoney}元</td>
                          <td style="text-align:center;margin-left:0;">
                          	<a class="btn btn-default btn-xs" href="javascript:editSubsidy('${stu.id }','${stu.username }','${stu.className }','${stu.subsidyMoney }','${stu.actualMoney }');"><i class="fa fa-edit"></i> 补助设置</a>
                          </td>
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                
                <form id="pageForm" action="${rootPath }/student_subsidyList.action" method="post">
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
<script type="text/javascript">
//搜索按钮 点击事件
$("#btn-search").click(function(){
	var gradeYear = $("#search-gradeYear").val();
	var keyword = $("#search-keyword").val();
	
	$("#pageForm > input[name='gradeYear']").val(gradeYear);
	$("#pageForm > input[name='keyword']").val(keyword);
	movePage();
});

/**
 * 编辑、添加、添加子菜单 按钮的事件
 */
function editSubsidy(id,username,className,subsidyMoney,actualMoney){
	$("#id").val(id);
	$("#className").val(className);
	$("#username").val(username);
	$("#subsidyMoney").val(subsidyMoney);
	$("#actualMoney").val(actualMoney);
	$("#editMenuModal").modal('show');
}

/**
 * 保存修改、添加
 */
function saveSubsidy(){
	var id = $("#id").val();
	var subsidyMoney = $("#subsidyMoney").val();
	var actualMoney = $("#actualMoney").val();
	
	if(!isMoney(subsidyMoney)){
		alert("应补金额不合法！");
		return ;
	}
	if(!isMoney(actualMoney)){
		alert("实补金额不合法！");
		return ;
	}
	
	var data = {
		id:id,
		subsidyMoney:subsidyMoney,	
		actualMoney:actualMoney
	};
	
	console.log(data);
	
	$.post(rootPath + "/student_subsidyEdit.action",data,function(text){
		if(text == "ok"){
			window.location.reload();
		}else if(text == "error"){
			alert("保存失败！");
		}else{
			alert(text);
		}
	});
}
</script>
</body>
</html>
