package com.temp.custom.mapper;

import java.util.List;

import com.temp.common.mapper.BaseMapper;
import com.temp.custom.model.Custom;

public interface CustomMapper extends BaseMapper<Custom> {
	
	List<Custom> getList();
}
