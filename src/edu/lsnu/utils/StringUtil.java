package edu.lsnu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private StringUtil(){
	}
	
	public static boolean isBank(String str){
		return str == null ? true : (str.trim().length() > 0 ? false : true);
	}
	
	public static boolean isNotBank(String str){
		return !isBank(str);
	}
	
	public static boolean isNumber(String number){
		String reg = "^[0-9]+$";//数字开头，数字结尾
		Pattern p = Pattern.compile(reg);
		Matcher matcher = p.matcher(number);
		if(matcher.find()){
			return true;
		}
		return false;
	}
	
}
