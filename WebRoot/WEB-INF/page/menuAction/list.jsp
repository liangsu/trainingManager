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
        <h4 class="modal-title">编辑菜单</h4>
      </div>
      <div class="modal-body">
          <div class="box-body">
          	<input type="hidden" name="id" id="id" value="" />
          	<div class="row">
	              <label class="col-sm-4 control-label">上级菜单：</label>
	              <div class="col-sm-8">
	                <select name="pid" id="pid" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                      <option value="0">顶级菜单</option>
                      <c:forEach items="${menuTree }" var="menu">
                        <option value="${menu.id }">┗━${menu.name }</option>
                        <c:forEach items="${menu.children }" var="subMenu">
                       	<option value="${subMenu.id }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;┗━${subMenu.name }</option>
                        </c:forEach>
                      </c:forEach>
					</select>
	              </div>
            </div>
            <div class="clearfix" style="margin-bottom:10px;"></div>
          	<div class="row">
	              <label for="name" class="col-sm-4 control-label">菜单名称：</label>
	              <div class="col-sm-8">
	              <input type="text" class="form-control" id="name" name="name" placeholder="菜单名称，如：学生中心">
	              </div>
            </div>
            <div class="clearfix" style="margin-bottom:10px;"></div>
          	<div class="row">
	              <label for="url" class="col-sm-4 control-label">URL地址：</label>
	              <div class="col-sm-8">
	              <input type="text" class="form-control" id="url" name="url" placeholder="URL地址，如：student_list.action">
	              </div>
            </div>
            <div class="clearfix" style="margin-bottom:10px;"></div>
          	<div class="row">
	              <label for="cssClass" class="col-sm-4 control-label">菜单图标CSS：</label>
	              <div class="col-sm-8">
	              <input type="text" class="form-control" id="cssClass" name="cssClass" placeholder="菜单图标css，如：fa fa-circle-o">
	              </div>
            </div>
            <div class="clearfix" style="margin-bottom:10px;"></div>
          	<div class="row">
	              <label for="theOrder" class="col-sm-4 control-label">排序：</label>
	              <div class="col-sm-8">
	              <input type="text" class="form-control" id="theOrder" name="theOrder" placeholder="菜单显示顺序，不填则自增">
	              </div>
            </div>
         </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-outline" onclick="saveMenu()">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 菜单信息 </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i> 权限管理</li>
        <li class="active">菜单信息</li>
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
                	<div class="col-sm-1 col-sm-offset-11">
                		<a href="javascript:void(0);" class="btn btn-primary btn-sm pull-right" data-toggle="modal" data-target="#editMenuModal">添加菜单</a>
                	</div>
                </div>
                	
                </div>
                <div class="row">
                  <div class="col-sm-12">
                    <table class="table table-bordered table-hover dataTable table-condensed" >
                      <thead>
                        <tr>
                          <th>菜单名称</th>
                          <th>上级菜单</th>
                          <th>url地址</th>
                          <th>菜单图标css</th>
                          <th>排序</th>
                          <th style="text-align: center;">操&nbsp;&nbsp;&nbsp;&nbsp;作</th>
                        </tr>
                      </thead>
                      <tbody>
                       <c:forEach items="${menuTree }" var="menu">
                        <tr role="row" class="event info">
                          <td class="sorting_1">${menu.name }</td>
                          <td></td>
                          <td>${menu.url }</td>
                          <td>${menu.cssClass }</td>
                          <td>${menu.theOrder }</td>
                          <td>
                          	<a class="btn btn-default btn-xs" href="javascript:editMenu('','${menu.id }','','','','');"><i class="fa fa-plus"></i> 添加子菜单</a>&nbsp;&nbsp;&nbsp;
                          	<a class="btn btn-default btn-xs" href="javascript:editMenu('${menu.id }','${menu.pid }','${menu.name }','${menu.url }','${menu.cssClass }','${menu.theOrder }');"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;
                          	<a class="btn btn-default btn-xs" href="javascript:delMenu('${menu.id }');"><i class="fa fa-trash-o"></i> 删除</a>
                          </td>
                        </tr>
                          <c:forEach items="${menu.children }" var="subMenu">
                          <tr role="row" class="event">
                            <td class="sorting_1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;┝━${subMenu.name }</td>
                            <td>${menu.name }</td>
                            <td>${subMenu.url }</td>
                            <td>${subMenu.cssClass }</td>
                            <td>${subMenu.theOrder }</td>
                            <td style="text-align:center;margin-left:0;">
                          	  <a class="btn btn-default btn-xs" href="javascript:editMenu('${subMenu.id }','${subMenu.pid }','${subMenu.name }','${subMenu.url }','${subMenu.cssClass }','${subMenu.theOrder }');"><i class="fa fa-edit"></i> 编辑</a>
                          	  &nbsp;&nbsp;&nbsp;
                          	  <a class="btn btn-default btn-xs" href="javascript:delMenu('${subMenu.id }');"><i class="fa fa-trash-o"></i> 删除</a>
                            </td>
                          </tr>
                          </c:forEach>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <div class="box-footer">
              	<h6>菜单说明：</h6>
              	<p style="font-size:12px;">1.这里的菜单对应于左边的菜单；<br>
              	2.有子菜单的菜单不能删除；<br>
              	3.分配给某个角色的菜单不能删除。
              	</p>
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
<script type="text/javascript" src="${rootPath }/js/menu.js"></script>
</body>
</html>
