package edu.lsnu.utils;

public class ValidateUtil {

	public static String isBank(String str, String errorMsg){
		if(StringUtil.isBank(str)){
			return errorMsg;
		}
		return "";
	}
}
