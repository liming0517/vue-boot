package com.wiserun.develop.operate.service;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wiserun.develop.messageconfig.model.MessageConfig;
import com.wiserun.develop.messageconfig.model.MessageConfigDTO;
import com.wiserun.develop.operate.mapper.OperateMapper;
import com.wiserun.develop.operate.model.Operate;
import com.wiserun.develop.operate.model.OperateDTO;

@Service
public class OperateService {
	 @Autowired
     private Mapper mapperTrans;
	 @Autowired
	 private OperateMapper omapper;
	 @Autowired
	 private HttpSession session;
	 /**
		 *可根据是否有条件查询所有操作表信息的数据
		 *创建日期 2019-09-26
		 *liangbenjian 
		 */
		public List<OperateDTO> getPageList(OperateDTO container){
				Operate operateSearch= mapperTrans.map(container,Operate.class);	//封装数据到对象里
				Map<String, Object> map = new HashMap<>();
			    String username=(String) session.getAttribute("user");
				map.put("operate_Model", container.getOperate_Model());         //map中的key值与映射文件中的select语句#{}占位符中的值需要一一对应
				map.put("operate_CreateUser",container.getOperate_CreateUser());	
				map.put("operate_State", container.getOperate_State());
				map.put("operate_operate", container.getOperate_operate());
				map.put("operate_CreateTime", container.getOperate_CreateTime());
				map.put("operate_CreateTimeStart", container.getOperate_CreateTimeStart());
				map.put("operate_CreateTimeEnd", container.getOperate_CreateTimeEnd());
		        map.put("page", container.getCurrentPage());
		        map.put("size", container.getLimit());
		        List<Operate> OperateList= omapper.queryPageList(map);            //将条件全部给方法得到查询出的数据
		        List<OperateDTO> list = new ArrayList<>();                     //放入list
		        for (Operate operate : OperateList) {
		        	OperateDTO dto = mapperTrans.map(operate, OperateDTO.class);		        	
		            list.add(dto);
		        }
				return list;	        
		}
		

		/**
		 *可根据是否有条件查询所有操作表信息的条数
		 *创建日期 2019-09-23
		 *liangbenjian 
		 */
		public Integer getTotal(OperateDTO container) {
			 String username=(String) session.getAttribute("user");
			 Operate OperateSearch = mapperTrans.map(container, Operate.class);
			 return omapper.queryCount(OperateSearch);
	    }
		
		 /**
		 *根据model查询列表
		 *创建日期 2019-09-26
		 *liangbenjian 
		 */
		public List<OperateDTO> getPageListBymodel(OperateDTO container){
			 List<Operate> OperateList= omapper.queryListByModel(container.getOperate_Model());
		     List<OperateDTO> list = new ArrayList<>();                     
		        for (Operate operate : OperateList) {
		        	OperateDTO dto = mapperTrans.map(operate, OperateDTO.class);		        	
		            list.add(dto);
		        }
				return list;	
			
		}
	 
		/**
		 *新增操作表信息
		 *创建日期 2019-09-26
		 *liangbenjian 
		 */
		public Integer add(OperateDTO container) {
			String username=(String) session.getAttribute("user");//拿到session里存储的登录人名字
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//创建日期的格式
			Date now = new Date(); 
			String hehe = dateFormat.format(now);
			Operate o=new Operate();
			UUID uuid = UUID.randomUUID();
	        String strID = uuid.toString();	
			o.setOperate_id(strID);
			o.setOperate_CreateUser(username);
			o.setOperate_CreateTime(hehe);
			o.setOperate_Model(container.getOperate_Model());
			o.setOperate_operate(container.getOperate_operate());
			o.setOperate_State(container.getOperate_State());
			return omapper.add(o);
		}
		
		
		/**
		 *修改通知消息的配置信息
		 *创建日期 2019-09-26
		 *liangbenjian 
		 */
		@Transactional(rollbackFor = Exception.class)   //当我们使用@Transaction 时默认为RuntimeException（也就是运行时异常）异常才会回滚。															
		public Integer update(OperateDTO container) {   //当我们抛出java.io.FileNotFoundException检查异常时导致了数据的不回滚	
			int i = 0;									//@Transactional(rollbackFor = Exception.class) 不管检查异常还是非检查异常都会回滚
			try {  										//@Transactional(notRollbackFor=RunTimeException.class)RuntimeException（也就是运行时异常）不回滚。
				Operate operate = new Operate();
				operate.setOperate_id(container.getOperate_id());
				operate.setOperate_State(container.getOperate_State());
				operate.setOperate_operate(container.getOperate_operate());
				operate.setOperate_Model(container.getOperate_Model());
				i=omapper.update(operate);
				/*int b=1/0;
				operate.setOperate_operate("1234");
				omapper.update(operate);*/ 
				
		     } catch (Exception e) {  
		           e.printStackTrace();   
		           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//就是这一句了，加上之后，捕获异常依旧会回滚
		     } 

			return i;
		}
		
		/**
		 *删除通知消息的配置信息
		 *创建日期 2019-09-23
		 *liangbenjian 
		 */
		public Integer delete(OperateDTO container) {
			return omapper.delete1(container.getOperate_id());
			
		}
}
