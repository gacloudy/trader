package com.trader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController extends CommonController {

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {

		mav.setViewName("index");

		mav.addObject("countByPriceDate", stockDateHistoryRepository.countByPriceDate());

		return mav;
	}

}