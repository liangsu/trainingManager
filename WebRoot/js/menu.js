/**
 * 保存修改、添加
 */
function saveMenu(){
	var id = $("#id").val();
	var pid = $("#pid").val();
	var name = $("#name").val();
	var url = $("#url").val();
	var cssClass = $("#cssClass").val();
	var theOrder = $("#theOrder").val();
	
	var data = {
		id:id,
		pid:pid,	
		name:name,
		url:url,
		cssClass:cssClass,
		theOrder:theOrder
	};
	console.log(data);
	
	$.post("menu_edit.action",data,function(text){
		if(text == "ok"){
			window.location.href = "menu_list.action";
		}else if(text == "error"){
			alert("保存失败！");
		}else{
			alert(text);
		}
	});
}

/**
 * 编辑、添加、添加子菜单 按钮的事件
 * @returns
 */
function editMenu(id,pid,name,url,cssClass,theOrder){
	$("#id").val(id);
	$("#pid").val(pid);
	$("#name").val(name);
	$("#url").val(url);
	$("#cssClass").val(cssClass);
	$("#theOrder").val(theOrder);
	$("#editMenuModal").modal('show');
}

/**
 * 删除菜单
 */
function delMenu(id){
	if(!id){
		alert("请选择要删除的信息");
		return ;
	}
	if(confirm("你确定要删除?")){
		$.get("menu_delete.action?id="+id,function(data){
			if(data == "ok"){
				window.location.href = "menu_list.action";
			}else if(data == "error"){
				alert("删除失败");
			}else{
				alert(data);
			}
		});
	}
}