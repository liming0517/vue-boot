package com.temp.custom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temp.custom.mapper.CustomMapper;
import com.temp.custom.model.Custom;

@Service
public class CustomService {

	@Autowired
	private CustomMapper mapper;
	
	public List<Custom> getCustomList(){
		//根据实际需求自行处理业务逻辑
		List<Custom> list = mapper.getList();
		return list;
	}
}
