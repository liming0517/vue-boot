package com.temp.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestFulAPIController {
	private static final Logger logger = LoggerFactory.getLogger(RestFulAPIController.class);
			
	public String nonLogin(HttpServletRequest request){
		logger.info("RestFulAPIController.nonLogin======================");

		return "";
	}
}
