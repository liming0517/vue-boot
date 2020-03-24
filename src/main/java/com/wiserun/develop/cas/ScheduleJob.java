package com.wiserun.develop.cas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import com.wiserun.develop.file.controller.FileController;
import com.wiserun.develop.message.mapper.MessageMapper;
import com.wiserun.develop.message.model.Message;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling  
public class ScheduleJob {
	private static  final Logger logger = LoggerFactory.getLogger(FileController.class);
	@Value("${tx.wx}")
	private  boolean WX;
	@Value("${tx.dx}")
	private  boolean DX;
	@Value("${tx.yj}")
	private  boolean YJ;
	
	@Autowired
	private MessageMapper mapper;
	
	//                  @Scheduled(fixedRate = 60000)
    public void UpdateMessage(){ 
		 try {
			 System.out.println("--------------------------更新提醒人--------------------------");
			 if(WX) {
				 System.out.println("微信提醒已启用");
				 List<Message> list=mapper.queryWXCode();
				 for(int i=0;i<list.size();i++) {
					 mapper.updateWX(list.get(i).getSM_Code());
					 goWX(list.get(i).getSM_Code());
				 }
			 }else {
				 System.out.println("微信提醒未启用"); 
			 }

			 if(DX) {
				 System.out.println("短信提醒已启用");
				 List<Message> list=mapper.queryDXCode();
				 for(int i=0;i<list.size();i++) {
					 mapper.updateDX(list.get(i).getSM_Code());
					 //更新完短信提醒之后可以在这通过code拿到需要发送的账户的号码发送短信
					 goDX(list.get(i).getSM_Code());
				 }
			 }else {
				 System.out.println("短信提醒未启用"); 
			 }
			 
			 if(YJ) {
				 System.out.println("邮件提醒已启用");
				 List<Message> list=mapper.queryYJCode();
				 for(int i=0;i<list.size();i++) {
					 mapper.updateYJ(list.get(i).getSM_Code());
					 goYJ(list.get(i).getSM_Code());
				 }
			 }else {
				 System.out.println("邮件提醒未启用"); 
			 }
			 System.out.println("--------------------------更新提醒人完毕--------------------------");
	    	}catch (Exception e) {
				// TODO: handle exception
	    		logger.info("getVMInfo==>"+e.getMessage());
			}
		
    }
	
	public void goWX(String id) {
		
	}
	public void goDX(String id) {
		
	}
	public void goYJ(String id) {
		
	}
	
}
