package com.wiserun.develop.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;
import com.wiserun.develop.common.service.CommonService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "公共接口")
@RestController("CommonController")
@RequestMapping("/WiserunCommon")
public class CommonController {
	
	@Autowired
	private CommonService service;
	
	@ApiOperation(value = "当前登录人详细信息", notes = "当前登录人详细信息")
    @RequestMapping(value = "/UserMsg", method = { RequestMethod.POST })
    public ResponseData getOne() throws Exception {
        return FormatUtil.success(service.getMsg());
    }

}
