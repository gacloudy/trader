package com.trader.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public static void copyFile(String inputFile, String outputFile) throws Exception {
		//FileInputStreamのオブジェクトを生成する
		FileInputStream fileIn = null;

		//FileOutputStreamのオブジェクトを生成する
		FileOutputStream fileOut = null;

		try {
			//FileInputStreamのオブジェクトを生成する
			fileIn = new FileInputStream(inputFile);

			//FileOutputStreamのオブジェクトを生成する
			fileOut = new FileOutputStream(outputFile);

			// byte型の配列を宣言
			byte[] buf = new byte[256];
			int len;

			// ファイルの終わりまで読み込む
			while((len = fileIn.read(buf)) != -1){
				fileOut.write(buf, 0, len);
			}

			//ファイルに内容を書き込む
			fileOut.flush();

		} finally {

			//ファイルの終了処理
			if(fileOut != null) {
				fileOut.close();
			}
			if(fileIn != null) {
				fileIn.close();
			}
		}

	}

	/**
	 * ファイル読み込み
	 */
	public static List<String> readFile(String filePath) {

		return readFile(filePath, "utf-8");
	}

	/**
	 * ファイル読み込み
	 */
	public static List<String> readFile(String filePath, String encode) {
		List<String> list = new ArrayList<String>();
		File file = new File(filePath);

		if (!file.exists()) {
			return list;
		}

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encode));
			String str = br.readLine();
			while(str != null){
				list.add(str);
				str = br.readLine();
			}
		}catch(Exception e) {
			return new ArrayList<String>();
		} finally {
			if (br != null) {
				  try {
					br.close();
				} catch (IOException e) {
				}
			}
		}

		return list;
	}

	/**
	 * ファイル書き込み
	 */
	public static void writeFile(String filePath, List<String> list) throws Exception {
		writeFile(filePath, list, "utf-8");
	}

	/**
	 * ファイル書き込み
	 */
	public static void writeFile(String filePath, List<String> list, String encode) throws Exception {
		FileWriter filewriter = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try{
			File file = new File(filePath);
			
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			
			if (file.exists()) {
				file.delete();
			}
			filewriter = new FileWriter(file);
			bw = new BufferedWriter(filewriter);

			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), encode)));
			for (int i = 0; i < list.size(); i++) {
				String row = list.get(i);
				if (0 < i) {
					pw.println();
				}
				pw.write(row);
			}

			pw.close();
			bw.close();
			filewriter.close();
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (bw != null) {
				bw.close();
			}
			if (filewriter != null) {
				filewriter.close();
			}
		}
	}

	/**
	 * ファイル追記
	 */
	public static void addFile(String filePath, String addText) throws Exception {
		FileWriter filewriter = null;

		try {
			File file = new File(filePath);

			if(file.exists()) {
		        filewriter = new FileWriter(file, true);
			} else {
		        filewriter = new FileWriter(file, false);
			}

	        filewriter.write(addText);
	        filewriter.write("\n");
		} finally {
			if(filewriter !=null) {
		        filewriter.close();
			}
		}
	}


	/**
	 * ファイル削除
	 */
	public static void deleteFile(String filePath) throws Exception {
		// Fileインスタンス生成
		File file = new File(filePath);

		// 存在チェック
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else {
				// ディレクトリは子ファイルを全て削除
				for (String cFile : file.list()) {
					deleteFile(cFile);
				}
				// ディレクトリ削除
				file.delete();
			}
		}
	}
}
