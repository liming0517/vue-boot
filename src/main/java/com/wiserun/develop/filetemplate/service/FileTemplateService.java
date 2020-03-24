package com.wiserun.develop.filetemplate.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wiserun.develop.file.model.S_File;
import com.wiserun.develop.file.model.S_FileDTO;
import com.wiserun.develop.filetemplate.mapper.FileTemplateMapper;
import com.wiserun.develop.filetemplate.model.FileTemplate;
import com.wiserun.develop.filetemplate.model.FileTemplateDTO;
import com.wiserun.develop.filetemplate.model.FileTemplateListDTO;
import com.wiserun.develop.message.mapper.MessageMapper;

@Service
public class FileTemplateService {

	@Autowired
    private Mapper mapperTrans;
	@Autowired
	private FileTemplateMapper mapper;
	@Autowired
	private HttpSession session;
	
	
	/**
	 *根据模块编号查询对应的一条详细数据
	 *创建日期 2019-8-9
	 *liangbenjian 
	 */
	public FileTemplate getOne(FileTemplateDTO container) {
		FileTemplate filetemplate;
		filetemplate=mapper.queryOne1(container.getSFT_ModelCode());
		return filetemplate;
	}
	
	
	/**
	 *可根据是否有条件查询所有资源的数据信息
	 *创建日期 2019-8-12
	 *liangbenjian 
	 */
	public List<FileTemplateListDTO> getPageList(FileTemplateDTO container){
		FileTemplate filetemplateSearch= mapperTrans.map(container,FileTemplate.class);	//封装数据到对象里
		Map<String, Object> map = new HashMap<>();
		String username=(String) session.getAttribute("user");
			map.put("SFT_TemplateType", filetemplateSearch.getSFT_TemplateType());	
			map.put("SFT_ModelCode", filetemplateSearch.getSFT_ModelCode());
			map.put("SFT_State", filetemplateSearch.getSFT_State());
	        map.put("page", container.getCurrentPage());
	        map.put("size", container.getLimit());
/*	       System.out.println(map.get("model"));
	       System.out.println(map.get("LoginUser"));*/
	        List<FileTemplate> FileTemplateList= mapper.queryPageList(map);            //将条件全部给方法得到查询出的数据
	        List<FileTemplateListDTO> list = new ArrayList<>();                     //放入list
	        for (FileTemplate fileTemplate : FileTemplateList) {
	        	FileTemplateListDTO dto = mapperTrans.map(fileTemplate, FileTemplateListDTO.class);
	            list.add(dto);
	        }
			return list;	        
	}
	
	/**
	 *可根据是否有条件查询所有资源的数据信息不分页用于下拉框
	 *创建日期 2019-8-26
	 *liangbenjian 
	 */
	public List<FileTemplateListDTO> getPageList1(FileTemplateDTO container){
		FileTemplate filetemplateSearch= mapperTrans.map(container,FileTemplate.class);	//封装数据到对象里
		Map<String, Object> map = new HashMap<>();
		String username=(String) session.getAttribute("user");
		   map.put("SFT_State", filetemplateSearch.getSFT_State());
/*	       System.out.println(map.get("model"));
	       System.out.println(map.get("LoginUser"));*/
	        List<FileTemplate> FileTemplateList= mapper.queryList1(map);            //将条件全部给方法得到查询出的数据
	        List<FileTemplateListDTO> list = new ArrayList<>();                     //放入list
	        for (FileTemplate fileTemplate : FileTemplateList) {
	        	FileTemplateListDTO dto = mapperTrans.map(fileTemplate, FileTemplateListDTO.class);
	            list.add(dto);
	        }
			return list;	        
	}
	/**
	 *可根据是否有条件查询所有工单的条数
	 *创建日期 2019-8-12
	 *liangbenjian 
	 */
	public Integer getTotal(FileTemplateDTO container) {
		FileTemplate FileTemplateSearch = mapperTrans.map(container, FileTemplate.class);
        return mapper.queryCount(FileTemplateSearch);
    }
	
	/**
	 *根据编号查询对应的一条详细数据然后更新或者新增
	 *创建日期 2019-8-12
	 *liangbenjian 
	 */
	public Integer save(FileTemplateDTO container) {
		FileTemplate fileTemplate=new FileTemplate();
		fileTemplate = mapper.queryOne2(container.getSFT_Code());
		//通过前台传过来的code查找是否存在，不存在说明为新建
            if (fileTemplate == null) {         	
				UUID uuid = UUID.randomUUID();
		        String strID = uuid.toString();	 
				Date now = new Date(); 
				String username=(String) session.getAttribute("user");//拿到session里存储的登录人名字
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//创建日期的格式
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");//编号
				String hehe = dateFormat.format(now);
				session.setAttribute("SFT_Code",strID);
            	fileTemplate = new FileTemplate();
            	fileTemplate.setSFT_Code(strID);
            	fileTemplate.setSFT_CreateTime(hehe);
            	fileTemplate.setSFT_CreateUser(username);
            	fileTemplate.setSFT_State(container.getSFT_State());
            	fileTemplate.setSFT_TemplateType(container.getSFT_TemplateType());
            	fileTemplate.setSFT_ModelCode(container.getSFT_ModelCode());
                return mapper.add(fileTemplate);
               
            }else {  
            	session.setAttribute("SFT_Code",container.getSFT_Code());
            	fileTemplate.setSFT_State(container.getSFT_State());
            	fileTemplate.setSFT_TemplateType(container.getSFT_TemplateType());
            	fileTemplate.setSFT_ModelCode(container.getSFT_ModelCode());
                return mapper.update(fileTemplate);
            }        
    }
	
}
