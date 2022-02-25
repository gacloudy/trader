package com.trader.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="stock_mst")
public class StockMst {

	// 銘柄コード
	@Id
	@Column
	private int code = 0;
	// 銘柄名
	@Column
	private String stockName = "";
	@Column
	private String stockNameEn = "";
	// 業種
	@Column
	private int industryType = -1;
	// 集計対象
	@Column
	private int gatheringFlg = -1;

}