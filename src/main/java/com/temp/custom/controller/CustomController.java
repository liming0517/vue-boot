package com.temp.custom.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;
import com.temp.custom.service.CustomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "演示用客户管理")
@RestController
@RequestMapping("/custom")
public class CustomController {

	@Autowired
	private CustomService service;
	
	@ApiOperation(value = "获取客户列表", notes = "获取客户列表")
    @RequestMapping(value = "/info/list", method = { RequestMethod.POST })
    public ResponseData getList() throws Exception {
		//业务逻辑，决定使用哪个service及哪个方法
		Map<String, Object> map = new HashMap<>();
		map.put("list",service.getCustomList());
		//输出结果
        return FormatUtil.success(map);
    }
}
