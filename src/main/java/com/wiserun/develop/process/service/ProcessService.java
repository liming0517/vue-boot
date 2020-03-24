package com.wiserun.develop.process.service;

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
import com.wiserun.develop.process.model.Process;
import com.wiserun.develop.processconfig.model.ProcessConfig;

import com.wiserun.develop.process.mapper.ProcessMapper;



@Service
public class ProcessService {

	
	@Autowired
    private Mapper mapperTrans;
	@Autowired
	private ProcessMapper mapper;
	@Autowired
	private HttpSession session;
	/*@Autowired
	private ProcessRecordService prservice;*/
	/**
	 *可根据条件查到符合的一条信息然后添加到流程记录表里-----流程记录公共方法
	 *创建日期 2019-8-9
	 *liangbenjian 
	 */
/*	public Integer getPageAndAdd(Process container){
			String username=(String) session.getAttribute("user");
			ProcessConfig ProcessConfig=mapper.queryAndAdd(container);			
			if(ProcessConfig!=null) {//判断是否找到了数据
				String role=ProcessConfig.getSPC_Role();
				if(role.equalsIgnoreCase(username)) {//判断找到的数据的配置角色是否为登录人
					UUID uuid = UUID.randomUUID();
			        String strID = uuid.toString();	  
					Date now = new Date(); 
					//创建日期的格式
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					//拿到当前时间
					String hehe = dateFormat.format(now);
					ProcessRecordDTO pr=new ProcessRecordDTO();
					pr.setSPR_Code(strID);
					pr.setSPR_CreateTime(hehe);
					pr.setSPR_CreateUser(username);
					pr.setSPR_State("1");
					pr.setSPR_Action(container.getAction());
					pr.setSPR_Model(container.getModel());
					pr.setSPR_ModelCode(container.getModelCode());
					pr.setSPR_Result(container.getResult());
					pr.setSPR_Description(container.getDescription());
					pr.setSPR_Batch(container.getBatch());
					pr.setSPR_ConfigNo(ProcessConfig.getSPC_Code());
					return prservice.add(pr);
				}else {
					return -1;
				}
				
			}else {
				return -1;
			}
				        
	}*/
}
