package com.trader.util;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.trader.entity.db.StockMst;
import com.trader.repositories.StockMstRepository;



@Service
@CacheConfig(cacheNames = {"stockMstInfo", "stockMstMap"})
public class CacheUtil {

	@Autowired
	CacheManager cacheManager;

	@Autowired
	protected StockMstRepository stockMstRepository;

	@Scheduled(cron = "0 0 * * * *")
	public void evictAllcachesAtIntervalsAll() {
		for(String nm : cacheManager.getCacheNames()) {
			cacheManager.getCache(nm).clear();
		}
	}

	@Cacheable(value="stockMstMap", sync=true)
	public StockMst getStockInfo(int code) {
		return stockMstRepository.findByCode(code);
	}
	
	@Cacheable(value="stockMstMap", sync=true)
	public Map<Integer, StockMst> getStockMap() {
		Map<Integer, StockMst> result = new TreeMap<Integer, StockMst>();

		for(StockMst mst : stockMstRepository.findAll()) {
			result.put(mst.getCode(), mst);
		}
		
		return result;
	}

}
