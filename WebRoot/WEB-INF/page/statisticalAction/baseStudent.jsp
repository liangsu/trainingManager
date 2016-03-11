<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>基地学生统计</title>
<%@ include file="../public/css.jspf" %>
<link rel="stylesheet" href="${rootPath }/plugins/datatables/dataTables.bootstrap.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 基地学生统计 </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i> 统计分析</li>
        <li class="active">基地学生统计</li>
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
                				<select id="search-grade" name="search-grade" >
                					<option value="2015" ${grade eq 2015 ? 'selected="selected"' : '' }>2015</option>
                					<option value="2016" ${grade eq 2016 ? 'selected="selected"' : '' }>2016</option>
                				</select> 级
                				<button class="btn btn-primary btn-sm" id="btn-search">查询</button>
                	</div>
                	<div class="col-sm-6">
                		<button class="btn btn-primary btn-sm pull-right" id="btn-excel">导出EXCEL</button>
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
                          <th>住址</th>
                          <th>电话</th>
                          <th>实习基地</th>
                          <th>实习地址</th>
                        </tr>
                      </thead>
                      <tbody>
                       <c:forEach items="${pageBean.recordList }" var="map">
                        <tr role="row" class="event">
                          <td class="sorting_1">${map.id }</td>
                          <td>${map.username }</td>
                          <td>${map.classname }</td>
                          <td>${map.address }</td>
                          <td>${map.phone }</td>
                          <td>${map.tName }</td>
                          <td>${map.tAddress }</td>
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                
                <form id="pageForm" action="${rootPath }/Statistical_baseStudentUI.action" method="post">
                	<input type="hidden" name="grade" value="${grade }">
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
$("#btn-search").click(function(){
	var grade = $("#search-grade").val();
	$("#pageForm > input[name='grade']").val(grade);
	
	$("#pageForm").submit();
});

$("#btn-excel").click(function(){
	var grade = $("#search-grade").val();
	var url = "${rootPath }/Statistical_baseStudentExcel.action?grade="+grade;
	window.open(url);
});
</script>
</body>
</html>
