package com.wiserun.develop.file.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wiserun.develop.file.mapper.FileMapper;
import com.wiserun.develop.file.model.S_File;
import com.wiserun.develop.file.model.S_FileDTO;
import com.wiserun.develop.file.model.S_FileListDTO;



@Service
public class FileService {
	@Autowired
    private Mapper mapperTrans;
	@Autowired
	private HttpSession session;
	@Autowired
	private FileMapper mapper;
	
	/**
	 *数据库添加信息操作
	 *创建日期 2019-8-5
	 *liangbenjian 
	 */
	public Integer add(S_File container) {
		//这里重新赋值是因为以防上传方法中拿不到session里的值
		String username=(String) session.getAttribute("user");
		container.setSF_CreateUser(username);
		return mapper.add(container);
		
	}
	

	/**
	 *根据编号查询对应的一条详细数据
	 *创建日期 2019-8-5
	 *liangbenjian 
	 */
	public S_File getOne(S_FileDTO container) {
		S_File s_file;
		s_file=mapper.queryOne1(container.getSF_Code());
		return s_file;
	}
	/**
	 *根据业务表编号查询对应的一条详细数据
	 *创建日期 2019-8-12
	 *liangbenjian 
	 */
	public S_File getOne2(S_FileDTO container) {
		S_File s_file;
		s_file=mapper.queryOne2(container.getSF_BussinessCode());
		return s_file;
	}
	
	
	/**
	 *可根据是否有条件查询所有附件的数据信息
	 *创建日期 2019-8-9
	 *liangbenjian 
	 */
	public List<S_FileListDTO> getPageList(S_FileDTO container){
		S_File S_FileSearch= mapperTrans.map(container,S_File.class);	//封装数据到对象里
		Map<String, Object> map = new HashMap<>();
		//由菜单资源申请进入时，仅租户可用，查看自己创建的且未完成所有流程的数据

			map.put("SF_Code", S_FileSearch.getSF_Code());         //map中的key值与映射文件中的select语句#{}占位符中的值需要一一对应
			map.put("SF_CreateUser",S_FileSearch.getSF_CreateUser());	
			map.put("SF_State", S_FileSearch.getSF_State());
			map.put("SF_BussinessCode", S_FileSearch.getSF_BussinessCode());
	        map.put("page", container.getCurrentPage());
	        map.put("size", container.getLimit());
/*	       System.out.println(map.get("model"));
	       System.out.println(map.get("LoginUser"));*/
	        List<S_File> S_FileList= mapper.queryPageList(map);            //将条件全部给方法得到查询出的数据
	        List<S_FileListDTO> list = new ArrayList<>();                     //放入list
	        for (S_File s_file : S_FileList) {
	        	S_FileListDTO dto = mapperTrans.map(s_file, S_FileListDTO.class);
	            list.add(dto);
	        }
			return list;	        
	}
	
	/**
	 *可根据是否有条件查询所有附件的数据信息
	 *创建日期 2019-8-16
	 *liangbenjian 
	 */
	public List<S_FileListDTO> getList(S_FileDTO container){
		S_File S_FileSearch= mapperTrans.map(container,S_File.class);	//封装数据到对象里
/*	       System.out.println(map.get("model"));
	       System.out.println(map.get("LoginUser"));*/
	        List<S_File> S_FileList= mapper.queryList(S_FileSearch);            //将条件全部给方法得到查询出的数据
	        List<S_FileListDTO> list = new ArrayList<>();                     //放入list
	        for (S_File s_file : S_FileList) {
	        	S_FileListDTO dto = mapperTrans.map(s_file, S_FileListDTO.class);
	            list.add(dto);
	        }
			return list;	        
	}
	/**
	 *可根据是否有条件查询所有附件的条数
	 *创建日期 2019-8-1
	 *liangbenjian 
	 */
	public Integer getTotal(S_FileDTO container) {
		S_File s_fileSearch = mapperTrans.map(container, S_File.class);
        return mapper.queryCount(s_fileSearch);
    }
	
	/**
	 *根据编号删除文件
	 *创建日期 2019-8-16
	 *liangbenjian 
	 */
	public Integer delete(S_FileDTO container) {
        return mapper.delete1(container.getSF_Code());
    }
	
	
	/**
	 *可根据模板编号查询对应附件列表
	 *创建日期 2019-8-16
	 *liangbenjian 
	 */
	public List<S_FileListDTO> getListBySFT_Code(S_File container){
	        List<S_File> S_FileList= mapper.queryList(container);            //将条件全部给方法得到查询出的数据
	        List<S_FileListDTO> list = new ArrayList<>();                     //放入list
	        for (S_File s_file : S_FileList) {
	        	S_FileListDTO dto = mapperTrans.map(s_file, S_FileListDTO.class);
	            list.add(dto);
	        }
			return list;	        
	}
}
