
package com.wiserun.devolop.system.importexportcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wiserun.common.EntityProcessor;
import com.wiserun.common.MessageResult;
import com.wiserun.common.WebContext;
import com.wiserun.common.controller.ImpExpController;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;
import com.wiserun.common.util.ClassUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.wiserun.devolop.system.entity.SysDepartment;
import com.wiserun.devolop.system.dao.SysDepartmentDao;
import com.wiserun.devolop.system.entity.SysDepartmentSch;
import com.wiserun.devolop.system.service.SysDepartmentService;


/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.department 导出及导入服务
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**


**/

/**
 ****************************************************
 * 开发注意事项-请根据需要修改 ：
 * 1-exportSysDepartment 方法：对导出数据的特殊处理（如代码转义）
 * 2-importSysDepartmentList 方法：对导入数据的特殊处理
 * 注意：
 * 1-getTemplateFilePath方法规定了导出模板名 ，必须以 xls 为后缀
 * 2-getModelMap方法规定了导出文件的标题
 * 3-importSysDepartmentList 方法： 规定了导入模板名，必须以 xls 为后缀
 */

// 导出功能
// 大数据量导出可能会慢
@Controller
public class SysDepartmentImportExportController extends ImpExpController<SysDepartment, SysDepartmentService> {

	// 模板文件路径
	@Override
	public String getTemplateFilePath() {
		HttpServletRequest request = WebContext.getInstance().getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		// X:xxx/main/webapp/webapp/student_export.xls
		// 全路径
		return path + this.ExportTemp_Path+ "/SysDepartment_export.xls";
		
		/*
		 * 注意 
		 * 1-模板文件必须存放指定目录
		 * 2-模板文件中可自定义字段名
		 * 3-模板文件中可自定义显示格式
		 */
	}
	

	// 无扩展导出（以数据库值为准，可能与JSP界面不一致）--可带请求参数
	// 有扩展导出（通过扩展处理，确保与JSP界面一致） 可带请求参数
	// ${ctx}/exportSysDepartment.do?&schGender=1
	@RequestMapping("exportSysDepartment.do")
	public void exportSysDepartment(SysDepartmentSch searchEntity,HttpServletResponse response){
		 // System.out.println("导出参数"+ClassUtil.convertObj2Map(searchEntity) );
		  
		  //设置默认查询条件及排序，必须确保与标准controller查询接口的处理模式完全一致
		    ExpressionQuery query=this.getService().queryBefore(searchEntity);
	      
		   query.setQueryAll(true);  //不分页--这是唯一和查询界面不同的地方 
	        
		  //this.exportByQuery(query, response); //无扩展不需要二次过滤-根据需要开启
	        
		  this.exportByQuery(query, response,new EntityProcessor<SysDepartment>() {
				// jsonObject是当前记录的json对象,可以对其增加键值对
				@Override
				public void process(SysDepartment entity, Map<String,Object> jsonObject) {
					// 在返回的json中会增加status 中文描述
					if(entity.YES.equalsIgnoreCase( entity.getState())  ) {
						jsonObject.put("state", "有效");
					}else{
						jsonObject.put("state", "无效");
					}
				}
				
			});
	}
	// 自定义参数导出--需要时可以开放
	// ${ctx}/exportSysDepartment.do
	/*
	@RequestMapping("exportSysDepartment.do")
	public void export3(HttpServletResponse response){
		ExpressionQuery query = new ExpressionQuery();
		
		query.setQueryAll(true); // 查询全部
		query.add(new ValueExpression("gender", 0));
		
		this.exportByQuery(query, response);
	}
	*/
	// 导出的文件名
	@Override
	public String getExportFileName() {
		return "sys.department_导出信息.xls";
	}
	
	
	//设置标题
	@Override
	protected Map<String, Object> getModelMap() {
		Map<String, Object> map = super.getModelMap();
		map.put("title", "sys.department");
		return map;
	}
	
    
    ////////////以下为导入代码////////////////////////////////////////
	/**
	 * 导入excel文件
	 * @return 提示信息
	 */
	@RequestMapping("importSysDepartmentList.do")
	public @ResponseBody MessageResult importSysDepartmentList(){
		
		 this.setImportXMLFileName(this.UploadTemp_Path+"/SysDepartment_import.xml"); //导入配置文件
		 this.setImportExcelFileName(this.UploadFile_Path+"/SysDepartment_import.xls"); //导入EXCEL文件（已上传服务器）
		 
		 @SuppressWarnings("unchecked")
		 List<SysDepartment> EntiyList=this.importExcel();
		 
		 if (EntiyList.size()==0){
			 return this.error("导入失败!没有获得可用数据!");
		 }else{
			    for (SysDepartment entity : EntiyList) {
		             //模板中没有参照信息，如项目类型ID，项目类型代码，需要根据导入的文字反查
		            //例如根据 XX类型名称 反查 XX类型ID，XX代码--需自行扩展
                     this.getService().setInfoByName(entity); 
          
                    //其他逻辑，均继承业务模块存盘代码
		            
		        }
                
                //批量存盘
				try {
					this.getService().insertBatchNotNull(EntiyList); //默认导入数据不做校验
					return this.success("导入成功!(共"+EntiyList.size()+"条记录)") ;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//this.logger.info("E==========="+e.getMessage() );
					return this.error(e.getMessage()) ; 
					//e.printStackTrace();
				}
		 }
	}
	


}

