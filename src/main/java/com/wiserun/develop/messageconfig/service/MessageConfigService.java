package com.wiserun.develop.messageconfig.service;

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
import com.wiserun.develop.messageconfig.mapper.MessageConfigMapper;
import com.wiserun.develop.messageconfig.model.MessageConfig;
import com.wiserun.develop.messageconfig.model.MessageConfigDTO;
import com.wiserun.develop.operate.mapper.OperateMapper;
import com.wiserun.develop.operate.model.Operate;

@Service
public class MessageConfigService {
	@Autowired
    private Mapper mapperTrans;
	@Autowired
	private MessageConfigMapper MCmapper;
	@Autowired
	private HttpSession session;
	@Autowired
	private RoleMapper rmapper;
	@Autowired
	private OperateMapper omapper;
	/**
	 *可根据是否有条件查询所有通知消息配置表的数据
	 *创建日期 2019-09-23
	 *liangbenjian 
	 */
	public List<MessageConfigDTO> getPageList(MessageConfigDTO container){
		MessageConfig messageconfigSearch= mapperTrans.map(container,MessageConfig.class);	//封装数据到对象里
		Map<String, Object> map = new HashMap<>();
		    String username=(String) session.getAttribute("user");
			map.put("SMC_Model", container.getSMC_Model());         //map中的key值与映射文件中的select语句#{}占位符中的值需要一一对应
			map.put("SMC_Version",container.getSMC_Version());	
			map.put("SMC_CreateUser", container.getSMC_CreateUser());
			map.put("SMC_CreateTimeStart", container.getSMC_CreateTimeStart());
			map.put("SMC_CreateTimeEnd", container.getSMC_CreateTimeEnd());
	        map.put("page", container.getCurrentPage());
	        map.put("size", container.getLimit());
/*	       System.out.println(map.get("model"));
	       System.out.println(map.get("LoginUser"));*/
	        List<MessageConfig> MessageconfigList= MCmapper.queryPageList(map);            //将条件全部给方法得到查询出的数据
	        List<MessageConfigDTO> list = new ArrayList<>();                     //放入list
	        for (MessageConfig messageConfig : MessageconfigList) {
	        	//显示给页面的被通知人需要将数据库的数据按逗号隔开后重新在数据库角色表查询拼接给前台
	        	MessageConfigDTO dto = mapperTrans.map(messageConfig, MessageConfigDTO.class);
	        	JSONObject jso=new JSONObject();	        	
	        	String[] newStr=dto.getSMC_Notified().split(",");
	        	String Notified="";
	        	if(newStr!=null||newStr.length!=0) {
	        		for(int i=0;i<newStr.length;i++) {
	        			int id=Integer.parseInt(newStr[i]);
	    				Role r=rmapper.queryOne(id);
	    				jso.put(newStr[i], r.getRoleName());
	    				Notified=Notified+r.getRoleName()+",";
	        		}
	        		Notified=Notified.substring(0, Notified.length()-1);
	        	}
	        	dto.setNotifiedlist(jso);
	        	//模块操作前台需要返回的为JSONObject
	        	JSONObject jso1=new JSONObject();	        	
	        	String oid=dto.getSMC_Operate();
	        	if(oid!=null) {
	        		Operate operate=omapper.queryOneByid(oid);
	        		jso1.put(oid, operate.getOperate_operate());
	        	}
	        	dto.setOperatelist(jso1);
	            list.add(dto);
	        }
			return list;	        
	}
	
	/**
	 *可根据是否有条件查询所有通知消息的条数
	 *创建日期 2019-09-23
	 *liangbenjian 
	 */
	public Integer getTotal(MessageConfigDTO container) {
		 String username=(String) session.getAttribute("user");
		 MessageConfig MessageconfigSearch = mapperTrans.map(container, MessageConfig.class);
		 return MCmapper.queryCount(MessageconfigSearch);
    }
	
	/**
	 *查询所有的不重复的关联模块操作
	 *创建日期 2019-09-23
	 *liangbenjian 
	 */
	public List<MessageConfig> queryOperateList(){
		return MCmapper.queryOperateList();		
	}
	
	
	
	/**
	 *新增通知消息配置信息
	 *创建日期 2019-09-23
	 *liangbenjian 
	 */
	public Integer add(MessageConfigDTO container) {
		String username=(String) session.getAttribute("user");//拿到session里存储的登录人名字
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//创建日期的格式
		Date now = new Date(); 
		String hehe = dateFormat.format(now);
		MessageConfig MessageconfigSearch = mapperTrans.map(container, MessageConfig.class);
		UUID uuid = UUID.randomUUID();
		//System.out.println("创建人："+username);
        String strID = uuid.toString();	
		MessageconfigSearch.setSMC_Code(strID);
		MessageconfigSearch.setSMC_CreateTime(hehe);
		MessageconfigSearch.setSMC_CreateUser(username);
		MessageconfigSearch.setSMC_State(container.getSMC_State());
		MessageconfigSearch.setSMC_Content(container.getSMC_Content());
		MessageconfigSearch.setSMC_Model(container.getSMC_Model());
		MessageconfigSearch.setSMC_Operate(container.getSMC_Operate());
		MessageconfigSearch.setSMC_URL(container.getSMC_URL());
		String arr[]=container.getSMC_Notifiedlist();
		//拿到列表用逗号进行拼接成字符串
		String str = "";
        for(int i=0;i<arr.length;i++) {
        	str=str+arr[i]+",";
        }       
        str=str.substring(0, str.length()-1);
		MessageconfigSearch.setSMC_Notified(str);
		MessageconfigSearch.setSMC_Version(container.getSMC_Version());
		 return MCmapper.add(MessageconfigSearch);
	}
	
	
	/**
	 *修改通知消息的配置信息
	 *创建日期 2019-09-23
	 *liangbenjian 
	 */
	public Integer update(MessageConfigDTO container) {
		//System.out.println("-----------------------被通知人："+container.getSMC_Notifiedlist().toString());
		//拿到列表用逗号进行拼接成字符串
		String arr[]=container.getSMC_Notifiedlist();
		String str = "";
        for(int i=0;i<arr.length;i++) {
        	str=str+arr[i]+",";
        }       
        str=str.substring(0, str.length()-1);
		MessageConfig Messageconfig = new MessageConfig();
		Messageconfig.setSMC_Code(container.getSMC_Code());
		Messageconfig.setSMC_State(container.getSMC_State());
		Messageconfig.setSMC_Content(container.getSMC_Content());
		Messageconfig.setSMC_Model(container.getSMC_Model());
		Messageconfig.setSMC_Operate(container.getSMC_Operate());
		Messageconfig.setSMC_URL(container.getSMC_URL());
		Messageconfig.setSMC_Notified(str);
		Messageconfig.setSMC_Version(container.getSMC_Version());
		return MCmapper.update(Messageconfig);
	}
	
	/**
	 *删除通知消息的配置信息
	 *创建日期 2019-09-23
	 *liangbenjian 
	 */
	public Integer delete(MessageConfigDTO container) {
		return MCmapper.delete1(container.getSMC_Code());
		
	}

}
