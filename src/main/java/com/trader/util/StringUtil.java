package com.trader.util;

public class StringUtil {

	/**
	 * 空文字判定
	 */
	public static String holdDoubleQuatation(String val) {
		return "\"" + val + "\"";
	}

	/**
	 * 空文字判定
	 */
	public static boolean isEmpty(String val) {
		return val==null || val.length() == 0;
	}

	/**
	 * NOT空文字判定
	 */
	public static boolean isNotEmpty(String val) {
		return !isEmpty(val);
	}

	/**
	 * 数字判定
	 */
	public static boolean isNumber(String val) {
		try {
			Double.parseDouble(val);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String SpritByCoron(String val) {
		
		if(val == null || val.length() < 4) {
			return val;
		}
		return val.substring(0, 2) + ":" + val.substring(2, 4);
	}

	public static String SpritBySlash(String val) {
		
		if(val == null || val.length() < 8) {
			return val;
		}
		return val.substring(0, 4) + "/" + val.substring(4, 6) + "/" + val.substring(6, 8);
	}


}