package com.wiserun.devolop.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.wiserun.common.DefaultMessageResult;
import com.wiserun.common.GridResult;
import com.wiserun.common.MessageResult;
import com.wiserun.common.ValidateHolder;
import com.wiserun.common.controller.CrudController;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;
import com.wiserun.common.util.ClassUtil;
import com.wiserun.common.util.ValidateUtil;

import com.wiserun.common.PublicCode; //公共常量
import com.wiserun.devolop.system.entity.SysDepartment;
import com.wiserun.devolop.system.dao.SysDepartmentDao;
import com.wiserun.devolop.system.entity.SysDepartmentSch;
import com.wiserun.devolop.system.service.SysDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：项目号设置 相关操作
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/
//以下数据可用于配置模块编辑权限
// insertOrUpdateSysDepartment,insertSysDepartment.do,updateSysDepartment.do,delSysDepartment.do,lgdelSysDepartment.do,batchSysDepartment
//以下数据可用于配置查看权限
// listSysDepartment.do,findRootSysDepartment.do,findSysDepartment.do,getSysDepartment.do
//以下数据一般挺可作为公共角色权限
// findRootSysDepartment.do,findSysDepartment.do

@Controller
public class SysDepartmentController extends
		CrudController<SysDepartment, SysDepartmentService> {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SysDepartmentService vService;
	@Autowired
	private SysDepartmentDao vDao;
    
    //********************以下区域方法为系统默认逻辑，一般不用改动//
	//方法排序顺序-依照：A-查询、B-增/删/改 、C-批量存盘操作 的类型顺序排列********************//
	 
    //////////////以下为 A-查询 类型 方法//////////////
	//get-获取单条记录(无特殊原因不要修改)
    @RequestMapping("/getSysDepartment.do")
	public @ResponseBody SysDepartment getSysDepartment(String id) {
 	    return this.getService().get(id);	 //返回对象
	}  


    //列表查询-适用于datagrid 预定义方法(无特殊原因不要修改-除非涉及是否分页，业务逻辑在service中queryBefore方法修改)
    @RequestMapping("/listSysDepartment.do")
	public @ResponseBody GridResult listSysDepartment(@RequestBody SysDepartmentSch searchEntity) {
    	

        //注意查询逻辑应确保与 打印机导入controller的打印接口逻辑一致
        ExpressionQuery query=this.getService().queryBefore(searchEntity);
    	//query.updateValueExpression(new ValueExpression("status", null  ) );//状态 查询所有数据
        GridResult grid=this.query(query); //分页 ////////////////////////
        //`this.queryAll(query); //不分页        
        
        //设置合计汇总列--打开后并设置前台grid showfooter 属性，前台grid即可显示
        /*
        //页脚处理
          */
        this.setGridFooter(query,grid);
        //////页脚处理完毕////////
       
        return grid;
	}
     //查询（不带权限）-返回LIST 预定义方法  不通过查询类检索 一般用于返回根租户全局配置  (无特殊原因不要修改，业务逻辑在service中queryNoLimitBefore方法修改)
      @RequestMapping("/findNoLimitSysDepartment.do")
	public @ResponseBody List<SysDepartment> findNoLimitSysDepartment() {
 		ExpressionQuery query = new ExpressionQuery();
        query=this.request2ExpressionQuery(); //读取request 参数
        
		this.getService().queryNoLimitBefore(query);//查询预处理(查询前手动调用)
		return this.getService().find(query);	 //返回列表，不带grid属性
        
    }  
   
    
    //查询-返回LIST 预定义方法  不通过查询类检索 一般用于后台查询 返回本账套的数据 (无特殊原因不要修改，业务逻辑在service中queryBefore方法修改)
      @RequestMapping("/findSysDepartment.do")
	public @ResponseBody List<SysDepartment> findSysDepartment() {
 		
       ExpressionQuery query = new ExpressionQuery();
        query=this.request2ExpressionQuery(); //读取request 参数
        
        this.getService().queryBefore(query);//查询预处理(查询前手动调用)
		return this.getService().find(query);	 //返回列表，不带grid属性
          
	}  
      //////////////以上为 A-查询 类型 方法//////////////
      
      //////////////以下为 B-增 删 改 类型 方法//////////////
      
       /**
       * 新增或修改-忽略空值 预定义方法 (此方法带强制存盘前判定)	
       * 根据对象 entity ID值判断，如为NULL则按 新增 处理，否则按 修改 处理
       * (无特殊原因不要修改，业务逻辑在service中isCanInsert，isCanUpdate，insertOrUpdateBefore，insertOrUpdateAfter方法修改)
       * @param entity
       * @return MessageResult 如果判断为新增数据，则返回消息为新id；如为修改数据，则返回消息为空
       */
      @RequestMapping("/insertOrUpdateSysDepartment.do")
    	public @ResponseBody MessageResult insertOrUpdateSysDepartment(@RequestBody SysDepartment entity) {
    	  	DefaultMessageResult mes; //消息对象
    	    if (null==entity) return this.error("无数据，放弃保存!");
    		
    		mes=(DefaultMessageResult)this.getService().insertOrUpdateNotNullByCheck(entity) ;
          	    
    		return mes;
       }  
      
  	/**
  	 * //新增-忽略空值 预定义方法 (此方法带强制存盘前判定)
  	 *  (无特殊原因不要修改，业务逻辑在service中isCanInsert，insertOrUpdateBefore，insertOrUpdateAfter方法修改)
  	 * @param entity
  	 * @return MessageResult 如果成功操作，则返回消息为新id；失败则返回原因
  	 */
     @RequestMapping("/insertSysDepartment.do")
  	public @ResponseBody MessageResult insertSysDepartment(@RequestBody SysDepartment entity) {
  		DefaultMessageResult mes; //消息对象
      	mes=(DefaultMessageResult)this.getService().insertNotNullByCheck(entity) ;
      	return mes;
     }
  	
 
    /**
	 * 删除-物理删除 预定义方法  (此方法带强制存盘前判定)
	 (无特殊原因不要修改，业务逻辑在service中isCandel，delBefore，delAfter方法修改)
	 * @param entity
	 * @return MessageResult 失败则返回原因
	 */
     @RequestMapping("/delSysDepartment.do")
	public @ResponseBody MessageResult delSysDepartment(@RequestBody SysDepartment entity) {
		DefaultMessageResult mes; //消息对象
      	mes=(DefaultMessageResult)this.getService().delByCheck(entity);
      	return mes;
	}
	
  	/**
  	 *     //删除-逻辑删除 等同更新状态 预定义方法(此方法带强制存盘前判定)
  	 *      (无特殊原因不要修改，业务逻辑在service中isCanlgdel，lgdelBefore，lgdelAfter方法修改)
  	 * @param entity
  	 * @return  MessageResult 失败则返回原因
  	 */
     @RequestMapping("/lgdelSysDepartment.do")
	public @ResponseBody MessageResult lgdelSysDepartment(@RequestBody SysDepartment entity) {
  		DefaultMessageResult mes; //消息对象
    	mes=(DefaultMessageResult)this.getService().lgdelByCheck(entity);
    	return mes;
  }
    
  	/**
 	 *   //更新-忽略空值 预定义方法(此方法带强制存盘前判定)
 	 *   (无特殊原因不要修改，业务逻辑在service中isCanUpdate，insertOrUpdateBefore，insertOrUpdateAfter方法修改)
 	 * @param entity
 	 * @return  MessageResult 失败则返回原因
 	 */
     @RequestMapping("/updateSysDepartment.do")
 	public @ResponseBody MessageResult updateSysDepartment(@RequestBody SysDepartment entity) {
 		DefaultMessageResult mes; //消息对象
       	mes=(DefaultMessageResult)this.getService().updateNotNullByCheck(entity);
    	return mes;
 	}
 	//////////////以上为 B-增 删 改 类型 方法//////////////
 	
 	//////////////以下为 C-批量存盘 类型 方法//////////////
   
     /**
      *  //批量处理表格编辑数据--适用于前台为表格编辑
      *  (无特殊原因不要修改)
      * @param id
      * @return  MessageResult 失败则返回原因
      */
     @RequestMapping("/batchSysDepartment.do")
	public @ResponseBody MessageResult batchSysDepartment(String id) {
    	 	Map<String, Object> map =this.request2Map();
    	 	MessageResult mes; //消息对象
    	 	/*
    	 	// 由于批量存盘，因此强制在事务做存盘前校验
    	 	 * 原因是：需要综合判断 全部数据：含待增删改查数据+数据库数据 
    	 	 */
    	 	try {
    	 		mes=this.getService().batchByCheckSysDepartment(map);
				return mes ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.logger.info("存盘错误==========="+e.getMessage() );
				e.printStackTrace();
				return this.error(e.getMessage()) ; 
				//e.printStackTrace();
			}
   }  
     //////////////以上为 C-批量存盘 类型 方法//////////////
     //********************以上区域方法为系统默认逻辑，一般不用改动********************//
     
     
     //********************以下区域方法均需要根据业务需求扩展********************//
     /**
      * 设置表格的页脚（如果需要请自行扩展）
      * @param query 查询表达式
      * @param grid 已有数据的grid对象
      */
     private void setGridFooter(ExpressionQuery query,GridResult grid){
         /*//样例--
    	 //页脚处理
    	 List<SysDepartment> result = new ArrayList<SysDepartment>();
         List<String> fcolumns =new ArrayList<String>() ; //Collections.emptyList();
         //注意：第一列一般显示标题，必须用 ** 作为前缀
         fcolumns.add("'**合计' as id"); //必须有，用于校验，文字可修改 也用于标题
         fcolumns.add("sum(rowOrder) as projectNumber"); //必须确保字段名与查询列表一致，数量可以不同
         fcolumns.add("'' as status");
         query.setColumns(fcolumns);
         
         result.addAll(this.getService().find(query)); //第一行页脚
          
         fcolumns.clear();
         fcolumns.add("'**平均' as id"); //必须有，用于校验，文字可修改 也用于标题
         fcolumns.add(" avg(rowOrder) as rowOrder");
         fcolumns.add("'' as status");
         query.setColumns(fcolumns);
         result.addAll(this.getService().find(query)); //第二行页脚（多行页脚以此类推）
         //grid.setFooter( this.getService().find(query)); //页脚数据
         grid.setFooter(result); //页脚数据
         */
       
     }
     
    //********************以上区域方法均需要根据业务需求扩展********************//
}
