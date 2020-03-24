package com.wiserun.develop.processconfig.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;
import com.wiserun.develop.processconfig.model.ProcessConfigDTO;
import com.wiserun.develop.processconfig.service.ProcessConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "流程配置模块")
@RestController("ProcessConfigController")
@RequestMapping("/ProcessConfig/ProcessConfig")
public class ProcessConfigController {
	
	@Autowired
	private ProcessConfigService pcservice;
	
	@ApiOperation(value = "新增流程配置信息", notes = "新增流程配置信息")
    @RequestMapping(value = "/add", method = { RequestMethod.POST })
    public ResponseData add(@RequestBody ProcessConfigDTO container) {
		  int i= pcservice.add(container);
		  String r;
		  if(i==1) {
			   r="Succees";
		  }else {
			   r="Fail ";
		  }
		  Map<String, Object> map = new HashMap<>();
		  map.put("Succeed", r); 
        return FormatUtil.success(map);
	}
	
	@ApiOperation(value = "修改流程配置信息", notes = "修改流程配置信息")
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    public ResponseData update(@RequestBody ProcessConfigDTO container) {
		int i;
		String code = container.getSPC_Code();
		if(code!=null) {//如果没传过来code则为新增方法，拿到code则为修改方法
			i = pcservice.update(container);
		}else{
			i= pcservice.add(container);
		}
		  String r;
		  if(i==1) {
			   r="Succees";
		  }else {
			   r="Fail ";
		  }
		  Map<String, Object> map = new HashMap<>();
		  map.put("Succeed", r); 
        return FormatUtil.success(map);
	}
	
	@ApiOperation(value = "删除流程配置信息", notes = "删除流程配置信息")
    @RequestMapping(value = "/delete", method = { RequestMethod.POST })
    public ResponseData delete(@RequestBody ProcessConfigDTO container) {
		  int i= pcservice.delete(container);
		  String r;
		  if(i==1) {
			   r="Succees";
		  }else {
			   r="Fail ";
		  }
		  Map<String, Object> map = new HashMap<>();
		  map.put("Succeed", r); 
        return FormatUtil.success(map);
	}
	
	@ApiOperation(value = "流程配置信息列表", notes = "流程配置信息消息")
    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    public ResponseData list(@RequestBody ProcessConfigDTO container) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", pcservice.getPageList(container));
        map.put("total", pcservice.getTotal(container));
        
        return FormatUtil.success(map);
    }
}
