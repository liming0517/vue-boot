package com.wiserun.RestFul.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;
import com.temp.custom.controller.CustomController;
import com.temp.custom.model.Custom;
import com.temp.custom.service.CustomService;
import com.wiserun.RestFul.service.RestFulApiCheckService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "restful客户管理接口")
@RestController
@RequestMapping("/customapi")
public class CustomApiController {
	 private static final Logger logger = LoggerFactory.getLogger(CustomController.class);
		@Autowired
		private CustomService service;
		@Autowired
		private RestFulApiCheckService rfac;
		
		@ApiOperation(value = "获取客户列表", notes = "获取客户列表")
	    @RequestMapping(value = "/list", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ResponseData getList(@RequestBody JSONObject userEntity) throws Exception {
			logger.info("=========CustomApiController.getList========");
			//先验证权限
			ResponseData rd=rfac.checkAuth(userEntity,"/customapi/list");
			//不为0时为出错
			if(rd.getCode()!=0) {
				//直接返回错误信息
				return rd;
			}
			//继续执行

			//业务逻辑，决定使用哪个service及哪个方法
			Map<String, Object> map = new HashMap<>();
			List<Custom> list=service.getCustomList();
			map.put("list",list);
			logger.info(map.toString());
			logger.info("=========CustomApiController.getList========");
			//输出结果
	        return FormatUtil.success(map);
	    }
}
