package com.trader.entity.db;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="stock_date_history")
public class StockDateHistory {

	@EmbeddedId
	private StockDateHistoryPK pk;

	// 始値
	@Column
	private double startPrice = 0.0;
	// 終値
	@Column
	private double endPrice = 0.0;
	// 高値
	@Column
	private double highestPrice = 0.0;
	// 安値
	@Column
	private double lowestPrice = 0.0;
	// 出来高
	@Column
	private int amount = 0;
	// 昨日株価
	@Column
	private double yesterdayPrice = 0.0;

//	public String getPriceDateNoSlash() {
//		if(pk.getPriceDate() == null) {
//			return "";
//		} else {
//			return pk.getPriceDate().replaceAll("/", "");
//		}
//	}

}