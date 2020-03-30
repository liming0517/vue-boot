package com.wiserun.RestFul.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.temp.common.model.ResponseData;
import com.temp.permission.entity.User;
import com.wiserun.RestFul.service.RestFulApiCheckService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "restful权限接口")
@RestController
@RequestMapping("/restAuth")
public class RestFulApi {
	 private static final Logger logger = LoggerFactory.getLogger(RestFulApi.class);
	 
	 @Autowired
	 private RestFulApiCheckService rfac;
	 
	@ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login", method = { RequestMethod.POST },produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData login(@RequestBody JSONObject userEntity) throws Exception {
		logger.info("=========RestFulApi.login========");
		logger.info("参数====》"+JSONObject.toJSONString(userEntity));
		String phone=userEntity.getString("phone");
		String password=userEntity.getString("password");
		logger.info("phone==="+phone);
		logger.info("password==="+password);
		
		//返回的结果
		String code="";
		JSONObject data=new JSONObject();
		String message="";
		//User user = mapper.queryOneByPhone(phone);
		User user = rfac.login(phone);
        if (user == null) {
        	//用户不存在
        	code="10002";
        	message="用户名或密码输入错误，登录失败";
        }
        else if (!(new BCryptPasswordEncoder()).matches(password, user.getUserPassword())) {
        	//密码错误
        	code="10002";
        	message="用户名或密码输入错误，登录失败";
        }
        else {
        	//登录成功
        	code="0";
        	message="登录成功";
        	data.put("token",user.getUserToken());
        	data.put("phone",phone);
        }

		logger.info("code==="+code);
		logger.info("data==="+data);
		logger.info("message==="+message);
		logger.info("=========RestFulApi.login========");
		//输出结果
		//返回前台，根据需要替换
		ResponseData rd=new ResponseData();
		rd.setCode(Integer.parseInt(code));
		rd.setData(data);
		rd.setMessage(message);
		return rd;
    }
}
