package com.wiserun.develop.operate.controller;

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
import com.wiserun.develop.operate.model.OperateDTO;
import com.wiserun.develop.operate.service.OperateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "操作管理")
@RestController("OperateController")
@RequestMapping("/Operate/Operate")
public class OperateController {
	
	@Autowired
	private OperateService Oservice;
	
	
	
	@ApiOperation(value = "保存操作表信息", notes = "保存操作表信息")
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    public ResponseData update(@RequestBody OperateDTO container) {
		String code=container.getOperate_id();
		int i;
		if(code!=null) {
			i= Oservice.update(container);
		}else {
			i= Oservice.add(container);
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
	
	@ApiOperation(value = "删除操作表信息", notes = "删除操作表信息")
    @RequestMapping(value = "/delete", method = { RequestMethod.POST })
    public ResponseData delete(@RequestBody OperateDTO container) {
		  int i= Oservice.delete(container);
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
	
	@ApiOperation(value = "操作表信息列表", notes = "根据参数查询操作表消息")
    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    public ResponseData list(@RequestBody OperateDTO container) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", Oservice.getPageList(container));
        map.put("total", Oservice.getTotal(container));
        
        return FormatUtil.success(map);
    }
	
	
	@ApiOperation(value = "通过model找出列表数据", notes = "根据参数查询操作表消息")
    @RequestMapping(value = "/operatelist", method = { RequestMethod.POST })
    public ResponseData operatelist(@RequestBody OperateDTO container) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", Oservice.getPageListBymodel(container));
        return FormatUtil.success(map);
    }
}
