package com.trader.entity.db;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="analyze_result")
public class AnalyzeResult {

	@EmbeddedId
	private AnalyzeResultPK pk;

	@Column (name="trade_result")
	private double tradeResult = 0;

}