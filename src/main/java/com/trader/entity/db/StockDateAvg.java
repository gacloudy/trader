package com.trader.entity.db;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="stock_date_avg")
public class StockDateAvg {

	@EmbeddedId
	private StockDateAvgPK pk;

	// 平均株価
	@Column
	private double avgPrice = 0.0;

}