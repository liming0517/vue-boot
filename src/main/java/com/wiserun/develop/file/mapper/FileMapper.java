package com.wiserun.develop.file.mapper;

import java.util.List;

import com.temp.common.mapper.BaseMapper;
import com.wiserun.develop.file.model.S_File;


public interface FileMapper extends BaseMapper<S_File>{
	S_File queryOne1(String id);
	
	S_File queryOne2(String id);

	List<S_File> queryListBySFT_Code(String id);
	
	int delete1(String id);
}
