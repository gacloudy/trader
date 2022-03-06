package com.trader.util;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.trader.entity.db.AnalyzeSign;
import com.trader.entity.db.StockDateCross;
import com.trader.entity.db.StockMst;
import com.trader.repositories.AnalyzeSignRepository;
import com.trader.repositories.StockDateCrossRepository;
import com.trader.repositories.StockMstRepository;



@Service
@CacheConfig(cacheNames = {"stockMstInfo", "stockMstMap", "goldenCross", "analyzeSign"})
public class CacheUtil {

	@Autowired
	CacheManager cacheManager;

	@Autowired
	protected StockMstRepository stockMstRepository;
	
	@Autowired
	protected StockDateCrossRepository stockDateCrossRepository;

	@Autowired
	protected AnalyzeSignRepository analyzeSignRepository;

	@Scheduled(cron = "0 0 * * * *")
	public void evictAllcachesAtIntervalsAll() {
		for(String nm : cacheManager.getCacheNames()) {
			cacheManager.getCache(nm).clear();
		}
	}

	@Cacheable(value="stockMstMap", sync=true)
	public StockMst getStockInfo(int code) {
		try {
			return stockMstRepository.findByCode(code);
		} catch (Exception e) {
		}
		return null;
	}
	
	@Cacheable(value="stockMstMap", sync=true)
	public Map<Integer, StockMst> getStockMap() {
		Map<Integer, StockMst> result = new TreeMap<Integer, StockMst>();

		try {
			for(StockMst mst : stockMstRepository.findAll()) {
				result.put(mst.getCode(), mst);
			}
		} catch (Exception e) {
		}

		return result;
	}

	@Cacheable(value="goldenCross", sync=true)
	public StockDateCross getGoldenCross(int code, String priceDate, int longSpan, int shortSpan) {
		try {
			return stockDateCrossRepository.findOneByCodeAndPriceDateAndLongSpanAndShortSpan(code, priceDate, longSpan, shortSpan);
		} catch (Exception e) {
		}
		return null;
	}
	
	@Cacheable(value="analyzeSign", sync=true)
	public Map<Integer, AnalyzeSign> getAnalyzeSignMap() {
		Map<Integer, AnalyzeSign> result = new TreeMap<Integer, AnalyzeSign>();

		try {
			for(AnalyzeSign as : analyzeSignRepository.findAll()) {
				result.put(as.getAnalyzeId(), as);
			}
		} catch (Exception e) {
		}

		return result;
	}


}
