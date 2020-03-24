package com.wiserun.develop.operate.mapper;

import java.util.List;

import com.temp.common.mapper.BaseMapper;
import com.wiserun.develop.operate.model.Operate;


public interface OperateMapper extends BaseMapper<Operate>{
	int delete1 (String id);
	
	List<Operate> queryListByModel (String model);
	
	Operate queryOneByid (String id);
}
