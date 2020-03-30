package com.wiserun.RestFul.mapper;

import java.util.List;

import com.temp.common.mapper.BaseMapper;
import com.wiserun.RestFul.model.RestFulApiModel;

public interface RestFulApiMapper extends BaseMapper<RestFulApiModel> {
	
	List<RestFulApiModel> queryAuthByPhone(RestFulApiModel rfam);
}
