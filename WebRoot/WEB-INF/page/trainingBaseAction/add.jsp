<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>添加实习基地</title>
<%@include file="../public/css.jspf" %>
<link rel="stylesheet" href="webuploader/webuploader.css" type="text/css">
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div style="position:absolute;z-index:9999;top:90px;left:750px;width:250px;height:200px;">
 <img id="ImgPr" width="200px" height="200px" />
</div>

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 增加实习基地  </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">实习基地管理</a></li>
        <li class="active">添加实习实训基地</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"></div>
        <form class="form-horizontal" action="${rootPath }/trainingBase_add.action" method="post">
        	
          <div class="box-body">
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label">基地名称</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" placeholder="基地名称">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group">
              <label for="address" class="col-sm-2 control-label">基地地址</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="address" name="address" placeholder="基地地址">
              </div>
			  <div class="col-sm-2"></div>
            </div>
			<div class="form-group">
              <label class="col-sm-2 control-label">基地图片</label>
              <div class="col-sm-4">
        			<div id="uploader-demo">
					    <!--用来存放item-->
					    <div id="fileList" class="uploader-list"></div>
					    <div id="filePicker">选择图片</div>
					</div>
              </div>
			  <div class="col-sm-2">
			  </div>
            </div>
            </div>
			<div class="form-group">
              <label for="description" class="col-sm-2 control-label">基地简要描述</label>
              <div class="col-sm-4">
                <textarea id="description" name="description" class="form-control" rows="4" placeholder="基地简要描述 ..."></textarea>
              </div>
			  <div class="col-sm-2"></div>
            </div>
			<div class="form-group">
              <label for="linkerName" class="col-sm-2 control-label">联系人</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="linkerName" name="linkerName" placeholder="联系人">
              </div>
			  <div class="col-sm-2"></div>
            </div>
			<div class="form-group">
              <label for="linkerPhone" class="col-sm-2 control-label">联系电话</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="linkerPhone" name="linkerPhone" placeholder="联系电话">
              </div>
			  <div class="col-sm-2"></div>
            </div>
          </div>
          <div class="box-footer">
		    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
			  <button type="submit" class="btn btn-default">重置</button>
			  <button type="submit" class="btn btn-info pull-right">保存</button>
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
<script type="text/javascript" language="javascript">
// 初始化Web Uploader
var uploader = WebUploader.create({
    // 选完文件后，是否自动上传。
    auto: true,
    // swf文件路径
    swf: 'webuploader/Uploader.swf',
    // 文件接收服务端。
    server: rootPath+'/upload.action',
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#filePicker',
    // 只允许选择图片文件。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    }
});

// 文件上传成功，给item添加成功class, 用样式标记上传成功。
uploader.on( 'uploadSuccess', function( file, response ) {
   var data = response.path;
   $("#ImgPr").attr("src",data);
   console.log(data);
});
</script>
</body>
</html>
