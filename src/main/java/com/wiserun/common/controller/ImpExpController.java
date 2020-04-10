package com.wiserun.common.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.digester3.Digester;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import com.wiserun.common.DurcException;
import com.wiserun.common.EntityProcessor;
import com.wiserun.common.MessageResult;
import com.wiserun.common.SearchEntity;
import com.wiserun.common.WebContext;
import com.wiserun.common.dao.BaseDao;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.service.SearchService;
import com.wiserun.common.util.ClassUtil;
import com.wiserun.common.util.DateUtil;
import org.jxls.reader.DateConverter;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.xml.sax.SAXException;

import com.alibaba.druid.wall.violation.ErrorCode;


/**
 * 负责导出的Controller,如果想要实现导出功能,可以继承该类
 * 
 * rebuild 20181210
 * 
 * 增加负责导入EXCEL的代码，仅限于导入列表型EXCEL 
 * @param <Entity>
 * @param <Service>
 */
public abstract class ImpExpController<Entity, Service extends SearchService<Entity, ? extends BaseDao<Entity>>>
		extends SearchController<Entity, Service> {
	
	private static Logger logger = Logger.getLogger(ImpExpController.class);
	/** 标准导入用xml配置文件相对目录 */
    public  String UploadTemp_Path= "/importTemplate";
    /** 标准导入用文件存放相对目录 */
    public  String UploadFile_Path= "/importTemplate";
    /** 标准导出用文件模板存放相对目录 */
    public  String ExportTemp_Path= "/exportTemplate";

    
	
	private ExportService exportService = new ExportService();
	
	
	/**
	 * 获取模板文件的绝对路径
	 * @return
	 */
	public abstract String getTemplateFilePath();
	
	protected String getModelName() {
		return "obj";
	}
	
	
	///////导入用参数
	private String importExcelFileName ; //待导入的EXCEL文件名
	private String importXMLFileName ; //导入模板配置文件名
	
	///////////
	/**
	 * 通过查询参数导出
	 * @param searchEntity
	 * @param response
	 */
	public void exportBySearchEntity(
			SearchEntity searchEntity
			,HttpServletResponse response) {
		this.exportByQuery(this.buildExpressionQuery(searchEntity), response);
	}
	
	/**
	 * 通过查询参数导出且支持表达式
	 * 可用于对结果集的二次处理，如状态1,0转为 有效，无效
	 * @param searchEntity
	 * @param response
	 * @param processor 表达式
	 */
	public void exportBySearchEntity(
			SearchEntity searchEntity
			,HttpServletResponse response
			,EntityProcessor<Entity> processor) {
		this.exportByQuery(this.buildExpressionQuery(searchEntity), response, processor);
	}
	
	/**
	 * 通过条件导出
	 * @param query
	 * @param response
	 */
	public void exportByQuery(ExpressionQuery query,HttpServletResponse response){
		this.exportByQuery(query, response,null);
		/*
		try {
			List<?> list = this.getService().find(query);

			doExport(list, response);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new DurcException("导出Excel失败,请查看日志");
		}
		*/	
	}
	
	/**
	 * 通过条件导出
	 * 可用于对结果集的二次处理，如状态1,0转为 有效，无效
	 * @param query
	 * @param response
	 */
	public void exportByQuery(ExpressionQuery query,HttpServletResponse response,EntityProcessor<Entity> processor){
		try {
			List<Entity> list = this.getService().find(query);
        //	List<?> list = this.getService().find(query); //旧方法

			List<Object> jsonObjList;
			if(processor != null) { //List<Object>
				
				jsonObjList = processEntityToJSONObject(list, processor);
				//this.logger.info("导出结果"+jsonObjList);
					
				doExport(jsonObjList, response); //产生excel文件(有二次处理)
			}
			else{
				doExport(list, response); //产生excel文件
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new DurcException("导出Excel失败,请查看日志");
		}	
	}
	
	// 将list中的entity对象处理成JSONObject对象
	private List<Object> processEntityToJSONObject(List<Entity> list,
			EntityProcessor<Entity> processor) {
		List<Object> jsonObjList = new ArrayList<Object>(list.size());
		
		for (Entity entity : list) {
			Map<String,Object> jsonObject = ClassUtil.convertObj2Map(entity);
			processor.process(entity, jsonObject);
			jsonObjList.add(jsonObject);
		}
		
		return jsonObjList;
	}
	/**
	 * 导出
	 * @param list
	 * @param response
	 */
	public void doExport(List<?> list,HttpServletResponse response){
		check();
		
		ExportEntity exportEntity = new ExportEntity();
		
		exportEntity.setTemplateFilePath(this.getTemplateFilePath());
		exportEntity.setList(list);
		exportService.export(exportEntity,getModelMap(),response);
	}
	
	/**
	 * 类似于ModelMap的用法,如:<br><br>
	 * <code>
	 * 1.<br>
	 * Map&lt;String, Object&gt; model = super.getModelMapResult();<br>
	 * model.put("title", "学生信息");<br>
	 * excel中:<br>
	 * ${title}<br><br>
	 * 
	 * 2.<br>
	 * Student student = new Student();<br>
	 * Map&lt;String, Object&gt; model = super.getModelMapResult();<br>
	 * model.put("student", student);<br>
	 * excel中:<br>
	 * ${student.name};
	 * </code>
	 * @return
	 */
	protected Map<String, Object> getModelMap() {
		return new HashMap<String, Object>();
	}
	
	private static List<String> XLS_NAME_LIST = Arrays.asList(".xls",".XLS");
	
	private void check(){
		String tempFilePath = getTemplateFilePath();
		if(StringUtils.hasText(tempFilePath)){
			String suffix = tempFilePath.substring(tempFilePath.length() - 4);
			if(!XLS_NAME_LIST.contains(suffix)){
				logger.error("Excel模板文件必须以xls结尾");
				throw new DurcException("Excel模板文件必须以xls结尾");
			}
			if(!new File(tempFilePath).exists()){
				logger.error("Excel模板文件不存在");
				throw new DurcException("Excel模板文件不存在");
			}
		}
	}
	
	/**
	 * 返回导出文件名
	 * @return
	 */
	public String getExportFileName(){
		String timestamp = DateUtil.format(new Date(), "yyyyMMddHHmmss");
		String templateFilePath = getTemplateFilePath();
		
		if(StringUtils.hasText(templateFilePath)){
			int index = templateFilePath.lastIndexOf("/") + 1;
			String name  = templateFilePath.substring(index, templateFilePath.length());
			return new StringBuilder(name).insert(name.indexOf("."), timestamp).toString();
		}else{
			return "export" + timestamp;
		}
		
	}

	//--------------------私有类---------------------// 
	private class ExportService {

		private static final String CONTENT_TYPE = "application/vnd.ms-excel;charset=GBK";
		private static final String F_HEADER_ARGU1 = "Content-Disposition";
		private static final String F_HEADER_ARGU2 = "attachment;filename=";

		public void export(
				ExportEntity exportEntity, Map<String, Object> beans,
				HttpServletResponse response) {
			String fileName;
			// 设置响应头
			response.setContentType(CONTENT_TYPE);
			try {
				fileName = new String(getExportFileName().getBytes("GBK"), "ISO8859-1");
			} catch (UnsupportedEncodingException e1) {
				fileName = "export";
			}
			response.setHeader(F_HEADER_ARGU1, F_HEADER_ARGU2 + fileName);

			beans.put(getModelName(), exportEntity.getList());
			
			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(
						exportEntity.getTemplateFilePath()));
				ServletOutputStream out = response.getOutputStream();
				XLSTransformer transformer = new XLSTransformer();
				Workbook workbook = transformer.transformXLS(is, beans);
				workbook.write(out);
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage(),e);
				throw new DurcException("Excel模板文件不存在,请联系管理员");
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new DurcException("Excel导出错误");
			}finally{
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						logger.error(e.getMessage(),e);
					}
				}
			}

		}
	}
	// 辅助类
	private class ExportEntity {
		
		private String templateFilePath;
		
		private List<?> list = Collections.emptyList();

		public String getTemplateFilePath() {
			return templateFilePath;
		}

		public void setTemplateFilePath(String templateFilePath) {
			this.templateFilePath = templateFilePath;
		}
		
		public List<?> getList() {
			return list;
		}

		public void setList(List<?> list) {
			this.list = list;
		}

	}
	
	
	///////////////////////////////////////////////////////////////////
	
	//以下为导入EXCEL 代码
	/*
	 * 导出EXCEL
	 * 注意必须先set EXCEL文件路径和XML模板文件路径
	 */
	public List importExcel(){
		 //配置文件
        FileInputStream xmlFin;
        FileInputStream dataFin;
		try {
			xmlFin = new FileInputStream(new File(this.getImportXMLFileName() ));
			//要导入的Excel
	        dataFin = new FileInputStream(new File(this.getImportExcelFileName()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
        
        InputStream inputXML = new BufferedInputStream(xmlFin);
        InputStream inputXLS = new BufferedInputStream(dataFin);


        List<Entity> objS = new ArrayList<Entity>();
        Map<String,Object> beanparams = new HashMap<String,Object>();
        beanparams.put("list",objS); //这里的 list 对应xml配置文件中 items值，不要随便改 

        XLSReader mainReader;
       
		try {
			//根据XML配置进行分析
			mainReader = ReaderBuilder.buildFromXML(inputXML);
			XLSReadStatus readStatus = mainReader.read(inputXLS,beanparams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
          
        return objS ;

		
}
	


	/*获得导入excel文件路径
	 * 注意 
	 * 1-模板文件必须存放指定目录
	 */
	public String getImportExcelFileName() {
		return importExcelFileName;
	}
	
		/*设置导入excel文件路径
		 * 注意 
		 * 1-模板文件必须存放指定目录
		 */
	public void setImportExcelFileName(String importExcelFileName) {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		this.importExcelFileName = path +  importExcelFileName;
	}
	/*获得导入模板文件路径
	 * 注意 
	 * 1-模板文件必须存放指定目录
	 */
	public String getImportXMLFileName() {
		return importXMLFileName;
	}
	/*设置导入模板文件路径
	 * 注意 
	 * 1-模板文件必须存放指定目录
	 */
	public void setImportXMLFileName(String importXMLFileName) {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		this.importXMLFileName = path +  importXMLFileName;
	}

}
