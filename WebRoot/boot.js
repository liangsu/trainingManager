_CreateJSPath = function(js){
	var scripts = document.getElementsByTagName("script");
	var path = "";
	for(var i = 0, l = scripts.length; i < l; i++){
		var src = scripts[i].src;
		if(src.indexof(js) != -1){
			var ss = src.split(js);
			path = ss[0];
			break;
		}
	}
	var href = location.href;
	href = href.split("#")[0];
	href = href.split("?")[0];
	var ss = href.split("/");
	ss.length = ss.length - 1;
	href = ss.join("/");
	if(path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") !=0 ){
		path = href + "/" + path;
	}
	return path;
}

var bootPATH = _CreateJSPath("boot.js");

//debugger
mini_debugger = true;

//miniui
document.write('<script src="' + bootPATH + 'style/plugins/jQuery/jQuery-2.1.4.min.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/bootstrap/js/bootstrap.min.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/bootstrap/js/bootstrap.min.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/dist/js/app.min.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/plugins/chartjs/Chart.min.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/dist/js/pages/dashboard2.js" type="text/javascript"></sc'+'ript>');
document.write('<script src="' + bootPATH + 'style/dist/js/demo.js" type="text/javascript"></sc'+'ript>');
document.write('<link href="' + bootPATH + 'skin/mini/cdcb/icons.css" rel="stylesheet" type="text/css" />');