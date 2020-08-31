package com.emi.appclock.until;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
   public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String parseString(Object obj){
		if(obj!=null){
			return String.valueOf(obj);
		}
		return null;
	}

	public static String obj2String(Object obj){
		if(obj!=null){
			return String.valueOf(obj);
		}
		return "";
	}
	
	
	public static int parseInt(Object obj){
		if(obj!=null){
			return Integer.parseInt(String.valueOf(obj));
		}
		return 0;
	}
	
	public static double paresDouble(Object obj){
		if(obj!=null){
			return Double.parseDouble(String.valueOf(obj));
		}
		return 0;
	}
	
	public static BigDecimal parseBigDecimal(double data) {
		DecimalFormat df = new DecimalFormat("#.00");
		return new BigDecimal(df.format(data));
	}

	public static BigDecimal bigTobig(BigDecimal b){
		if(b==null){
			return new BigDecimal(0);
		}
		return b;
	}

	public static BigDecimal stringTobig(String str){
		if(str==null || str.equals("") ){
			return new BigDecimal(0);
		}
		return new BigDecimal(str);
	}


	public static double parseDouble(Object obj) {
		if(obj!=null){
			double data=Double.parseDouble(String.valueOf(obj));
			DecimalFormat df = new DecimalFormat("#.00");
			return new BigDecimal(df.format(data)).doubleValue();
		}
		return 0;
	}
	
	public static String dateParseToString(Date date){
		if(date!=null){
			return format.format(date);
		}else{
			return "";
		}
	}


	public static boolean isNullObject(Object object) {
		if (object == null || "".equals(object.toString())
				|| "null".equals(object.toString())) {
			return true;
		}
		return false;
	}
	public static String dateToString(Date date, String format) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String concttParam(String ... str){
		StringBuffer sbf=new StringBuffer();
		for(int i=0;i<str.length;i++){
			sbf.append(str[i]);
		}
		return  sbf.toString();
	}
}
