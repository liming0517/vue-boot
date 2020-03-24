package com.wiserun.develop.message.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;
import com.wiserun.develop.message.model.MessageDTO;
import com.wiserun.develop.message.service.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "通知消息模块")
@RestController("MessageController")
@RequestMapping("/message/message")
public class MessageController {

	
	@Autowired
	private MessageService service;
	
	
	@ApiOperation(value = "通知消息列表", notes = "根据参数查询通知消息")
    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    public ResponseData list(@RequestBody MessageDTO container) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", service.getPageList(container));
        map.put("total", service.getTotal(container));
        
        return FormatUtil.success(map);
    }
	
	
	@ApiOperation(value = "通知消息详细信息", notes = "根据编号查询通知消息详细信息并更新已阅人")
    @RequestMapping(value = "/info", method = { RequestMethod.POST })
    public ResponseData getOne(@RequestBody MessageDTO container) {
        return FormatUtil.success(service.getOne(container));
    }
	
	@ApiOperation(value = "通知消息标为已读", notes = "根据编号查询通知消息详细信息并更新已阅人")
    @RequestMapping(value = "/read", method = { RequestMethod.POST })
    public ResponseData read(@RequestBody MessageDTO container) {
		  int i= service.read(container);
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
	
	@ApiOperation(value = "通知消息标为未读", notes = "根据编号查询通知消息详细信息并更新已阅人")
    @RequestMapping(value = "/unread", method = { RequestMethod.POST })
    public ResponseData unread(@RequestBody MessageDTO container) {
		  int i= service.unread(container);
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
}
