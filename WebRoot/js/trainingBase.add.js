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
   $("#img").val(data);
   console.log(data);
});

/**
 * 提交表单
 */
function submitForm(){
	var areaForm = $("#areaForm");
	var url = areaForm.attr("action");
	var data = areaForm.serialize();
	
	$.post(url,data,function(text){
		if(text == "ok"){
			window.location.href = rootPath + "/trainingBase_list.action";
		}else if(text == "error"){
			alert("保存失败!");
		}else{
			alert(text);
		}
	});
}
