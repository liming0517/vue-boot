package com.wiserun.develop.message.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temp.permission.entity.User;
import com.temp.permission.mapper.UserMapper;

import com.wiserun.develop.message.mapper.MessageMapper;
import com.wiserun.develop.message.model.Message;
import com.wiserun.develop.message.model.MessageDTO;
import com.wiserun.develop.message.model.MessageListDTO;
import com.wiserun.develop.messageconfig.mapper.MessageConfigMapper;
import com.wiserun.develop.messageconfig.model.MessageConfig;


@Service
public class MessageService {
	
	@Autowired
    private Mapper mapperTrans;
	@Autowired
	private MessageMapper mapper;
	@Autowired
	private MessageConfigMapper MCmapper;
	@Autowired
	private HttpSession session;
/*	@Autowired
	private ApplyService aservice;*/
	/*@Autowired
	private OrderMapper omapper;*/
	@Autowired
	private UserMapper umapper;
	/**
	 *可根据是否有条件查询所有通知消息的数据信息
	 *创建日期 2019-8-7
	 *liangbenjian 
	 */
	public List<MessageListDTO> getPageList(MessageDTO container){
		Message messageSearch= mapperTrans.map(container,Message.class);	//封装数据到对象里
		Map<String, Object> map = new HashMap<>();
		    String username=(String) session.getAttribute("user");
			map.put("SM_Content", messageSearch.getSM_Content());         //map中的key值与映射文件中的select语句#{}占位符中的值需要一一对应
			map.put("SM_State",messageSearch.getSM_State());	
			map.put("SM_CreateTimeStart", messageSearch.getSM_CreateTimeStart());
			map.put("SM_CreateTimeEnd", messageSearch.getSM_CreateTimeEnd());
			map.put("SM_YoN", messageSearch.getSM_YoN());
			map.put("LoginUser",username);
	        map.put("page", container.getCurrentPage());
	        map.put("size", container.getLimit());
/*	       System.out.println(map.get("model"));
	       System.out.println(map.get("LoginUser"));*/
	        List<Message> MessageList= mapper.queryPageList(map);            //将条件全部给方法得到查询出的数据
	        List<MessageListDTO> list = new ArrayList<>();                     //放入list
	        for (Message message : MessageList) {
	        	MessageListDTO dto = mapperTrans.map(message, MessageListDTO.class);
	        	/*//拿到已阅人账号
	        	String Readed=dto.getSM_Readed();
	        	if(Readed.contains(username)) {
	        		dto.setSM_YoN("Y");
	        	}else {
	        		dto.setSM_YoN("N");
	        	}*/
	            list.add(dto);
	        }
			return list;	        
	}
	/**
	 *可根据是否有条件查询所有通知消息的条数
	 *创建日期 2019-8-7
	 *liangbenjian 
	 */
	public Integer getTotal(MessageDTO container) {
		 String username=(String) session.getAttribute("user");
		Message MessageSearch = mapperTrans.map(container, Message.class);
		MessageSearch.setLoginUser(username);
        return mapper.queryCount(MessageSearch);
    }
	
	
	/**
	 *根据编号查询对应的一条详细数据并且更新已阅人
	 *创建日期 2019-8-7
	 *liangbenjian 
	 */
	public Message getOne(MessageDTO container) {
		//拿到session里登录人名
		MessageDTO md=new MessageDTO();
		md.setSM_Code(container.getSM_Code());
        this.read(md);
		return mapper.queryOne1(container.getSM_Code());
	}
	
	
	/**
	 *直接使用已读方法，打开地址及主键参数已通过列表获取，可直接打开业务模块,并标为已读
	 *创建日期 2019-8-7
	 *liangbenjian 
	 */
	public Integer read(MessageDTO container) {
		//通过查询方法查到那条数据
		Message message=mapper.queryOne1(container.getSM_Code());
		//拿到已阅人
		String SM_Readed=message.getSM_Readed();
		//拿到session里登录人名
		String username=(String) session.getAttribute("user");
		//判断已阅人是否为空，为空的话直接赋值即可
		if(SM_Readed==null) {
			message.setSM_Readed(username);			
		}else {
			//判断已阅人是否存在逗号，存在说明两个人或更多
			if(SM_Readed.indexOf(",")<0) {
				//已阅人为一个人,判断已阅人是否为登录人
				if(SM_Readed.equalsIgnoreCase(username)) {
					System.out.println("已阅人只有一个，且为登录人");
				}else {
					System.out.println("已阅人只有一个，不为登录人");
					SM_Readed=SM_Readed+","+username;
				}
			}else {
				//存在逗号说明有两个人或以上
				 String[] newStr=SM_Readed.split(",");
				 List<String> list = Arrays.asList(newStr);
				 if(list.contains(username)) {
					 //判断是否存在
					 System.out.println("已阅人有多个，其中存在登录人");				 
				 }else {
					 SM_Readed=SM_Readed+","+username;
					 System.out.println("已阅人有多个，其中不存在登录人");
				 }
			}
		}		
		return mapper.update(message);
	}
	
	
	/**
	 *将已读消息标为未读
	 *创建日期 2019-8-7
	 *liangbenjian 
	 */
	public Integer unread(MessageDTO container) {
		//通过查询方法查到那条数据
		Message message=mapper.queryOne1(container.getSM_Code());
		//拿到已阅人
		String SM_Readed=message.getSM_Readed();
		//拿到session里登录人名
		String username=(String) session.getAttribute("user");
		//判断是否有逗号，有则至少两个人
		if(SM_Readed.indexOf(",")<0) {
			//判断已阅人唯一那个人是否为登录人，是的话则删除
			if(SM_Readed.equalsIgnoreCase(username)) {
				message.setSM_Readed(null);	
				 System.out.println("已阅人为一个，切正好为登录人，已删");
			}
		}else {
			 //字符串以逗号分隔成数组
			 String[] newStr=SM_Readed.split(","); 
			 List<String> list = Arrays.asList(newStr);
			 if(list.contains(username)) {
				 //判断是否存在
				 System.out.println("已阅人有多个，其中存在登录人");	
				 list.remove(username);
			 }
			 String str=StringUtils.join(list.toArray(), ",");
			 message.setSM_Readed(str);			 
		}
		
		return mapper.update(message);
	}
	
	/**
	 *新增通知消息---公用方法
	 *创建日期 2019-8-13
	 *liangbenjian 
	 */
	public Integer add(MessageDTO container) {
		
		Message message =new Message();;
		Date now = new Date(); 
		String username=(String) session.getAttribute("user");//拿到session里存储的登录人名字
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//创建日期的格式
		String hehe = dateFormat.format(now);
		UUID uuid = UUID.randomUUID();
        String strID = uuid.toString();	
		message.setSM_Code(strID);
		message.setSM_CreateTime(hehe);
		message.setSM_CreateUser(username);
		message.setSM_State("1");
		//通过编号查到资源主表信息------
		/*ApplyDTO a=new ApplyDTO();
		Apply apply=new Apply();*/
		System.out.println("Apply--------------------------"+container.getApplyCode());
		System.out.println("Order--------------------------"+container.getOrderCode());
/*		if(container.getApplyCode()!=null) {
			System.out.println("设置了Apply--------------------------");
			a.setRA_Code(container.getApplyCode());
			apply=aservice.getOne(a);
			message.setSM_BussinessCode(container.getApplyCode());
		}	*/
		//----------------
		if(container.getOrderCode()!=null) {
			System.out.println("设置了Order--------------------------");
			message.setSM_BussinessCode(container.getOrderCode());
		}
		message.setSM_Model(container.getSM_Model());
		message.setSM_Operate(container.getSM_Operate());
		
		MessageConfig m=new MessageConfig();
		//暂时给版本号给固定值
		m.setSMC_Version("1");
		/*m.setSMC_Version(apply.getRA_ApplyType());*/
/*		if(apply==null) {
			//如果是问题工单那边生成通知消息那不会查到资源主表信息也就没有版本号直接给1即可
			m.setSMC_Version("1");
		}*/
		m.setSMC_Model(container.getSM_Model());
		m.setSMC_Operate(container.getSM_Operate());	
		MessageConfig newM=MCmapper.queryOne1(m);
		//替换掉通知内容里的编号--------------
		System.out.println("---------------------通知内容："+newM.getSMC_Content());
		String str=newM.getSMC_Content();
		str=str.replaceAll("\\d+", message.getSM_BussinessCode());
		message.setSM_Content(str);
		message.setSM_URL(newM.getSMC_URL());
		//---------------------------------
		//处理从配置表中拿到的被通知人---------
		 ArrayList Read = new ArrayList();
		 String[] newStr=newM.getSMC_Notified().split(","); 
		 List<String> list = Arrays.asList(newStr);
		 for(int i=0;i<list.size();i++) {
			 System.out.println("---------------------角色名："+list.get(i)+"--------------------");
			 String n=list.get(i);
			 List<User> l= umapper.queryListByRole(n);
			 for(int j=0;j<l.size();j++) {
				 System.out.println("账号名："+l.get(j).getUserPhone());
				 Read.add(l.get(j).getUserPhone());
			 }
		 }
		 HashSet h = new HashSet(Read);
		 Read.clear();
		 Read.addAll(h);
		 String SMC_Notified=StringUtils.join(Read.toArray(), ",");
		 message.setSM_Notified(SMC_Notified);
		//------------------------------------
	
		/*if(container.getSM_Operate().contains("驳回")) {
			//判断传过来的操作信息中是否有驳回，如果有说明被驳回需要在通知人中增加创建人
			String n=message.getSM_Notified();
			if(n==null||n=="") {
				//被通知人为空，直接将创建者赋值给被通知人
				 n=apply.getRA_CreateUser();
				 message.setSM_Notified(n);
			}else {
				if(n.indexOf(",")<0) {//判断是否有逗号，没有则一个人
					if(n.equalsIgnoreCase(apply.getRA_CreateUser())) {
						//判断唯一的那个人是不是创建人
						System.out.println("创建者是被通知人");
					}else {
						n=n+","+apply.getRA_CreateUser();
						 message.setSM_Notified(n);
					}								
				}else {
					String newStr1[]=n.split(",");
    				//转化成list
    				List<String> list1=Arrays.asList(newStr1);
    				if(list1.contains(apply.getRA_CreateUser())){
    					System.out.println("创建者在被通知人中存在");
    				}else{
    					System.out.println("创建者在被通知人中不存在");
    					message.setSM_Notified(n+","+apply.getRA_CreateUser());      					
    				}
				}
			}
		}
		if(container.getSM_Operate().equals("四审通过")) {
			//四审通过通知人里要有创建人
			String n=message.getSM_Notified();
			if(n==null||n=="") {
				//被通知人为空，直接将创建者赋值给被通知人
				 n=apply.getRA_CreateUser();
				 message.setSM_Notified(n);
			}else {
				if(n.indexOf(",")<0) {//判断是否有逗号，没有则一个人
					if(n.equalsIgnoreCase(apply.getRA_CreateUser())) {
						//判断唯一的那个人是不是创建人
						System.out.println("创建者在被通知人中存在");
					}else {
						n=n+","+apply.getRA_CreateUser();
						 message.setSM_Notified(n);
					}
									
				}else {
					String newStr2[]=n.split(",");
    				//转化成list
    				List<String> list2=Arrays.asList(newStr2);
    				if(list2.contains(apply.getRA_CreateUser())){
    					System.out.println("创建者在被通知人中存在");
    				}else{
    					System.out.println("创建者在被通知人中不存在");
    					message.setSM_Notified(n+","+apply.getRA_CreateUser());      					
    				}
				}
			}
    	}*/
/*		if(container.getSM_Operate().contains("答复")) {
			Order o= omapper.queryOne1(container.getOrderCode());
			//判断传过来的操作信息中为答复
			String n=message.getSM_Notified();
			if(n==null||n=="") {
				//被通知人为空，直接将创建者赋值给被通知人
				 n=o.getWO_CreateUser();
				 message.setSM_Notified(n);
			}else {
				if(n.indexOf(",")<0) {//判断是否有逗号，没有则一个人
					if(n.equalsIgnoreCase(o.getWO_CreateUser())) {
						//判断唯一的那个人是不是创建人
						System.out.println("创建者是被通知人");
					}else {
						n=n+","+o.getWO_CreateUser();
						 message.setSM_Notified(n);
					}									
				}else {
					String newStr3[]=n.split(",");
    				//转化成list
    				List<String> list3=Arrays.asList(newStr3);
    				System.out.println("-------------------------::::::::"+o.getWO_CreateUser());
    				if(list3.contains(o.getWO_CreateUser())){
    					System.out.println("创建者在被通知人中存在");
    				}else{
    					System.out.println("创建者在被通知人中不存在");
    					message.setSM_Notified(n+","+o.getWO_CreateUser());      					
    				}
				}
			}
		}*/
		System.out.println("----------------------------------------+++++++++++++++++++++++++++++++"+message.getSM_Notified());
		return mapper.add(message);
    }
}
