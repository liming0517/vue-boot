package com.wiserun.develop.filetemplate.mapper;

import java.util.List;
import java.util.Map;

import com.temp.common.mapper.BaseMapper;
import com.wiserun.develop.filetemplate.model.FileTemplate;


public interface FileTemplateMapper extends BaseMapper<FileTemplate>{
	
	FileTemplate queryOne1(String id);

	FileTemplate queryOne2(String id);
	
	List<FileTemplate> queryList1(Map map);
	
	      
}
