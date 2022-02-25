package com.trader.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trader.entity.db.ApplicationLog;


@Controller
public class LogController extends CommonController {

	@RequestMapping("/log")
	public ModelAndView log(ModelAndView mav) {

		mav.setViewName("log");
		
		List<ApplicationLog> logList =
				applicationLogRepository.findAllByOrderByDateDesc();

		mav.addObject("log", logList);
		
		return mav;
	}

	@RequestMapping("/log/delete")
	public ModelAndView logDelete(ModelAndView mav) {
		
		applicationLogRepository.deleteAll();
		
		return log(mav);
	}
}