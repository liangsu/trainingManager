<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>实习基地</title>
<%@include file="../public/css.jspf" %>
<link rel="stylesheet" href="webuploader/webuploader.css" type="text/css">
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div style="position:absolute;z-index:9999;top:90px;left:750px;width:250px;height:200px;">
 <img id="ImgPr" width="200px" height="200px" src="${model.img }" />
</div>

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 实习基地  </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">实习基地管理</a></li>
        <li class="active">实习实训基地</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"></div>
        <form id="areaForm" class="form-horizontal" action="" method="post" onsubmit="return false;">
          <input type="hidden" name="id" id="id" value="${model.id }">
          <input type="hidden" name="img" id="img" value="${model.img }">
          <div class="box-body">
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label">基地名称</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" value="${model.name }" readonly="readonly">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="address" class="col-sm-2 control-label">基地地址</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="address" name="address" value="${model.address }" placeholder="基地地址" readonly="readonly">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            </div>
			<div class="form-group">
              <label for="description" class="col-sm-2 control-label">基地简要描述</label>
              <div class="col-sm-4">
                <textarea id="description" name="description" class="form-control" rows="4" readonly="readonly" placeholder="基地简要描述 ...">${model.description }</textarea>
              </div>
			  <div class="col-sm-2"></div>
            </div>
			<div class="form-group">
              <label for="linkerName" class="col-sm-2 control-label">联系人</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="linkerName" name="linkerName" value="${model.linkerName }" placeholder="联系人" readonly="readonly">
              </div>
			  <div class="col-sm-2"></div>
            </div>
			<div class="form-group">
              <label for="linkerPhone" class="col-sm-2 control-label">联系电话</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="linkerPhone" name="linkerPhone" value="${model.linkerPhone }" placeholder="联系电话" readonly="readonly">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">实习人数</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="num" name="num" value="${model.num }" placeholder="联系电话" readonly="readonly">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            
          </div>
          <div class="box-footer">
		    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
		      <a href="${rootPath }/trainingBase_evaluate.action?id=${model.id}" class="btn btn-info pull-right">查看评价</a>
			  <button onclick="javascript:history.go(-1);" class="btn btn-default">返回</button>
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
<%@include file="../public/js.jspf" %>
<script src="${rootPath }/webuploader/webuploader.min.js"></script>
</body>
</html>
