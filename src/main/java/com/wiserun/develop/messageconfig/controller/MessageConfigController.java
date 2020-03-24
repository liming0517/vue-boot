package com.wiserun.develop.messageconfig.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;
import com.wiserun.develop.messageconfig.model.MessageConfigDTO;
import com.wiserun.develop.messageconfig.service.MessageConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "通知消息配置模块")
@RestController("MessageConfigController")
@RequestMapping("/message/messageconfig")
public class MessageConfigController {
	
	@Autowired
	private MessageConfigService mcservice;
	
/*	@ApiOperation(value = "新增通知消息配置信息", notes = "新增通知消息配置信息")
    @RequestMapping(value = "/add", method = { RequestMethod.POST })
    public ResponseData add(@RequestBody MessageConfigDTO container) {
		  int i= mcservice.add(container);
		  String r;
		  if(i==1) {
			   r="Succees";
		  }else {
			   r="Fail ";
		  }
		  Map<String, Object> map = new HashMap<>();
		  map.put("Succeed", r); 
        return FormatUtil.success(map);
	}*/
	
	@ApiOperation(value = "保存通知消息配置信息", notes = "保存通知消息配置信息")
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    public ResponseData update(@RequestBody MessageConfigDTO container) {
		String code=container.getSMC_Code();
		int i;
		if(code!=null) {
			i= mcservice.update(container);
		}else {
			i= mcservice.add(container);
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
	
	@ApiOperation(value = "删除通知消息配置信息", notes = "删除通知消息配置信息")
    @RequestMapping(value = "/delete", method = { RequestMethod.POST })
    public ResponseData delete(@RequestBody MessageConfigDTO container) {
		  int i= mcservice.delete(container);
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
	
	@ApiOperation(value = "通知消息配置信息列表", notes = "根据参数查询通知消息")
    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    public ResponseData list(@RequestBody MessageConfigDTO container) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", mcservice.getPageList(container));
        map.put("total", mcservice.getTotal(container));
        
        return FormatUtil.success(map);
    }
	
	@ApiOperation(value = "通知消息关联模块列表", notes = "通知消息关联模块列表")
    @RequestMapping(value = "/OperateList", method = { RequestMethod.POST })
    public ResponseData queryOperateList() {
        Map<String, Object> map = new HashMap<>();
        map.put("list", mcservice.queryOperateList());            
        return FormatUtil.success(map);
    }

}
