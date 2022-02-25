package com.trader.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtil {
	
	public static void main(String[] args) throws Exception {
		int code = 8411;
		List<String> list =
				getHttpPage("https://finance.yahoo.co.jp/quote/" + code + ".T");

		System.out.println(isYahooStarted(list));
//		System.out.println(getYesterdayPrice(list));
//		System.out.println(getStartPrice(list));
//		System.out.println(getHighestPrice(list));
//		System.out.println(getLowestPrice(list));
//		System.out.println(getAmount(list));
		;
	}
	
	private static final Pattern PATTERN_PRICE = Pattern.compile(">[0-9]{0,3},?[0-9]{0,3},?[0-9]{1,3}\\.?[0-9]?株?</span>");
	private static final String LABEL_ALERT = "</span>アラート設定</a>";
	private static final String LABEL_HEADER_CLOSE = "</header>";
	private static final String LABEL_YESTERDAY_PRICE = ">前日終値</span>";
	private static final String LABEL_START_PRICE = ">始値</span>";
	private static final String LABEL_HIGHEST_PRICE = ">高値</span>";
	private static final String LABEL_LOWEST_PRICE = ">安値</span>";
	private static final String LABEL_AMOUNT = ">出来高</span>";
	private static final String LABEL_BUY_SALE = ">売買代金</span>";
	
	public static boolean isYahooStarted(List<String> list) {
		String dateKey = DateUtil.getDateFromMMSddStr(new Date());

		try {
			for(String row : list) {
				
				if(row.indexOf(">リアルタイム株価<") > -1
						&& row.indexOf("</time>") > -1) {
					int indexStart = row.indexOf(">リアルタイム株価<");
					String editedRow1 = row.substring(indexStart + ">リアルタイム株価<".length());
	
					int indexEnd = row.indexOf("</time>", 0);
					String editedRow2 = editedRow1.substring(0, indexEnd);
	
					if(editedRow2.indexOf(dateKey) > -1) {
						return true;
					}

					break;
				}
				
			}
		} catch (Exception e) {
		}
		return false;
	}
	/**
	 * 株価HTTP取得
	 */
	public static List<String> getStockHTTP(int code) {
		
		return getHttpPage("https://finance.yahoo.co.jp/quote/" + code + ".T");
		
	}

	/**
	 * 株価取得
	 */
	public static double getPrice(List<String> list) {
		
		return extractNumber(list, LABEL_ALERT, LABEL_HEADER_CLOSE);
		
	}

	
	/**
	 * 前日終値取得
	 */
	public static double getYesterdayPrice(List<String> list) {
		
		return extractNumber(list, LABEL_YESTERDAY_PRICE, LABEL_START_PRICE);
		
	}

	/**
	 * 始値取得
	 */
	public static double getStartPrice(List<String> list) {
		
		return extractNumber(list, LABEL_START_PRICE, LABEL_HIGHEST_PRICE);
		
	}

	/**
	 * 高値取得
	 */
	public static double getHighestPrice(List<String> list) {
		
		return extractNumber(list, LABEL_HIGHEST_PRICE, LABEL_LOWEST_PRICE);
		
	}

	/**
	 * 高値取得
	 */
	public static double getLowestPrice(List<String> list) {
		
		return extractNumber(list, LABEL_LOWEST_PRICE, LABEL_AMOUNT);
		
	}

	/**
	 * 出来高取得
	 */
	public static int getAmount(List<String> list) {
		
		return (int)extractNumber(list, LABEL_AMOUNT, LABEL_BUY_SALE);
		
	}

	/**
	 * HTTPページ取得
	 */
	public static List<String> getHttpPage(String urlStr, int timeout) {
		List<String> result = new ArrayList<String>();
		HttpURLConnection con = null;
		InputStream in = null;
		BufferedReader br = null;

		try {

			// URLの作成
			URL url = new URL(urlStr);

			// 接続用HttpURLConnectionオブジェクト作成
			con = (HttpURLConnection)url.openConnection();
			// リクエストメソッドの設定
			con.setRequestMethod("GET");
			// リダイレクトを自動で許可しない設定
			con.setInstanceFollowRedirects(true);
			// ヘッダーの設定(複数設定可能)
			con.setRequestProperty("Accept-Language", "jp");
	        con.setRequestProperty("Content-Type","text/xml;charset=utf-8");
	        con.setReadTimeout(timeout);

			// 接続
			con.connect();

			// 本文の取得
			in = con.getInputStream();
			byte bodyByte[] = new byte[1024];
			in.read(bodyByte);

			br = new BufferedReader(new InputStreamReader(in, "utf-8"));

			String line;
			while ((line = br.readLine()) != null) {
				result.add(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<String>();
		} finally {
			try {
				if (con != null) {
					con.disconnect();
				}
				if (in != null) {
					in.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				return new ArrayList<String>();
			}
		}
		return result;
	}
	
	/**
	 * HTTPページ取得
	 */
	public static List<String> getHttpPage(String urlStr) {
		
		return getHttpPage(urlStr, 60 * 1000);
	}
	
	private static double extractNumber(List<String> list, String start, String end) {
		try {
			for(String row : list) {
	
				if(row.indexOf(start) > -1
						&& row.indexOf(end) > -1) {
					int indexStart = row.indexOf(start);
					String editedRow1 = row.substring(indexStart + start.length());
	
					int indexEnd = row.indexOf(end, 0);
					String editedRow2 = editedRow1.substring(0, indexEnd);
	
					Matcher matcher = PATTERN_PRICE.matcher(editedRow2);
					if (matcher.find()) {
						String resultStr = matcher.group().replaceAll("</span>", "").replaceAll(">", "").replaceAll(",", "").replaceAll("株", "");
						return Double.parseDouble(resultStr);
					}

					break;
				}
				
			}
		} catch (Exception e) {
			return 0.0;
		}

		
		return 0.0;
	}
}
