package com.wiserun.develop.process.mapper;

import java.util.List;

import com.temp.common.mapper.BaseMapper;
import com.wiserun.develop.process.model.Process;
import com.wiserun.develop.processconfig.model.ProcessConfig;

public interface ProcessMapper extends BaseMapper<Process>{

	Process queryOne1(String id);
	
	ProcessConfig queryAndAdd(Process process);
	
	List<Process> queryList(Process process);
}
