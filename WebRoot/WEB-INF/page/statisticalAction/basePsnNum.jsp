<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>基地人数统计</title>
<%@ include file="../public/css.jspf" %>
<link rel="stylesheet" href="${rootPath }/plugins/datatables/dataTables.bootstrap.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 基地人数统计 </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i> 统计分析</li>
        <li class="active">基地人数统计</li>
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
                		<a href="${rootPath }/student_editUI.action" class="btn btn-primary btn-sm pull-right">导出EXCEL</a>
                	</div>
                </div>
                	
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <table class="table table-bordered table-striped table-hover dataTable" >
                      <thead>
                        <tr >
                          <th>基地名称</th>
                          <th>前往实习人数</th>
                          <th>学生评价总分</th>
                          <th>学生评价平均分</th>
                          <th>学生人数评价总分</th>
                          <th>基地综合评价总分</th>
                        </tr>
                      </thead>
                      <tbody>
                       <c:forEach items="${list }" var="map">
                        <tr role="row" class="event">
                          <td class="sorting_1">${map.name }</td>
                          <td>${map.psnNum }</td>
                          <td>${map.totalScore }</td>
                          <td>${map.avgScore }</td>
                          <td>${map.numScore }</td>
                          <td>${map.score }</td>
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                
                <form id="pageForm" action="${rootPath }/Statistical_basePsnNumUI.action" method="post">
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
</script>
</body>
</html>
