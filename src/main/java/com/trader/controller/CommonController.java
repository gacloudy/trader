package com.trader.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.trader.entity.db.ApplicationLog;
import com.trader.entity.db.StockDateAvg;
import com.trader.repositories.ApplicationLogRepository;
import com.trader.repositories.IndustryTypeMstRepository;
import com.trader.repositories.StockDateAvgRepository;
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
	protected StockDateAvgRepository stockDateAvgRepository;

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
	
	protected boolean analyzeCross(
			int code,
			String date,
			int longTerm,
			int shortTerm
			) {
		if(shortTerm >= longTerm) {
			return false;
		}
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateUtil.getDateFromyyyyMMddStr(date));
			String dateTo = DateUtil.getyyyyMMddStrFromDate(cal.getTime());
			cal.add(Calendar.DATE, -1 * longTerm * 2);
			String dateFrom = DateUtil.getyyyyMMddStrFromDate(cal.getTime());
			
			List<StockDateAvg> listShort =
					stockDateAvgRepository.findByCodeAndAndSpanPriceDateBetwee(code, shortTerm,  dateFrom, dateTo);
			Map<String, Double> mapShort = 
					new HashMap<String, Double>();
			for(StockDateAvg avg : listShort) {
				mapShort.put(avg.getPk().getPriceDate(), avg.getAvgPrice());
			}
			
			List<StockDateAvg> listLong =
					stockDateAvgRepository.findByCodeAndAndSpanPriceDateBetwee(code, longTerm,  dateFrom, dateTo);
			if(listLong.size() < longTerm) {
				return false;
			}
			Map<String, Double> mapLong = 
					new HashMap<String, Double>();
			for(StockDateAvg avg : listLong) {
				mapLong.put(avg.getPk().getPriceDate(), avg.getAvgPrice());
			}
			
			double priceShort = mapShort.get(date);
			double priceLong = mapLong.get(date);
			
			if(priceShort == priceLong) {
				return false;
			}
			
			boolean buyFlg = priceShort > priceLong;
			
			for(int i = 1; i < longTerm; i++) {
				cal.setTime(DateUtil.getDateFromyyyyMMddStr(date));
				cal.add(Calendar.DATE, -1 * i);
				String dateKey = DateUtil.getyyyyMMddStrFromDate(cal.getTime());
				
				if(mapShort.containsKey(dateKey) && !mapLong.containsKey(dateKey)
						|| !mapShort.containsKey(dateKey) && mapLong.containsKey(dateKey)
						) {
					return false;
				} else if(!mapShort.containsKey(dateKey) && !mapLong.containsKey(dateKey)) {
					continue;
				}
				
				if(buyFlg && mapShort.get(dateKey) > mapLong.get(dateKey)
						|| !buyFlg && mapShort.get(dateKey) < mapLong.get(dateKey)) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}			


		return true;
		
	}

}