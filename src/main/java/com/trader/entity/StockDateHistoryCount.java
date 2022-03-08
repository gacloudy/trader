package com.trader.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class StockDateHistoryCount {

	public StockDateHistoryCount(String priceDate, BigInteger count) {
		this.priceDate = priceDate;
		this.count = count;
	}

	public StockDateHistoryCount(Object[] objects) { 
		this((String) objects[0], (BigInteger) objects[1]);
	}
	
	@Id
	private String priceDate = "";

	private BigInteger count = new BigInteger("0");


}