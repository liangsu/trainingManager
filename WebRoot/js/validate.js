//这是一个用于校验的js
/**
 * 判断似乎否是数字
 * @param num
 * @returns
 */
function isNumber(num){
	var reg = /^[0-9]+$/;
	if(reg.test(num)){
		return true;
	}
	return false;
}

/**
 * 判断似乎否是钱
 * @param money 需要校验的值
 */
function isMoney(money){
	var reg=/^[0-9]*(\.[0-9]{1,2})?$/;
	if(reg.test(money)){
		return true;
	}
	return false;
}

function isTel(){
	
}

function isMobile(){
	
}

function isPhone(){
	
}

/**
 * 校验密码是否合法
 * 0-16位，包含字母、数字、"."、"_"
 * @param pwd
 * @returns {Boolean}
 */
function checkPassword(pwd){
	var reg = /^[0-9a-zA-Z_\.]{5,16}$/;
	if(reg.test(pwd)){
		return true;
	}
	return false;
}