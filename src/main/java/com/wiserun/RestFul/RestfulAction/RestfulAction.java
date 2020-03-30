package com.wiserun.RestFul.RestfulAction;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.temp.common.model.ResponseData;

import io.swagger.annotations.Api;

@Api(description = "Restful接口调用实例")
@Controller
public class RestfulAction  {
	 private static final Logger logger = LoggerFactory.getLogger(RestfulAction.class);
	@Autowired
	private ClientAction ca;
	//放入配置文件
	@Value("${restful.systemIP}")
	private String systemIP="";
	@Value("${restful.department_list}")
	private String department_list="";
	@Value("${restful.user_login}")
	private String user_login="";
	@Value("${restful.custom_info_list}")
	private String custom_info_list= "";
	
	//http://192.168.111.45:8080/RestFulAPI/restfultest
	@RequestMapping(value = "/restfultest", method = { RequestMethod.POST })
    @ResponseBody
	public ResponseData testRestFul(HttpServletRequest request) {
		 logger.info("====RestfulAction.testRestFul.testRestFul====");
		String apiURL=systemIP+custom_info_list;
		//logger.info("sessionID==="+request.getSession().getId());
		//logger.info("sessionToken==="+request.getSession().getAttribute("token"));
		//构成传入参数
		JSONObject parm=new JSONObject();
		parm.put("apiURL",apiURL);
		parm.put("name","一号客户");
		parm.put("token",request.getSession().getAttribute("token"));
		//放入post方法执行
		JSONObject result=ca.ClientPost(parm);
		//解析返回的结果
		String code=result.getString("code");
		JSONObject data=result.getJSONObject("data");
		String message=result.getString("message");
		/*
		logger.info("code==="+code);
		logger.info("data==="+data);
		logger.info("message==="+message);
		logger.info("====RestfulAction.testRestFul.testRestFul.result====");
		*/
		//返回前台，根据需要替换
		ResponseData rd=new ResponseData();
		rd.setCode(Integer.parseInt(code));
		rd.setMessage(message);
		rd.setData(data);
		return rd;
	}
	//http://192.168.111.45:8080/RestFulAPI/restfulAuth
	@RequestMapping(value = "/restfulAuth", method = { RequestMethod.POST })
    @ResponseBody
	public ResponseData restfulAuth(HttpServletRequest request) {
		// logger.info("RestfulAction.testRestFul.restfulAuth");
		String apiURL=systemIP+user_login;
		//构成传入参数
		JSONObject parm=new JSONObject();
		parm.put("apiURL",apiURL);
		parm.put("phone","U13800000000");
		parm.put("password","123456");
		//放入post方法执行
		JSONObject jsonObject=ca.ClientPost(parm);
		//解析返回的结果
		String code=jsonObject.getString("code");
		JSONObject data=jsonObject.getJSONObject("data");
		String message=jsonObject.getString("message");
		/*		
		logger.info("code==="+code);
		logger.info("data==="+data);
		logger.info("message==="+message);
		logger.info("sessionID==="+request.getSession().getId());
		logger.info("=====RestfulAction.testRestFul.restfulAuth.result.jsonObject====");
		 */
		//登录成功时保存token到session
		if("0".equalsIgnoreCase(code)) {
			request.getSession().setAttribute("token", data.getString("token"));
			request.getSession().setAttribute("user", data.getString("phone"));
		 }
		//返回前台，根据需要替换
		ResponseData rd=new ResponseData();
		rd.setCode(Integer.parseInt(code));
		rd.setMessage(message);
		rd.setData(data);
		 return rd;
	}
}
