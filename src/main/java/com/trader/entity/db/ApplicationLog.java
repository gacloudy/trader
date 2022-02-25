package com.trader.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="application_log")
public class ApplicationLog {

	// 日付
	@Id
	@Column
	private String date = "";
	// ログ
	@Column
	private String log = "";
}