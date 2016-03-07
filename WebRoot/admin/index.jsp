<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>${name }</title>
<script type="text/javascript" src="boot.js"></script>
<%
	if (session.getAttribute("cbPerson") == null) {
%>
<script type="text/javascript">
	window.location.href=encodeURI("<%=request.getContextPath() %>/admin/login.jsp");
</script>
<%
	}
%>
</head>

<body class="c-scroll-no">
<input type="hidden" name="username" value="">
<input type="hidden" name="password" value="">
<div id="layout" class="mini-layout" style="width:100%;height:100%">
	<div class="c-main-header">
		<div class="c-logo-body">
			<div class="c-user-body right">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="c-user-info">
							<div class="left">你好：<strong>admin</strong></div>
							<div class="left c-user-set"><a href="javascript:void(0);" onclick="createTag('6-2','个人资料','base/personData.jsp')"></a>个人设置</div>
							<div class="left c-user-time"><span id="c-main-datetime">2013年03月05日 星期二 17:56:21</span></div>
							<script type="text/javascript">setInterval('$("#c-main-datetime").html(getSystemTime)',1000);</script>
						</td>
					</tr>
					<tr>
						<td class="c-user-tool">
							<div class="right c-user-tool-login">
								<a href="javascript:void(0);" onclick="cannel();"><span class="c-i-tool-c">注销</span></a>
								<a href="javascript:void(0);" onclick="outlogin();"><span class="c-i-tool-o">退出</span></a>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="c-main-logo">${name }网站管理系统</div>
		</div>
	</div>
</div>

<div class="c-main-footer h28" title="south" region="south" showSplit="false" showHeader="false" height="28" style="border:0;" spliSize="0" bodyStyle="overflow:hidden;">
	<div class="right">
		<a href="#"><span class="c-i-tool-n">版权信息</span></a>
	</div>
	<div class="left">
		<div class="left">
			<div class="c-i-tool-menu-down" id="c-tool-menu">
				<div class="ico"></div>
			</div>
		</div>
	</div>
</div>

<div title="west" region="west" showSplit="false" showHeader="false" class="c-main-body-left" width="210" style="border:0;padding:5px;" splitSize="0">
	<div class="m-menu-top">
		<div class="right"></div>
		<div class="left"></div>
		<div class="title"><h1 id="c-menu-title">功能菜单</h1></div>
	</div>
	
	<div class="m-menu-body mini-fit" style="overflow:hedden;">
		<iframe src="menu.jsp" allowtransparency="true" style="background-color:transparent;" frameborder="0" id="left" name="left" scrolling="auto" width="100%" height="100%"></iframe>
	</div>
	
	<div class="m-menu-bottom">
		<div class="right"></div>
		<div class="left"></div>
	</div>
	
</div>

<div title="center" region="center" style="border:0;" bodyStyle="overflow:hidden;">
	<div id="mainTabs" class="mini-tabs c-main-tabs" activeIndex="0" style="width:100%;height:100px;" showCollapseButton="true" bodyStyle="border-left:1px solid #278295">
		<div name="home" title="首页" url="getLoginCount.cdcb"></div>
	</div>
</div>

</body>
<script type="text/javascript">
mini.parse();
var layout = mini.get("layout");
$("#c-tool-menu").click(function(){
	if(this.className == "c-i-tool-menu-down"){
		this.title = "显示左侧菜单";
		this.className = "c-i-tool-menu-up";
		$("#c-main-body-left").addClass("c-dis-off");
		layout.updateRegion("west",{visible:false});
	}else{
		this.title = "关闭左侧菜单";
		this.className = "c-i-tool-menu-down";
		ayout.updateRegion("west",{visible:true});
	}
});

function createTag(id,text,url){
	var tabs = mini.get("mainTabs");
	var id = "tab$" + id;
	var tab = tabs.getTab(id);
	if(!tab){
		tag = {};
		tab.name = id;
		tab.title = "text";
		tab.showCloseButton = true;
		tab.url = url;
		tab.addTab(tab);
	}else{
		tabs.loadTab(url,tab);
	}
	tabs.activeTab(tab);
}

function fullScreen(obj){
	
}

function outlogin(){

}

function cannel(){
	
}
</script>
</html>
