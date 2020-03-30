package com.temp.custom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;
import com.temp.custom.model.Custom;
import com.temp.custom.service.CustomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "演示用客户管理")
@RestController
@RequestMapping("/custom")
public class CustomController {
	 private static final Logger logger = LoggerFactory.getLogger(CustomController.class);
	@Autowired
	private CustomService service;
	
	@ApiOperation(value = "获取客户列表", notes = "获取客户列表")
    @RequestMapping(value = "/info/list", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getList() throws Exception {
		logger.info("=========CustomController.getList========");
		//业务逻辑，决定使用哪个service及哪个方法
		Map<String, Object> map = new HashMap<>();
		//map.put("list",service.getCustomList());
		List<Custom> list=service.getCustomList();
		map.put("list",list);
		for(int i=0;i<list.size();i++) {
			Custom cus=list.get(i);
			logger.info("第"+i+"个");
			logger.info("name="+cus.getC_Name());
		}
		
		logger.info(map.toString());
		logger.info("=========CustomController.getList========");
		//输出结果
        return FormatUtil.success(map);
    }
}
