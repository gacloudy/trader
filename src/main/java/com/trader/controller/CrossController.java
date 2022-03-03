package com.trader.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.trader.Constants;
import com.trader.entity.db.StockDateCross;
import com.trader.entity.db.StockMst;
import com.trader.util.DateUtil;


@Controller
public class CrossController extends CommonController {

	@RequestMapping("/cross")
	public ModelAndView log(ModelAndView mav) {

		mav.setViewName("cross");
				
		return mav;
	}

	@RequestMapping(value = "/cross/exe", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> exe(
			@RequestParam(value="code", required = true)int code,
			@RequestParam(value="date", required = true)String date,
			@RequestParam(value="longSpan", required = true)int longSpan,
			@RequestParam(value="shortSpan", required = true)int shortSpan
			) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", analyzeCross(code, date, longSpan, shortSpan));

		return result;

	}

	@RequestMapping(value = "/cross/exeAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> exeAll(
			@RequestParam(value="date", required = true)String date) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		stockDateCrossRepository.deleteByPriceDate(date);

		for(StockMst mst : cUtil.getStockMap().values()) {
			for(int i = 2; i <= Constants.LONGEST_SPAN; i++) {
				for(int j = 2; j <= Constants.LONGEST_SPAN; j++) {
					if(i <= j) {
						continue;
					}

					
					if(analyzeCross(mst.getCode(), date, i, j)) {
						
						Map<String, Object> map =
								new HashMap<String, Object>();
						map.put("code", mst.getCode());
						map.put("longSpan", i);
						map.put("shortSpan", j);
						
						result.add(map);
					}
					
				}
			}
		}
		return result;

	}

}