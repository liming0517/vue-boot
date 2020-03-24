package com.wiserun.develop.processconfig.mapper;

import com.temp.common.mapper.BaseMapper;
import com.wiserun.develop.processconfig.model.ProcessConfig;


public interface ProcessConfigMapper extends BaseMapper<ProcessConfig>{
	
	ProcessConfig queryOne1(String id);
	
	ProcessConfig queryOne2(ProcessConfig record);
	
	int delete1(String id);

}
