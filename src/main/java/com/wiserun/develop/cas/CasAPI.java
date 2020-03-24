package com.wiserun.develop.cas;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.temp.permission.entity.User;
import com.temp.permission.mapper.UserMapper;
import com.temp.permission.service.LoginService;
import com.temp.permission.service.UserService;
import com.wiserun.develop.file.controller.FileController;
import com.wiserun.develop.roledepartment.service.RoledepartmentService;

import net.sf.json.JSONArray;



@Controller
@RequestMapping("/cas")
@Component
public class CasAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	// log1ger.info    S1ystem.out.println
	// http://192.168.111.45:8080/SJWOSB/cas/findUser
	@Value("${cas.client-host-url}")
	private  String SERVER_NAME = "http://192.168.111.100:8080";
	@Autowired
	private UserMapper umapper;
	@Autowired
	private RoledepartmentService rdservice;
	
	@RequestMapping(value = "/findUser",produces="application/json; charset=utf-8")
	@ResponseBody
	public Object find(HttpServletRequest request){
		logger.info("CasAPI.findUser======================");
		try {
		    //第一种方案
			//logger.info("---findUser----");
	        AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
	        logger.info("返回值");
	        logger.info(JSON.toJSONString(principal.getAttributes()));
	        Map<String, Object> attributes = principal.getAttributes();
	        logger.info("CasAPI.findUser.uuid====="+ principal.getAttributes().get("uuid"));
	        logger.info("CasAPI.findUser.name====="+ principal.getAttributes().get("name"));	                
	        request.getSession().setAttribute("name", principal.getAttributes().get("name"));
	        //更新session内的属性
	        request.getSession().setAttribute("userUuid", principal.getAttributes().get("uuid"));
	        request.getSession().setAttribute("token", principal.getAttributes().get("uuid"));
	        request.getSession().setAttribute("user", principal.getAttributes().get("name"));
	        request.getSession().setAttribute("name", principal.getAttributes().get("name"));
	        //--------------------得到所属组织名
	        String projects=  (String)principal.getAttributes().get("projects");
	        JSONArray json=JSONArray.fromObject(projects); 
	        net.sf.json.JSONObject jsonOne=null; 
	        String projectsname=null;
	        Map<String,String> updataMap = null; 
	        for(int i=0;i<json.size();i++){
	        	updataMap = new HashMap<String,String>();
	        	jsonOne = json.getJSONObject(i);
	        	projectsname=jsonOne.getString("name");
	        }
	        //在其他的服务器上运行可能会出现的到的组织乱码问题本地没有所以注释掉
	        /*projectsname = new String(projectsname.getBytes("GBK"),"utf-8");*/
	        logger.info("CasAPI.findUser.projectsname====="+ projectsname);

	        //更新数据库的用户的组织
	        String t=(String) principal.getAttributes().get("uuid");
	        User user=umapper.queryOne1(t);
	        user.setUser_department(projectsname);
	        umapper.update(user);
	        //通过角色与组织的关系表更新当前用户的角色
	        rdservice.saverole(t);
	        
	        return attributes;
	}catch(Exception e) {
			logger.info("CasAPI.findUser.Exception 获取登录人报错======================");
			logger.info(e.toString());
			Map<String, Object> attributes=new HashMap<>();
			attributes.put("name", "");
			attributes.put("uuid", "");
			return attributes;
		}
	}
	
	@RequestMapping(value = "/checkLogin")
	public String checkLogin(HttpServletRequest request){
		logger.info("CasAPI.checkLogin======================");
		String token= (String)request.getSession().getAttribute("userUuid");
		logger.info("CasAPI.checkLogin.token======================"+token);
		

		if(token!=null) {
			logger.info("CasAPI.checkLogin to Nothing");
			return "/index.html";
		}else {
			logger.info(SERVER_NAME+"/index.html");
			return "/index.html";
		}


	}
	
	@RequestMapping(value = "/logout",produces="application/json; charset=utf-8")
	@ResponseBody
	public String logout(HttpServletRequest request) {
		logger.info("CasAPI===========logout======================");
		
		request.getSession().invalidate();
		return "200";
	}
	
	public Object find1(HttpServletRequest request){

		logger.info("CasAPI.find1======================");
		try {
			//logger.info("---findUser----");
			request.setCharacterEncoding("UTF-8");
	        AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
	        logger.info("返回值");
	        logger.info(JSON.toJSONString(principal.getAttributes()));
	        Map<String, Object> attributes = principal.getAttributes();
	        
	        String extra =  (String) principal.getAttributes().get("extra");
	        logger.info("CasAPI.find1.extra====="+ extra);
	        JSONObject jsonObj = JSONObject.parseObject(extra);
	        logger.info("CasAPI.find1.fullname====="+ jsonObj.get("fullname"));
	        logger.info("CasAPI.find1.create_time====="+ jsonObj.get("create_time"));
	        logger.info("CasAPI.find1.fullname.getBytes===>"+
	        		new String(jsonObj.get("fullname").toString().getBytes("gbk"),"UTF-8"));
	        
	        User user=new User();
	        user.setUserPhone(principal.getAttributes().get("name").toString());
	        user.setUserName(principal.getAttributes().get("name").toString());
	        user.setUserEmail(principal.getAttributes().get("email").toString());
	        user.setUserToken(principal.getAttributes().get("uuid").toString());
	        
	        LoginService ls=new LoginService();
	        UserService us=new UserService();
	        UserDetails result=null;
	        Integer res=9;
	        try {
	        	result=ls.loadUserByUsername(user.getUserPhone());
	        }catch(Exception e) {
	        	logger.info("CasAPI.find1    查不到人");
	        	res=us.casAutoSave(user);
	        }
	        logger.info("人员信息创建后===>"+res);
	        if(result!=null) {
	        	res=9;
	        	res=us.casAutoUpdate(result);
	        	logger.info("人员信息更新后===>"+res);
	        }
	        //更新session内的属性
	        request.getSession().setAttribute("userUuid", principal.getAttributes().get("uuid"));
	        request.getSession().setAttribute("token", principal.getAttributes().get("uuid"));
	        request.getSession().setAttribute("user", principal.getAttributes().get("name"));
	        request.getSession().setAttribute("name", principal.getAttributes().get("name"));
            System.out.println("+++++++++++++++++++++++++++用户名token放入session:"+principal.getAttributes().get("uuid"));
            System.out.println("+++++++++++++++++++++++++++登陆用户名放入session:"+principal.getAttributes().get("name"));
	        return attributes;
		}catch(Exception e) {
			logger.info("CasAPI.find1.Exception 获取登录人报错======================》");
			logger.info(e.toString());
			Map<String, Object> attributes=new HashMap<>();
			attributes.put("name", "");
			attributes.put("uuid", "");
			return attributes;
		}
	}


}