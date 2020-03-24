package com.wiserun.develop.roledepartment.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temp.permission.entity.RoleUser;
import com.temp.permission.entity.User;
import com.temp.permission.mapper.RoleUserMapper;
import com.temp.permission.mapper.UserMapper;
import com.wiserun.develop.roledepartment.mapper.RoledepartmentMapper;
import com.wiserun.develop.roledepartment.model.Roledepartment;



@Service
public class RoledepartmentService {
	
	@Autowired
	private RoledepartmentMapper rdmapper;
	@Autowired
	private UserMapper umapper;
	@Autowired
	private RoleUserMapper rumapper;
	/**
	 *通过当前用户的所属组织更新当前用户的角色信息
	 *创建日期 2019-9-18
	 *liangbenjian 
	 */
	public Integer saverole(String token){
		Roledepartment  r=new Roledepartment();
		//找到当前用户的详细信息
		User u=umapper.queryOne1(token);
		r.setRole_department_departmentid(u.getUser_department());
		List<Roledepartment> Rdlist=null;
		//通过用户的组织找到组织对应的角色
		if(u.getUser_department()!=null) {
			 Rdlist=rdmapper.queryList(r);
		}	
		//System.out.println("组织对应角色列表状态："+Rdlist.isEmpty()+"------------------------------------------------------------");
		if(u.getUser_department()==null||Rdlist.isEmpty()) {
			//如果用户没有所属组织或者所属组织没有对应角色则直接赋予租户角色
			RoleUser zuhu=new RoleUser();
			zuhu.setRoleUserRoleId(2);
			zuhu.setRoleUserUserId(u.getUserId());
			rumapper.add(zuhu);
			System.out.println("------------------当前用户角色更新完毕-租户-------------------");
			return null;
		}
		for(int i=0;i<Rdlist.size();i++) {
			//循环拿到角色并且在角色与用户关系表中查找是否存在该信息，若不存在则创建
			RoleUser ru=new RoleUser();
			int Roleid=Integer.parseInt(Rdlist.get(i).getRole_department_roleid());
			ru.setRoleUserRoleId(Roleid);
			ru.setRoleUserUserId(u.getUserId());
			RoleUser newru=new RoleUser();
			newru=rumapper.queryOne1(ru);
			if(newru==null) {
				//如果在数据库的关系表中没有这条数据则添加这条数据
				rumapper.add(ru);
			}			
		}
		System.out.println("------------------当前用户角色更新完毕-------------------");
		return null;		       
	}

}
