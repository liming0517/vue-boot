package com.temp.department.mapper;

import java.util.List;
import java.util.Map;

import com.temp.common.mapper.BaseMapper;
import com.temp.department.model.Department;
import com.temp.department.model.DepartmentDTO;


public interface DepartmentMapper extends BaseMapper<Department>{
	
	Department queryOne1(String id);
	
	int delete1(String id);
	
	Department queryOne2(String CName);
}
