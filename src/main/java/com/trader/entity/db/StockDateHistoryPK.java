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
public class StockDateHistoryPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column (name="code")
	private int code = 0;
	// 日付
	@Column (name="price_date")
	private String priceDate = "";

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockDateHistoryPK other = (StockDateHistoryPK) obj;
		if (code != other.code)
			return false;
		if (priceDate == null) {
			if (other.priceDate != null)
				return false;
		} else if (!priceDate.equals(other.priceDate))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((priceDate == null) ? 0 : priceDate.hashCode());
		return result;
	}
}