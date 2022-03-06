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

import com.trader.Constants;
import com.trader.entity.db.AnalyzeResult;
import com.trader.entity.db.AnalyzeResultPK;
import com.trader.entity.db.AnalyzeSign;
import com.trader.entity.db.ApplicationLog;
import com.trader.entity.db.StockDateAvg;
import com.trader.entity.db.StockDateCross;
import com.trader.entity.db.StockDateCrossPK;
import com.trader.entity.db.StockDateHistory;
import com.trader.repositories.AnalyzeResultRepository;
import com.trader.repositories.AnalyzeSignRepository;
import com.trader.repositories.ApplicationLogRepository;
import com.trader.repositories.IndustryTypeMstRepository;
import com.trader.repositories.StockDateAvgRepository;
import com.trader.repositories.StockDateCrossRepository;
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
	protected StockDateCrossRepository stockDateCrossRepository;

	@Autowired
	protected ApplicationLogRepository applicationLogRepository;

	@Autowired
	protected AnalyzeSignRepository analyzeSignRepository;

	@Autowired
	protected AnalyzeResultRepository analyzeResultRepository;

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
			int longSpan,
			int shortSpan
			) {
		if(shortSpan >= longSpan) {
			return false;
		}
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(DateUtil.getDateFromyyyyMMddStr(date));
			String dateTo = DateUtil.getyyyyMMddStrFromDate(cal.getTime());
			cal.add(Calendar.DATE, -1 * longSpan * 2);
			String dateFrom = DateUtil.getyyyyMMddStrFromDate(cal.getTime());
			
			List<StockDateAvg> listShort =
					stockDateAvgRepository.findByCodeAndSpanAndPriceDateBetween(code, shortSpan,  dateFrom, dateTo);
			Map<String, Double> mapShort = 
					new HashMap<String, Double>();
			for(StockDateAvg avg : listShort) {
				mapShort.put(avg.getPk().getPriceDate(), avg.getAvgPrice());
			}
			if(!mapShort.containsKey(date)) {
				return false;
			}
			
			List<StockDateAvg> listLong =
					stockDateAvgRepository.findByCodeAndSpanAndPriceDateBetween(code, longSpan,  dateFrom, dateTo);
			if(listLong.size() < longSpan) {
				return false;
			}
			Map<String, Double> mapLong = 
					new HashMap<String, Double>();
			for(StockDateAvg avg : listLong) {
				mapLong.put(avg.getPk().getPriceDate(), avg.getAvgPrice());
			}
			if(!mapLong.containsKey(date)) {
				return false;
			}
			
			double priceShort = mapShort.get(date);
			double priceLong = mapLong.get(date);
			
			if(priceShort == priceLong) {
				return false;
			}
			
			boolean buyFlg = priceShort > priceLong;
			
			for(int i = 1; i < longSpan; i++) {
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
			
			StockDateCross obj =
					new StockDateCross();
			StockDateCrossPK pk = new StockDateCrossPK(code, date, longSpan, shortSpan);
			obj.setPk(pk);
			obj.setSign(buyFlg ? Constants.SIGN_BUY: Constants.SIGN_SALE);
			stockDateCrossRepository.save(obj);
			
		} catch (Exception e) {
			addLog("analyzeCrossに失敗"
					+ " code:" + code
					+ " date:" + date
					+ " longSpan:" + longSpan
					+ " shortSpan:" + shortSpan
					+ e.toString());
			return false;
		}			


		return true;
		
	}
	
	protected void registerAnalyzeResult(
			int code,
			StockDateCross cross,
			AnalyzeSign as
			) {

		try {

			Calendar cal = Calendar.getInstance();
			cal.setTime(DateUtil.getDateFromyyyyMMddStr(cross.getPk().getPriceDate()));
			String dateKey = DateUtil.getyyyyMMddStrFromDate(cal.getTime());
			cal.add(Calendar.MONTH, 3);
			String dateTo = DateUtil.getyyyyMMddStrFromDate(cal.getTime());
			List<StockDateHistory> priceList =
					stockDateHistoryRepository.findByCodeAndPriceDateBetween(code, dateKey, dateTo);
			if(priceList.isEmpty()) {
				return;
			}

			StockDateHistory startObj = priceList.get(priceList.size() - 1);

			if(startObj.getEndPrice() - startObj.getYesterdayPrice() > 0
					&& (startObj.getEndPrice() - startObj.getYesterdayPrice()) / startObj.getYesterdayPrice() >= as.getDiff()) {
				return;
			} else if(startObj.getEndPrice() - startObj.getYesterdayPrice() < 0
					&& (startObj.getEndPrice() - startObj.getYesterdayPrice()) / startObj.getYesterdayPrice() <= -1 * as.getDiff()) {
				return;
			}

			double benefit = as.getBenefit();
			double cut = as.getCut();

			// 開始の株価
			double basePrice =
					priceList.get(priceList.size() - 1).getEndPrice();
			double benefitPrice =
					basePrice * (1 + benefit);
			double cutPrice =
					basePrice * (1 + cut);

			int count = 0;
			for(int i = priceList.size() - 1 - 1; 0 <= i; i--) {
				if(++count > as.getMaxCount()) {
					break;
				}
				
				StockDateHistory obj =
						priceList.get(i);
				
				AnalyzeResult ar =
						new AnalyzeResult();
				AnalyzeResultPK pk = 
						new AnalyzeResultPK(code, as.getAnalyzeId(), cross.getPk().getPriceDate(), cross.getPk().getLongSpan(), cross.getPk().getShortSpan());
				ar.setPk(pk);

				if(Constants.SIGN_BUY.equals(cross.getSign())) {
					if(obj.getStartPrice() >= benefitPrice) {
						ar.setTradeResult((obj.getStartPrice() - basePrice) / basePrice);
						analyzeResultRepository.save(ar);
						break;
					} else if(obj.getStartPrice() <= cutPrice) {
						ar.setTradeResult((obj.getStartPrice() - basePrice) / basePrice);
						analyzeResultRepository.save(ar);
					} else if(obj.getHighestPrice() >= benefitPrice) {
						ar.setTradeResult((benefitPrice - basePrice) / basePrice);
						analyzeResultRepository.save(ar);
						break;
					} else if(obj.getLowestPrice() <= cutPrice) {
						ar.setTradeResult((cutPrice - basePrice) / basePrice);
						analyzeResultRepository.save(ar);
						break;
					}
				} else {
					if(obj.getStartPrice() <= benefitPrice) {
						ar.setTradeResult(-1 * (obj.getStartPrice() - basePrice) / basePrice);
						analyzeResultRepository.save(ar);
						break;
					} else if(obj.getStartPrice() >= cutPrice) {
						ar.setTradeResult(-1 * (obj.getStartPrice() - basePrice) / basePrice);
						analyzeResultRepository.save(ar);
					} else if(obj.getHighestPrice() <= benefitPrice) {
						ar.setTradeResult(-1 * (benefitPrice - basePrice) / basePrice);
						analyzeResultRepository.save(ar);
						break;
					} else if(obj.getLowestPrice() >= cutPrice) {
						ar.setTradeResult(-1 * (cutPrice - basePrice) / basePrice);
						analyzeResultRepository.save(ar);
						break;
					}
				}
			}
		} catch (Exception e) {
		}
	}
}