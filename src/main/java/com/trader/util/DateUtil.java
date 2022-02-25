package com.trader.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	// Dateから時刻だけ抽出(HHmm)
	private static final SimpleDateFormat sdfClockStr = new SimpleDateFormat("HHmm");
	public static String getHHmmStr(Date date) {
		return sdfClockStr.format(date);
	}
	// Dateから時刻だけ抽出(HH:mm)
	private static final SimpleDateFormat sdfClockStrWithColon = new SimpleDateFormat("HH:mm");
	public static String getHHcolonmmStr(Date date) {
		return sdfClockStrWithColon.format(date);
	}

	// Dateから文字列抽出（yyyy年M月d日（月））
	private static final SimpleDateFormat sdfKanjiWithDateFromDate = new SimpleDateFormat("yyyy年M月d日（E）");
	public static String getKanjiyyyyMdStrWithDateFromDate(Date date) {
		return sdfKanjiWithDateFromDate.format(date);
	}

	// Dateから文字列抽出（yyyy年MM月）
	private static final SimpleDateFormat sdfKanjiMonthStrFromDate = new SimpleDateFormat("yyyy年MM月");
	public static String getKanjiyyyyMMStrFromDate(Date date) {
		return sdfKanjiMonthStrFromDate.format(date);
	}

	// Dateから文字列抽出（yyyy年M月）
	private static final SimpleDateFormat sdfKanjiSingleMonthStrFromDate = new SimpleDateFormat("yyyy年M月");
	public static String getKanjiyyyyMStrFromDate(Date date) {
		return sdfKanjiSingleMonthStrFromDate.format(date);
	}

	// Dateから文字列抽出（yyyyMMdd）
	private static final SimpleDateFormat sdfNoSlashStrFromDate = new SimpleDateFormat("yyyyMMdd");
	public static String getyyyyMMddStrFromDate(Date date) {
		return sdfNoSlashStrFromDate.format(date);
	}

	// Dateから文字列抽出（yyyy/MM/dd）
	private static final SimpleDateFormat sdfSlashStrFromDate = new SimpleDateFormat("yyyy/MM/dd");
	public static String getyyyySMMSddStrFromDate(Date date) {
		return sdfSlashStrFromDate.format(date);
	}

	// Dateから文字列抽出（yyyy年M月d日）
	private static final SimpleDateFormat sdfKanjiSingleStrFromDate = new SimpleDateFormat("yyyy年M月d日");
	public static String getKanjiSingleStrFromDate(Date date) {
		return sdfKanjiSingleStrFromDate.format(date);
	}

	// Dateから文字列抽出（yyyy/MM）
	private static final SimpleDateFormat sdfDateSlashStrFromDate = new SimpleDateFormat("MM/dd");
	public static String getMMSddStrFromDate(Date date) {
		return sdfDateSlashStrFromDate.format(date);
	}

	// Dateから月だけ抽出（yyyy/MM）
	private static final SimpleDateFormat sdfSlashMonthStrFromDate = new SimpleDateFormat("yyyy/MM");
	public static String getyyyySMMStrFromDate(Date date) {
		return sdfSlashMonthStrFromDate.format(date);
	}

	// Dateから月だけ抽出（yyyyM）
	private static final SimpleDateFormat sdfMonthMinStrFromDate = new SimpleDateFormat("yyyyM");
	public static String getyyyyMStrFromDate(Date date) {
		return sdfMonthMinStrFromDate.format(date);
	}

	// Dateから月日だけ抽出（MM/dd）
	private static final SimpleDateFormat sdfSlashDateStrFromDate = new SimpleDateFormat("MM/dd");
	public static String getDateFromMMSddStr(Date date) {
		return sdfSlashDateStrFromDate.format(date);
	}


	// Dateから月だけ抽出（yyyyMM）
	private static final SimpleDateFormat sdfMonthStrFromDate = new SimpleDateFormat("yyyyMM");
	public static String getyyyyMMStrFromDate(Date date) {
		return sdfMonthStrFromDate.format(date);
	}



	// Date変換（yyyy/MM/dd）
	private static final SimpleDateFormat sdfDateFromSlashStr = new SimpleDateFormat("yyyy/MM/dd");
	public static Date getDateFromyyyySMMSddStr(String dateStr) {
		try {
			return sdfDateFromSlashStr.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	// Date変換（yyyyMMdd）
	private static final SimpleDateFormat sdfDateFromNoSlashStr = new SimpleDateFormat("yyyyMMdd");
	public static Date getDateFromyyyyMMddStr(String dateStr) {
		try {
			return sdfDateFromNoSlashStr.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	// Date変換（yyyyMMddHHmm）
	private static final SimpleDateFormat sdfTimingFromNoSlashStr = new SimpleDateFormat("yyyyMMddHHmm");
	public static Date getTimingFromyyyyMMddHHmmStr(String dateStr) {
		try {
			return sdfTimingFromNoSlashStr.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	// Dateからタイミングだけ抽出（yyyyMMddHHmm）
	private static final SimpleDateFormat sdfNoSlashTimingStrFromDate = new SimpleDateFormat("yyyyMMddHHmm");
	public static String getyyyyMMddHHmmStrFromDate(Date date) {
		return sdfNoSlashTimingStrFromDate.format(date);
	}

	// Dateからタイミングだけ抽出（yyyyMMddHHmmssSSS）
	private static final SimpleDateFormat sdfNoSlashMomentStrFromDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static String getNoSlashyyyyMMddHHmmssSSSStrFromDate(Date date) {
		return sdfNoSlashMomentStrFromDate.format(date);
	}

	// Dateからタイミングだけ抽出（yyyyMMddHHmmssSSS）
	private static final SimpleDateFormat sdfSlashMomentStrFromDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
	public static String getyyyyMMddHHmmssSSSStrFromDate(Date date) {
		return sdfSlashMomentStrFromDate.format(date);
	}

	// Date変換（yyyy-MM-dd）
	private static final SimpleDateFormat sdfTimingFromHaihunStr = new SimpleDateFormat("yyyy-MM-dd");
	public static Date getDateFromyyyyMMddHaihunStr(String dateStr) {
		try {
			return sdfTimingFromHaihunStr.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	// Dateから文字列抽出（yyyy-MM-dd）
	public static String getyyyyMMddHaihunStrFromDate(Date date) {
		return sdfTimingFromHaihunStr.format(date);
	}


	public static int diffOfMonth(String ymFrom, String ymTo) {
		try {
	        Calendar from = Calendar.getInstance();
	        from.setTime(new SimpleDateFormat("yyyyMMdd").parse(ymFrom + "01"));
	
	        Calendar to = Calendar.getInstance();
	        to.setTime(new SimpleDateFormat("yyyyMMdd").parse(ymTo + "01"));
	
	        int ymDiff = 0;
	        while(to.compareTo(from) >= 0) {
	        	++ymDiff;
	        	from.add(Calendar.MONTH, 1);
	        }
	        return ymDiff;
		} catch (Exception e) {
			return 0;
		}
	}

}