package com.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	/**
	 * @description 正则判断是否为正数
	 * @author 龚梁钧
	 * @created 2016年12月22日 下午4:43:25
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/*
	 * 传入一个字符串数组以奇数位置为可以，偶数位置为value 拆分为key/value
	 */
	public static Map<String, String> stringToMap(String[] strArr){
		Map<String, String> result = new HashMap<String, String>();
		for(int i=1; i<strArr.length-1; i++ ){
			if(i%2==1){//如果下标为奇数 存为key
				result.put(strArr[i], strArr[i+1]);
			}
		}
		
		return result;
	}
}
