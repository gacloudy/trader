package com.trader.entity.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class StockDateCrossPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column (name="code")
	private int code = 0;
	@Column (name="price_date")
	private String priceDate = "";
	@Column (name="long_span")
	private int longSpan = 0;
	@Column (name="short_span")
	private int shortSpan = 0;

}