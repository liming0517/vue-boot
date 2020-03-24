package com.wiserun.develop.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wiserun.develop.process.model.Process;
import com.wiserun.develop.process.service.ProcessService;
import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "审核信息模块")
@RestController("ProcessController")
@RequestMapping("/resourve/process")
public class ProcessController {
	@Autowired
	private ProcessService service;
	
	
	


}
