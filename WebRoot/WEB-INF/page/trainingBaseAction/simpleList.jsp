<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>基地查看</title>
<%@ include file="../public/css.jspf" %>
<link rel="stylesheet" href="${rootPath }/plugins/datatables/dataTables.bootstrap.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 实习基地 </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">实习基地管理</a></li>
        <li class="active">实习实训基地</li>
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
                	 	年份:&nbsp;
                				<select name="year" id="search-year">
                					<c:forEach begin="${beginYear }" end="${endYear }" var="curYear">
                						<option value="${curYear }" ${curYear eq year ? "selected='selected'" : "" }>${curYear }</option>
                					</c:forEach>
                				</select> 年,
                				关键字: &nbsp;<input type="text" id="search-keyword"  placeholder="名称" value="${keyword }" >&nbsp;
                				<button class="btn btn-primary btn-sm" type="submit" id="btn-search">搜索</button>
                	</div>
                </div>
                	
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <table class="table table-bordered table-striped table-hover dataTable" >
                      <thead>
                        <tr >
                          <th class="">基地名称</th>
                          <th class="">简要描述</th>
                          <th class="">联系人</th>
                          <th class="">联系电话</th>
                          <th class="">去年前往实习人数</th>
                        </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${pageBean.recordList}" var="tb">
                      	<tr role="row" class="event">
                      		<td><a href="trainingBase_detailInfo.action?id=${tb.id }">${tb.name }</a></td>
                      		<td>${tb.description }</td>
                      		<td>${tb.linkerName }</td>
                      		<td>${tb.linkerPhone }</td>
                      		<td>${tb.num }</td>
                      	</tr>
                      </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                <!-- 分页表单 -->
                <form id="pageForm" action="trainingBase_simpleList.action" method="post">
                	<input type="hidden" name="year" value="${year }">
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
  <!-- /.content-wrapper -->
  <!-- <div class="control-sidebar-bg"></div> -->
</div>
<%@ include file="../public/js.jspf" %>
<script type="text/javascript" src="${rootPath }/js/trainingBase.js"></script>
</body>
</html>
