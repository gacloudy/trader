package com.trader.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.trader.Constants;
import com.trader.entity.db.AnalyzeSign;
import com.trader.entity.db.StockDateAvg;
import com.trader.entity.db.StockDateAvgPK;
import com.trader.entity.db.StockDateCross;
import com.trader.entity.db.StockDateHistory;
import com.trader.entity.db.StockDateHistoryPK;
import com.trader.entity.db.StockMst;
import com.trader.util.DateUtil;
import com.trader.util.HttpUtil;


@Controller
public class BatchController extends CommonController {

	@RequestMapping("/batch")
	public ModelAndView batch(ModelAndView mav) {

		mav.setViewName("batch");

		return mav;
	}


	@RequestMapping(value = "/batch/registerDateHistory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> batchRegisterDateHistory(ModelAndView mav) {

		Map<String, Object> result = new HashMap<String, Object>();

		addLog("株価日付テータ登録開始");

		try {
			String dateKey = DateUtil.getyyyyMMddStrFromDate(new Date());
	
			for(StockMst mst : cUtil.getStockMap().values()) {
				try {
					if(mst.getGatheringFlg() != 1) {
						continue;
					}
					List<String> httpList =
							HttpUtil.getStockHTTP(mst.getCode());
					
					if(!httpList.isEmpty() && HttpUtil.isYahooStarted(httpList)) {
						double endPrice = HttpUtil.getPrice(httpList, mst.getStockName());
						if(endPrice <= 0.0) {
							continue;
						}
								
						StockDateHistoryPK pk =
								new StockDateHistoryPK(mst.getCode(), dateKey);
						
						StockDateHistory stockDateHistory =
								new StockDateHistory();
						stockDateHistory.setPk(pk);
						stockDateHistory.setYesterdayPrice(HttpUtil.getYesterdayPrice(httpList));
						stockDateHistory.setStartPrice(HttpUtil.getStartPrice(httpList));
						stockDateHistory.setEndPrice(endPrice);
						stockDateHistory.setHighestPrice(HttpUtil.getHighestPrice(httpList));
						stockDateHistory.setLowestPrice(HttpUtil.getLowestPrice(httpList));
						stockDateHistory.setAmount(HttpUtil.getAmount(httpList));
						
						stockDateHistoryRepository.save(stockDateHistory);
					}
				} catch (Exception e) {
					addLog("株価日付テータ登録に失敗：" + mst.getCode());
				}
			}
			
			addLog("株価日付テータ登録終了");
	
		} catch (Exception e) {
			result.put("result", false);
		}			

		result.put("result", true);

		return result;
	}
	
	@RequestMapping(value = "/batch/registerAvg", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> batchRegisterAvg(ModelAndView mav) {

		Map<String, Object> result = new HashMap<String, Object>();

		addLog("株価平均データ登録開始");

		try {

			String dateKey = DateUtil.getyyyyMMddStrFromDate(new Date());

			for(StockMst mst : cUtil.getStockMap().values()) {
				try {
					Optional<StockDateHistory> stockDateHistory =
							stockDateHistoryRepository.findById(new StockDateHistoryPK(mst.getCode(), dateKey));
					if(stockDateHistory.isEmpty()) {
						continue;
					}
					
					List<StockDateHistory> list =
							stockDateHistoryRepository.findByCodeAndPriceDateLessThanEqual(mst.getCode(), dateKey);
					
					for(int i = 2; i <= Constants.LONGEST_SPAN; i++) {
						if(list.size() >= i) {
							double sum = 0.0;
							for(int j = 0; j < i; j++) {
								sum += list.get(j).getEndPrice();
							}
							
							StockDateAvg StockDateAvg =
									new StockDateAvg();
							StockDateAvg.setPk(new StockDateAvgPK(mst.getCode(), dateKey, i));
							StockDateAvg.setAvgPrice(sum / i);

							stockDateAvgRepository.save(StockDateAvg);
						} else {
							break;
						}
					}

				} catch (Exception e) {
					addLog("株価平均テータ登録に失敗：" + mst.getCode());
				}
			}
			addLog("株価平均テータ登録終了");
	
		} catch (Exception e) {
			result.put("result", false);
		}			

		result.put("result", true);

		return result;
	}
	
	@RequestMapping(value = "/batch/registerCross", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> batchRegisterCross(ModelAndView mav) {

		Map<String, Object> result = new HashMap<String, Object>();

		addLog("ゴールデンクロスデータ登録開始");

		try {

			String dateKey = DateUtil.getyyyyMMddStrFromDate(new Date());

			for(StockMst mst : cUtil.getStockMap().values()) {
				try {
					for(int i = 2; i <= Constants.LONGEST_SPAN; i++) {
						for(int j = 2; j <= Constants.LONGEST_SPAN; j++) {
							if(i <= j) {
								continue;
							}

							analyzeCross(mst.getCode(), dateKey, i, j);
						}
					}
				} catch (Exception e) {
					addLog("ゴールデンクロステータ登録に失敗：" + mst.getCode());
				}
			}
			addLog("ゴールデンクロステータ登録終了");
	
		} catch (Exception e) {
			result.put("result", false);
		}			

		result.put("result", true);

		return result;
	}
	
	@RequestMapping(value = "/batch/registerAnalyze", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> registerAnalyze(ModelAndView mav) {

		Map<String, Object> result = new HashMap<String, Object>();

		addLog("売買結果 登録開始");

		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -5);
			String dateKey = DateUtil.getyyyyMMddStrFromDate(cal.getTime());

			for(StockMst mst : cUtil.getStockMap().values()) {
				addLog("売買結果 登録開始：" + mst.getCode());
				for(AnalyzeSign as : cUtil.getAnalyzeSignMap().values()) {
					try {
						for(StockDateCross cross : stockDateCrossRepository.findByCodeAndPriceDate(mst.getCode(), dateKey)) {
							if(as.getSign().equals(cross.getSign())) {
								registerAnalyzeResult(mst.getCode(), cross, as);
							}
						}
					} catch (Exception e) {
						addLog("売買結果 登録に失敗：" + mst.getCode());
					}
				}
			}
			addLog("売買結果 登録終了");
		} catch (Exception e) {
			result.put("result", false);
		}			

		result.put("result", true);

		return result;
	}
}