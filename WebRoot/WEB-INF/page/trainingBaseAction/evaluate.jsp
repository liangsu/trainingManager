<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>基地评价</title>
<%@include file="../public/css.jspf" %>
<link rel="stylesheet" href="webuploader/webuploader.css" type="text/css">
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 基地评价  </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li><a href="#">实习基地管理</a></li>
        <li><a href="${rootPath }/trainingBase_detailInfo.action?id=${model.id}">基地信息</a></li>
        <li class="active">基地评价</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;" width="auto">
        <div class="box-header with-border"><h3 class="box-title">对<span class="font-bold">${model.name }</span>的评价（共<span id="evaluateTotal" ></span>）：</h3></div><!-- /.box-header -->
        
        <%--
         <div class="direct-chat-msg">
           <div class="direct-chat-info clearfix">
             <span class="direct-chat-name pull-left">王二娃</span>
             <span class="direct-chat-timestamp pull-right">满意</span>
           </div>
           <img class="direct-chat-img" src="dist/img/user1-128x128.jpg" alt="message user image">
           <div class="direct-chat-text"> 非常好</div>
         </div> --%>
         
         
         <div id="more" class="box-footer text-center"><a href="javascript:getMore();" class="uppercase">查看更多</a></div>
                
      </div>
    </section>
  </div>
  <!-- /.content-wrapper -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<%@include file="../public/js.jspf" %>
<script src="${rootPath }/webuploader/webuploader.min.js"></script>
<script type="text/javascript">
$(function(){
	getMore();
});

var currentPage = 0;
var pageCount = 1;
var id = "${model.id}";
/**
 * 查看更多的点击事件
 */
function getMore(){
	if(currentPage >= pageCount){
		return ;
	}
	currentPage++;
	
	//请求数据
	$.post(rootPath + "/trainingBase_getEvaluate.action",{id:id,currentPage:currentPage},function(data){
		data = eval("("+data+")");
		if(data){
			pageCount = data.pageCount;
			currentPage = data.currentPage;
			$("#evaluateTotal").text(data.recordCount);
			//
			var list = data.recordList;
			for(var i = 0;i<list.length;i++){
				var html = getHtml(list[i].username,list[i].evaluate,list[i].estimate); 
				$(html).insertBefore("#more");
			}
		}
	});
}

/**
 * 获取html
 */
function getHtml(username, evaluate, estimate){
	if(estimate == 0){
		estimate = "不满意";
	}else if(estimate == 25){
		estimate = "满意";
	}else if(estimate == 50){
		estimate = "非常满意";
	}
	
	
	var html = '<div class="direct-chat-msg">'
		        + '  <div class="direct-chat-info clearfix">'
		        + '    <span class="direct-chat-name pull-left">'+username+'</span>'
		        + '    <span class="direct-chat-timestamp pull-right">'+estimate+'</span>'
		        + '  </div>'
		        + '  <img class="direct-chat-img" src="dist/img/user1-128x128.jpg" alt="message user image">'
		        + '  <div class="direct-chat-text">'+evaluate+'</div>'
		        + '</div>';
	return html;
}
</script>
</body>
</html>
