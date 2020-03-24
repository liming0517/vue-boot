package com.wiserun.develop.message.mapper;

import com.temp.common.mapper.BaseMapper;
import com.wiserun.develop.message.model.Message;
import java.util.List;

public interface MessageMapper extends BaseMapper<Message>{
	
	Message queryOne1(String id);
	
	List<Message> queryWXCode();
	
	List<Message> queryDXCode();
	
	List<Message> queryYJCode();
	
	int updateWX(String id);
	
	int updateDX(String id);
	
	int updateYJ(String id);

}
