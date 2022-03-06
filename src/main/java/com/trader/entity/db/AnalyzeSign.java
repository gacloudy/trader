package com.trader.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.trader.Constants;
import com.trader.util.FileUtil;

import lombok.Data;

@Data
@Entity
@Table(name="analyze_sign")
public class AnalyzeSign {

	@Id
	@Column (name="analyze_id")
	private int analyzeId = 0;

	@Column (name="sign")
	private String sign = "";

	@Column (name="benefit")
	private double benefit = 0;

	@Column (name="cut")
	private double cut = 0;

	@Column (name="max_count")
	private int maxCount = 0;
	
	@Column (name="diff")
	private double diff = 0;

	public static void main(String args[]) throws Exception {
		FileUtil.deleteFile("C:\\sts-4.13.1.RELEASE\\workspace\\trader\\src\\main\\resources\\temp.sql");
		int id = 0;
		for(double i = 5.0; i <= 20.0; i++) {
			for(double j = -5.0; j >= -20.0; j--) {
				for(int k = 5; k <= 40; k += 5) {
					double l = 5.0;
					FileUtil.addFile("C:\\sts-4.13.1.RELEASE\\workspace\\trader\\src\\main\\resources\\temp.sql", "insert into trader.analyze_sign values(" + ++id + ",'" + Constants.SIGN_BUY + "'," + (i/100) + "," + (j/100) + "," + k + "," + (l/100) + ");");
					FileUtil.addFile("C:\\sts-4.13.1.RELEASE\\workspace\\trader\\src\\main\\resources\\temp.sql", "insert into trader.analyze_sign values(" + ++id + ",'" + Constants.SIGN_SALE + "'," + (-1 * i/100) + "," + (-1 * j/100) + "," + k + "," + (l/100) + ");");
				}
			}
		}
		
	}
	

}