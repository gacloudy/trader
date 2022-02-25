package com.trader.controller;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.trader.entity.db.ApplicationLog;
import com.trader.entity.db.StockMst;
import com.trader.repositories.ApplicationLogRepository;
import com.trader.repositories.IndustryTypeMstRepository;
import com.trader.repositories.StockDateHistoryRepository;
import com.trader.repositories.StockMstRepository;
import com.trader.util.CacheUtil;
import com.trader.util.DateUtil;



@RestController
public abstract class CommonController {

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpSession session;	

	@Autowired
	protected CacheUtil cUtil;

	@Autowired
	protected StockMstRepository stockMstRepository;

	@Autowired
	protected IndustryTypeMstRepository industryTypeMstRepository;

	@Autowired
	protected StockDateHistoryRepository stockDateHistoryRepository;

	@Autowired
	protected ApplicationLogRepository applicationLogRepository;

	@PersistenceContext
	EntityManager entityManager;
	
	protected void addLog(String log) {
		try {
			ApplicationLog applicationLog =
					new ApplicationLog();
			applicationLog.setDate(DateUtil.getyyyyMMddHHmmssSSSStrFromDate(new Date()));
			applicationLog.setLog(log);
			
			applicationLogRepository.save(applicationLog);
		} catch (Exception e) {
		}
	}

}