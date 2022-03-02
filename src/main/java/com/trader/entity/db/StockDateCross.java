package com.trader.entity.db;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="stock_date_cross")
public class StockDateCross {

	@EmbeddedId
	private StockDateCrossPK pk;

	@Column
	private String sign = "";

}