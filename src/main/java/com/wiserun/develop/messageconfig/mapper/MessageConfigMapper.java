package com.wiserun.develop.messageconfig.mapper;

import java.util.List;

import com.temp.common.mapper.BaseMapper;
import com.wiserun.develop.messageconfig.model.MessageConfig;

public interface MessageConfigMapper  extends BaseMapper<MessageConfig>{
	
	MessageConfig queryOne1(MessageConfig record);
	
	int delete1(String id);

	List<MessageConfig> queryOperateList(); 
}
