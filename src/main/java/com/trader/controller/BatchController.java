package com.trader.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

		addLog("譬ｪ萓｡譌･莉倥ユ繝ｼ繧ｿ逋ｻ骭ｲ髢句ｧ�");

		try {
			String dateKey = DateUtil.getyyyyMMddStrFromDate(new Date());
	
			List<StockMst> list = stockMstRepository.findAll();
			
			for(StockMst mst : list) {
				try {
					List<String> httpList =
							HttpUtil.getStockHTTP(mst.getCode());
					
					if(!httpList.isEmpty() && HttpUtil.isYahooStarted(httpList)) {
						StockDateHistoryPK pk =
								new StockDateHistoryPK(mst.getCode(), dateKey);
						
						StockDateHistory stockDateHistory =
								new StockDateHistory();
						stockDateHistory.setPk(pk);
						stockDateHistory.setYesterdayPrice(HttpUtil.getYesterdayPrice(httpList));
						stockDateHistory.setStartPrice(HttpUtil.getStartPrice(httpList));
						stockDateHistory.setEndPrice(HttpUtil.getPrice(httpList));
						stockDateHistory.setHighestPrice(HttpUtil.getHighestPrice(httpList));
						stockDateHistory.setLowestPrice(HttpUtil.getLowestPrice(httpList));
						stockDateHistory.setAmount(HttpUtil.getAmount(httpList));
						
						stockDateHistoryRepository.save(stockDateHistory);
					}
				} catch (Exception e) {
					addLog("譬ｪ萓｡譌･莉倥ユ繝ｼ繧ｿ逋ｻ骭ｲ縺ｫ螟ｱ謨暦ｼ�" + mst.getCode());
				}
			}
			
			addLog("譬ｪ萓｡譌･莉倥ユ繝ｼ繧ｿ逋ｻ骭ｲ邨ゆｺ�");
	
		} catch (Exception e) {
			result.put("result", false);
		}			

		result.put("result", true);

		return result;
	}
}