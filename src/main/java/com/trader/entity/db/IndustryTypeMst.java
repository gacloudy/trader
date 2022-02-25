package com.trader.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="industry_type_mst")
public class IndustryTypeMst {

	// 業種
	@Id
	@Column
	private int industryType = -1;
	// 業種名
	@Column
	private String industryName = "";
	// 業種名
	@Column
	private String industryNameEn = "";
}