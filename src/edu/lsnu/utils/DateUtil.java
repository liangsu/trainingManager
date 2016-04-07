package edu.lsnu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 获取当期日期
	 * @return
	 */
	public static Date getNowShort(){
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String nowStr = df.format(now);
		try {
			now = df.parse(nowStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return now;
	}
	
	public static int getCurrentYear(){
		int year = 2015;
		DateFormat df = new SimpleDateFormat("yyyy");
		year = Integer.parseInt(df.format(new Date()));
		return year;
	}
	
	public static int getYearByDate(Date date){
		int year = 0;
		DateFormat df = new SimpleDateFormat("yyyy");
		year = Integer.parseInt(df.format(date));
		return year;
	}
	
	public static String getDateString(Date date,String pattern){
		String dateStr = null;
		DateFormat df = new SimpleDateFormat(pattern);
		dateStr = df.format(date);
		return dateStr;
	}
	
	public static String getDateString(Date date){
		return getDateString(date, "yyyy-MM-dd");
	}
	
	public static Date getDate(String dateStr,String pattern){
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat(pattern);
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
