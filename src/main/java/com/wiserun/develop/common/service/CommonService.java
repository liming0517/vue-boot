package com.wiserun.develop.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.temp.permission.entity.RoleUser;
import com.temp.permission.entity.User;
import com.temp.permission.mapper.RoleUserMapper;
import com.temp.permission.mapper.UserMapper;
import com.wiserun.common.dao.BaseDao;
import com.wiserun.common.service.CrudService;
import com.wiserun.common.util.StringUtil;
import com.wiserun.develop.common.model.UserMsg;
import com.wiserun.develop.message.mapper.MessageMapper;
import com.wiserun.develop.message.model.Message;

import app.dao.OrderInfoDao;
import app.entity.OrderInfo;


@Service
public class CommonService {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private UserMapper usermapper;
	@Autowired
	private RoleUserMapper roleusermapper;
	@Autowired
    private Mapper mapperTrans;
	@Autowired
	private MessageMapper Mmapper;
	
	//拿到当前登录人的详细信息除了密码
	public UserMsg getMsg() {
		
		UserMsg um=new UserMsg();
		//通过token查到当前用户的详细信息
		String token=(String) session.getAttribute("token");
		User u=usermapper.queryOne1(token);
		um.setUsercode(u.getUserPhone());
		um.setUsername(u.getUserName());
		um.setToken(token);
		um.setEmail(u.getUserEmail());
		um.setDepartment(u.getUser_department());		
		List<RoleUser> list=roleusermapper.queryListByUserId(u.getUserId());
		String role="";
		for(int i=0;i<list.size();i++) {
			if(i==0) {
				role=list.get(i).getRoleUserRoleId()+"";
			}else {
				role=role+","+list.get(i).getRoleUserRoleId();
			}
			
		}
		um.setRole(role);
		um.setRolelist(fg(role,","));
		um.setDepartmentlist(fg(u.getUser_department(),","));
		return um;
		
	}
	//传入需要分割的字符串和分割用的符号，返回String数组
	public String[] fg(String str,String fh) {
		String[] result = null;
		if(str.indexOf(fh)!=-1) {
			 result = str.split(fh);
		}else{
			result=new String[1];
			result[0]=str;
		}
		
		return result;		
	}
	
	public Double test() throws Exception{
		StringUtil su = null; 
		Message ms=new Message();
		ms.setSM_Code("2c1cd0f3-f1c1-46fc-93c9-81cae6522bfd");
		UserMsg um=new UserMsg();
		UserMsg um2=new UserMsg();
		um.setUsercode("1");
		um.setUsername("2");
		um.setToken("3");
		um.setEmail("4");
		um.setDepartment("5");
		System.out.println("测试=========");
		return null;
	}
	

}
