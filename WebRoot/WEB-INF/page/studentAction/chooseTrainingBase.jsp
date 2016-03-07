<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>选择基地</title>
<%@ include file="../public/css.jspf" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="background-color:#ecf0f5;margin-left:0;">
    <!-- 页面头部导航 -->
    <section class="content-header">
      <h1> 选择基地 </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>个人中心</li>
        <li class="active">选择基地</li>
      </ol>
    </section>
    <!-- 内容主体 -->
    <section class="content">
      <div class="box" style="margin-bottom:0;padding-bottom:1px;">
        <div class="box-header"></div>
        <form class="form-horizontal" action="${rootPath }/student_chooseTrainingBase.action" method="post">
          <input type="hidden" name="id" value="${model.id }">
          <div class="box-body">
            <div class="form-group">
              <label class="col-sm-2 control-label">实习去向：</label>
              <div class="col-sm-2">
                <label class="radio-inline">
				  <input type="radio" name="trainingType" value="0" ${model.trainingType eq 0 ? 'checked="checked"' : '' }> 集中实习
				</label>
              </div>
              <div class="col-sm-2">
                <label class="radio-inline">
				  <input type="radio" name="trainingType" value="1" ${model eq null ? 'checked="checked"' : (model.trainingType eq 1 ? 'checked="checked"' : '')  } >自主实习 
				</label>
              </div>
            </div>
            
            <div class="form-group jizhong">
              <label for="tid" class="col-sm-2 control-label">实习基地：</label>
              <div class="col-sm-4">
                <select class="" name="tid" id="tid">
                	<c:forEach items="${trainingBases }" var="tb">
                	  <option value="${tb.id }" ${model.tid eq tb.id ? 'selected="selected"' : '' }>${tb.name }</option>
                	</c:forEach>
                </select> &nbsp;
              </div>
			  <div class="col-sm-2"></div>
            </div>
            
            <div class="form-group zizhu">
              <label for="address" class="col-sm-2 control-label">实习基地名称：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" value="" placeholder="实习基地名称，如：xxx有限公司">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group zizhu">
              <label for="address" class="col-sm-2 control-label">实习基地地址：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="address" name="address" value="" placeholder="实习基地地址，如：xx省xx市xx街道xx号">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group zizhu">
              <label for="address" class="col-sm-2 control-label">实习基地联系人：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="linkerName" name="linkerName" value="" placeholder="实习基地联系人，如：小明">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group zizhu">
              <label for="address" class="col-sm-2 control-label">实习基地联系电话：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="linkerPhone" name="linkerPhone" value="" placeholder="实习基地联系电话，如：15520110823">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group zizhu">
              <label for="address" class="col-sm-2 control-label">实习基地指导老师：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="teacherName" name="teacherName" value="" placeholder="实习基地指导老师，如：王五">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            <div class="form-group zizhu">
              <label for="address" class="col-sm-2 control-label">实习基地指导老师电话：</label>
              <div class="col-sm-4">
                <input type="text" class="form-control" id="teacherPhone" name="teacherPhone" value="" placeholder="实习基地指导老师电话，如：15520110823">
              </div>
			  <div class="col-sm-2"></div>
            </div>
            
           </div>
          </div>
          <div class="box-footer">
		    <div class="col-sm-offset-2 col-md-2" style="padding-left:0px;">
			  <button type="reset" class="btn btn-default">重置</button>
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
<%@ include file="../public/js.jspf" %>
<script type="text/javascript">
$(function(){
	$(":radio").click(function(){
		var val = $(this).val();
		if(val == 0){
			$(".zizhu").hide();
			$(".jizhong").show();
		}else if(val == 1){
			$(".zizhu").show();
			$(".jizhong").hide();
		}
	});
	
	$("input[name='trainingType']:checked").click();
	
});

</script>
</body>
</html>