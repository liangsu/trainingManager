package edu.lsnu.utils;

public class StringUtil {

	private StringUtil(){
	}
	
	public static boolean isBank(String str){
		return str == null ? true : (str.trim().length() > 0 ? false : true);
	}
	
	public static boolean isNotBank(String str){
		return !isBank(str);
	}
}
