package com.wiserun.RestFul.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.temp.common.model.ResponseData;
import com.temp.permission.entity.User;
import com.temp.permission.mapper.UserMapper;
import com.wiserun.RestFul.mapper.RestFulApiMapper;
import com.wiserun.RestFul.model.RestFulApiModel;

@Service
public class RestFulApiCheckService {
	private static final Logger logger = LoggerFactory.getLogger(RestFulApiCheckService.class);
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RestFulApiMapper rfaMapper;
	
	public User login(String phone) {
		User user = userMapper.queryOneByPhone(phone);
		return user;
	}
	
	public ResponseData checkAuth( JSONObject userEntity,String api) {
		logger.info("com.wiserun.RestFul.service.RestFulApiCheck.checkAuth");
		logger.info("参数====》"+JSONObject.toJSONString(userEntity));
		logger.info("api====》"+api);
		ResponseData rd=new ResponseData();
		//根据需要加入验证代码
		String token=userEntity.getString("token");
		//String token="999";
		//根据token查询，证明此人有登录权限，并获取账号
		User user = userMapper.queryOneByToken(token);
		String user_id="";
		//加入权限验证
		//有此用户
		if(user!=null) {
			user_id=user.getUserPhone();
			logger.info("user_id====》"+user_id);
			RestFulApiModel model=new RestFulApiModel();
			model.setApi(api);
			model.setAuth_id(user_id);
			List<RestFulApiModel> list=rfaMapper.queryAuthByPhone(model);
			//有配置信息
			if(list!=null&&list.size()>0) {
				//可执行接口
				rd.setCode(0);
				rd.setMessage("有权限");
				logger.info("======输出list=====");
				for(RestFulApiModel rfam : list) {
					logger.info("======分隔数据=====");
					logger.info("id="+rfam.getId());
					logger.info("auth_type="+rfam.getAuth_type());
					logger.info("auth_id="+rfam.getAuth_id());
				}
				logger.info("======输出list结束=====");
			}else {
				rd.setCode(2);
				rd.setMessage("用户无权限");
			}
		}else {
			rd.setCode(2);
			rd.setMessage("用户不存在");
		}
		return rd;
	}
}
