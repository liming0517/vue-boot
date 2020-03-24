package com.wiserun.develop.processconfig.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.temp.permission.entity.Role;
import com.temp.permission.mapper.RoleMapper;
import com.wiserun.develop.messageconfig.model.MessageConfig;
import com.wiserun.develop.messageconfig.model.MessageConfigDTO;
import com.wiserun.develop.processconfig.mapper.ProcessConfigMapper;
import com.wiserun.develop.processconfig.model.ProcessConfig;
import com.wiserun.develop.processconfig.model.ProcessConfigDTO;

@Service
public class ProcessConfigService {
	
	@Autowired
    private Mapper mapperTrans;
	@Autowired
	private ProcessConfigMapper mapper;
	
	@Autowired
	private HttpSession session;
	@Autowired
	private RoleMapper rmapper;
	
	
	/**
	 *根据编号查询对应的一条详细数据
	 *创建日期 2019-8-8
	 *liangbenjian 
	 */
	public ProcessConfig getOne(ProcessConfigDTO container) {
		return mapper.queryOne1(container.getSPC_Code());
	}
	/**
	 *根据条件查询对应的一条详细数据
	 *创建日期 2019-8-8
	 *liangbenjian 
	 */
	public ProcessConfig getOne2(ProcessConfigDTO container) {
		ProcessConfig p=new ProcessConfig();
		p.setSPC_Version(container.getSPC_Version());
		p.setSPC_Step(container.getSPC_Step());
		return mapper.queryOne2(p);
	}
	
	
	/**
	 *新增一条信息
	 *创建日期 2019-8-8
	 *liangbenjian 
	 */
	public Integer add(ProcessConfigDTO container) {
		ProcessConfig processconfig=new ProcessConfig();
		Date now = new Date(); 
		String username=(String) session.getAttribute("user");//拿到session里存储的登录人名字
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//创建日期的格式
		String hehe = dateFormat.format(now);
		UUID uuid = UUID.randomUUID();
        String strID = uuid.toString();	
        processconfig.setSPC_Code(strID);
        processconfig.setSPC_CreateTime(hehe);
        processconfig.setSPC_CreateUser(username);
        processconfig.setSPC_State("1");
        processconfig.setSPC_Model(container.getSPC_Model());
        processconfig.setSPC_Version(container.getSPC_Version());
        processconfig.setSPC_Step(container.getSPC_Step());
        //拼接数组
        String arr[]=container.getSPC_Rolelist();
        String str = "";
        for(int i=0;i<arr.length;i++) {
        	str=str+arr[i]+",";
        }       
        str=str.substring(0, str.length()-1);
		processconfig.setSPC_Role(str);
        return mapper.add(processconfig);
        
    }
	/**
	 *根据编号查询对应的一条详细数据然后更新
	 *创建日期 2019-8-8
	 *liangbenjian 
	 */
	public Integer update(ProcessConfigDTO container) {
		String arr[]=container.getSPC_Rolelist();	
        String str = "";
        for(int i=0;i<arr.length;i++) {
        	str=str+arr[i]+",";
        }       
        str=str.substring(0, str.length()-1);
		ProcessConfig processconfig=mapper.queryOne1(container.getSPC_Code());
        processconfig.setSPC_State(container.getSPC_State());
        processconfig.setSPC_Model(container.getSPC_Model());
        processconfig.setSPC_Version(container.getSPC_Version());
        processconfig.setSPC_Step(container.getSPC_Step());
        processconfig.setSPC_Role(str);
        return mapper.update(processconfig);
        
    }
	
	/**
	 *删除流程配置的配置信息
	 *创建日期 2019-09-23
	 *liangbenjian 
	 */
	public Integer delete(ProcessConfigDTO container) {
		//System.out.println("--------------删除方法code："+container.getSPC_Code());
		return mapper.delete1(container.getSPC_Code());
		
	}
	
	/**
	 *可根据是否有条件查询所有流程配置表的数据
	 *创建日期 2019-09-23
	 *liangbenjian 
	 */
	public List<ProcessConfigDTO> getPageList(ProcessConfigDTO container){
		ProcessConfig ProcessConfigSearch= mapperTrans.map(container,ProcessConfig.class);	//封装数据到对象里
		Map<String, Object> map = new HashMap<>();
		    String username=(String) session.getAttribute("user");
			map.put("SPC_State", container.getSPC_State());         //map中的key值与映射文件中的select语句#{}占位符中的值需要一一对应
			map.put("SPC_CreateUser",container.getSPC_CreateUser());	
			map.put("SPC_Model", container.getSPC_Model());
			map.put("SPC_Version", container.getSPC_Version());
			map.put("SPC_CreateTimeStart", container.getSPC_CreateTimeStart());
			map.put("SPC_CreateTimeEnd", container.getSPC_CreateTimeEnd());			
	        map.put("page", container.getCurrentPage());
	        map.put("size", container.getLimit());
/*	       System.out.println(map.get("model"));
	       System.out.println(map.get("LoginUser"));*/
	        List<ProcessConfig> ProcessConfigList= mapper.queryPageList(map);            //将条件全部给方法得到查询出的数据
	        List<ProcessConfigDTO> list = new ArrayList<>();                     //放入list
	        for (ProcessConfig processconfig : ProcessConfigList) {
	        	ProcessConfigDTO dto = mapperTrans.map(processconfig, ProcessConfigDTO.class);
	        	JSONObject jso=new JSONObject();	        	
	        	String[] newStr=dto.getSPC_Role().split(",");
	        	String Role="";
	        	if(newStr!=null||newStr.length!=0) {
	        		for(int i=0;i<newStr.length;i++) {
	        			int id=Integer.parseInt(newStr[i]);
	    				Role r=rmapper.queryOne(id);
	    				jso.put(newStr[i], r.getRoleName());
	    				Role=Role+r.getRoleName()+",";
	        		}
	        		Role=Role.substring(0, Role.length()-1);
	        	}
	        	dto.setRolelist(jso);
	            list.add(dto);
	        }
			return list;	        
	}
	
	/**
	 *可根据是否有条件查询所有通知消息的条数
	 *创建日期 2019-09-23
	 *liangbenjian 
	 */
	public Integer getTotal(ProcessConfigDTO container) {
		 String username=(String) session.getAttribute("user");
		 ProcessConfig ProcessConfigSearch = mapperTrans.map(container, ProcessConfig.class);
		 return mapper.queryCount(ProcessConfigSearch);
    }
}
