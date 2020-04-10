package com.wiserun.devolop.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.wiserun.common.DefaultMessageResult;
import com.wiserun.common.MessageResult;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;
import com.wiserun.common.service.CrudService;
import com.wiserun.common.util.ClassUtil;
import com.wiserun.common.util.MyBeanUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wiserun.devolop.system.dao.SysFunctionDao;
import com.wiserun.devolop.system.entity.SysFunction;
import com.wiserun.devolop.system.entity.SysFunctionSch;
import com.wiserun.devolop.system.service.SysFunctionService;//用于读取代码表数据
import com.wiserun.permission.common.UserOperation;
import com.wiserun.permission.common.UserPermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：项目号设置 SERVICE层相关操作
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

@Service
public class SysFunctionService extends CrudService<SysFunction, SysFunctionDao> {
	
	@Autowired
	SysFunctionService SysFunctionservice;

	private final Logger logger = Logger.getLogger(this.getClass());
	//********************以下区域方法为系统默认产生，不可修改方法名，但应根据业务需求修改内部逻辑********************//
	//********************//方法排序顺序-依照：A-对象属性预处理、B-查询（前处理）、C-数据库操作(增、删、改) 可行性处理、D-增/改 /删 执行前处理、E-增/改 /删 执行后处理 、F-批量存盘操作 的类型顺序排列
	
	////////以下为A-对象属性预处理方法///////////////////////////////////////////////////
	
	/**
	 * 根据代码信息ID获取详细数据
	 * 此方法一般在业务Service insertOrUpdateBefore 方法 中 自动调用
	 * 
	 * 由于部分代码类数据需要同时保存ID,CODE,NAME三类信息，因此需要在后台根据ID做处理
	 * 根据传入对象所引用的某个代码ID，如ProjectTypeID 设置 对象的 ProjectType- 项目类型信息（代码，名称）
	 * 无业务需求则可不做任何处理 
	 */
	public void setInfoByID(SysFunction entity) {
		
		 //根据实际需要扩展--如前台已传入相应数据，这里也可以不做任何处理，以提高执行效率
		SysFunction sysFunction;
		if(null!=entity.getId()){
			sysFunction=SysFunctionservice.get(entity.getId());
			
			if (null!=sysFunction){
	           //entity.setProjectTypeCode(sysFunction.getDetailCode()); //项目代码
	           //entity.setProjectTypeName(sysFunction.getCodeName()); //项目名称
			}
		}
        
	}
	
	/**
	 * 导入数据的预处理（如有导入功能，可根据需求扩展）
	 * 根据代码信息名称获取ID等数据
	 * 此方法一般在 导入业务Controller 存盘方法调用前 手动调用
	 * 
	 * 由于部分代码类数据需要同时保存ID,CODE,NAME三类信息，导入数据时可能只有NAME类信息
	 * 根据传入对象所引用的某个NAME信息，如ProjectType 设置 对象的 ProjectTypeID,ProjectTypeCode- 项目类型信息（ID,代码）
	 * 无业务需求则可不做任何处理 
	 */
	public void setInfoByName(SysFunction entity) {
		
		 //根据实际需要扩展
	
        
	}
	/////////以上为A-对象属性预处理方法//////////////////////////////////////////////////////////////
	
	//////////以下为B-数据库操作(查询)前预处理方法//////////////
	/////////////注：此段所有方法均扩展自底层CrudService
	
	/**
	 * 查询前处理代码--根据需要扩展:设置查询属性 不带权限（一般用于获取全局配置） 
	 * 考虑到不同业务场景需求，此方法必须人工调用，不会自动调用
	 * @param query 传入查询用参数集合
	 */
	public ExpressionQuery queryNoLimitBefore(ExpressionQuery query) {
		super.setBeforeQueryNoLimit(query);
		//业务逻辑
		//设置默认排序条件--根据需要修改
    	if (null==query.getOrder()) { ////////////////////////////////searchEntity.getSortname()
    		query.addSort("rowOrder", "asc");  //设置默认的组合排序
    		query.addSort("createDate", "desc");
    	}
		/////////////////
		return query;
	}
	/**
	 * 查询前处理代码--不带权限
	 * 根据需要扩展(一般无需处理，业务逻辑可放置在 参数为 query 时的方法)
	 * 考虑到不同业务场景需求，此方法必须人工调用，不会自动调用
	 * 整理为 query 形式返回
	 * @param entity
	 */
	public ExpressionQuery queryNoLimitBefore(SysFunctionSch entity){
		ExpressionQuery query = new ExpressionQuery();
		query=this.buildExpressionQuery(entity);
    	return this.queryNoLimitBefore(query); 
	}
	/**
	 * 查询前处理代码--带权限
	 * 根据需要扩展(一般无需处理，业务逻辑可放置在 参数为 query 时的方法)
	 * 考虑到不同业务场景需求，此方法必须人工调用，不会自动调用
	 * 整理为 query 形式返回
	 * @param entity
	 */
	public ExpressionQuery queryBefore(SysFunctionSch entity){
		ExpressionQuery query = new ExpressionQuery();
		query=this.buildExpressionQuery(entity);
    	return this.queryBefore(query); 
	}
	/**
	 * 查询前处理代码--带权限
	 * 根据需要扩展
	 * 考虑到不同业务场景需求，此方法必须人工调用，不会自动调用
	 * @param entity
	 */
	public ExpressionQuery queryBefore(ExpressionQuery query) {
		super.setBeforeQuery(query);
		//业务逻辑
		//设置默认排序条件--根据需要修改
    	if (null==query.getOrder()) { ////////////////////////////////searchEntity.getSortname()
    		query.addSort("rowOrder", "asc");  //设置默认的组合排序
    		query.addSort("createDate", "desc");
    	}
		////
		return query;
		
	    //查询参数扩展-参考
        //
        //query.add(new ValueExpression("name", "JIM")); //等于 条件
        //query.add(new ValueExpression("name",">=", "JIM")); // 大于 小于 条件
        //query.add(new LikeDoubleExpression("mobile", "133")); // 模糊查询条件 也可以用 LikeRightExpression 等模式
		//query.add(new ListExpression("id", Arrays.asList(31,37))); // IN 数值集合条件
        //以上默认为 AND 条件
        //query.add(new SqlExpression("gender=0 or id in (19,20)")); //自定义SQL
        //query.add(new ValueExpression(SqlContent.OR, "gender", "=", 0)); // WHERE 中使用 OR 条件
		//多表查询
		//query.addJoinExpression(new DefaultJoinExpression(" inner join department t2 on t.DEPARTMENT = t2.ID"));
		//多表查询条件
		//query.add(new ValueExpression("t2.department_name", "外语系"));
        //
     
   
	}
	
	//////////以上为B-数据库操作(查询)前预处理方法//////////////
	
	//////////以下为C-数据库操作(增、删、改)执行前可执行性的判定方法//////////////
	/////////////注：此段所有方法均扩展自底层CrudService

	
	/**
	 * （新增）存盘前可执行性的判定方法--根据需要扩展：
	 * 此方法具有一定限制性
	 * 仅在 Service 的 insertByCheck ， insertNotNullByCheck 自动调用；或在业务逻辑中 手动调用 
	 * @param entity
	 * @return MessageResult 如 MessageResult.isError 则表示判断失败
	 */
	public MessageResult insertCheck(SysFunction entity){
		
		
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)super.insertCheck(entity);  //全局检测
		if (mes.getSuccess()){
			this.logger.info("业务逻辑新增前判定======");
		
			List<SysFunction> list;
	        ExpressionQuery query = ExpressionQuery.buildQueryAll();
			//请在此处预处理查询条件
            //query.add(new ValueExpression("projectNumber", entity.getProjectNumber() )); //判定项目号是否重复 
	        this.queryBefore(query) ; //查询预处理
			list =this.find(query);
	       
			if(list.size()>0) {
            	//请在此处处理返回的错误信息
				//mes=(DefaultMessageResult)this.error("项目号("+entity.getProjectNumber()+")重复!");
			}else {
	            mes=(DefaultMessageResult)this.success();	
			}
			//根据需要做业务级判定(默认有效)
			//mes=(DefaultMessageResult)this.success();
       	}  
	    return mes;
		/*
		List<SysFunction> list;
        ExpressionQuery query = ExpressionQuery.buildQueryAll();
        //请在此处预处理查询条件
		//query.add(new ValueExpression("projectNumber", entity.getProjectNumber() )); //判定项目号是否重复 
        this.queryBefore(query) ; //查询预处理
		list =this.find(query);
       
		if(list.size()>0) {
			mes= this.error("存盘失败，原因:项目号重复!");
		}else {
            mes=this.success();	
		}
		*/
		///////////////////////////    
		
	   
	}
	/**
	 * （修改）存盘前可执行性的判定方法--根据需要扩展：
	 * 此方法具有一定限制性
	 * 仅在 Service 的 updateByCheck ， updateNotNullByCheck 自动调用；或在业务逻辑中 手动调用 
	 * @param entity
	 * @return MessageResult 如 MessageResult.isError 则表示判断失败
	 */
	public MessageResult updateCheck(SysFunction entity){
		
		
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)super.updateCheck(entity);  //全局检测
		if (mes.getSuccess()){
			this.logger.info("业务逻辑修改前判定======");
			//根据需要做业务级判定(默认有效)
			//mes=(DefaultMessageResult)this.success();
       	}  
	    return mes;
	}
	/**
	 * （物理删除）前可执行性的判定方法--根据需要扩展：
	 * 此方法具有一定限制性
	 * 仅在 Service 的 delByCheck  自动调用；或在业务逻辑中 手动调用 
	 * @param entity
	 * @return MessageResult 如 MessageResult.isError 则表示判断失败
	 */
	public MessageResult delCheck(SysFunction entity){
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)super.delCheck(entity);  //全局检测
		if (mes.getSuccess()){
			this.logger.info("业务删除前判定======");
			//根据需要做业务级判定(默认有效)
			//mes=(DefaultMessageResult)this.success();
       	}  
	    return mes;
	}
	/**
	 * （逻辑删除）存盘前可执行性的判定方法--根据需要扩展：
	 * 此方法具有一定限制性
	 * 仅在 Service 的 lgdelByCheck 自动调用；或在业务逻辑中 手动调用 
	 * @param entity
	 * @return MessageResult 如 MessageResult.isError 则表示判断失败
	 */
	public MessageResult lgdelCheck(SysFunction entity){
		
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)super.lgdelCheck(entity);  //全局检测
		if (mes.getSuccess()){
			this.logger.info("业务逻辑删除前判定======");
			//根据需要做业务级判定(默认有效)
			//mes=(DefaultMessageResult)this.success();
       	}  
	    return mes;
	}

	//////////以上为C-数据库操作(增、删、改)执行前可执行性的判定方法//////////////
	
	
	//////////以下为D-数据库操作(增、删、改)执行前预处理方法//////////////
	/////////////注：此段所有方法均扩展自底层CrudService
	
	/**
	 * （新增或更新）存盘前处理代码--根据需要扩展：具体业务逻辑需在 this.setCodeInfo等方法中实现
	 * 此方法将在通过service新增或更新数据时自动调用
	 * @param entity
	 */
	public void insertOrUpdateBefore(SysFunction entity){
		
		super.insertOrUpdateBefore(entity);	
		this.logger.info("业务存盘前处理======");
		
		this.setInfoByID(entity);
		entity.savebefore();
		this.logger.info("存盘对象===" + ClassUtil.convertObj2Map(entity));
		
	}
	/**
	 * 逻辑删除前处理代码--根据需要扩展
	 * 此方法将在通过service逻辑删除数据时自动调用
	 * @param entity
	 */
	public void lgdelBefore(SysFunction entity){
		super.lgdelBefore(entity);
		this.logger.info("业务删除前处理======");
		entity.savebefore(); //实际做的是存盘操作
	}
	
	/**
	 * 物理删除前处理代码--根据需要扩展
	 * 此方法将在通过service物理删除数据时自动调用
	 * @param entity
	 */
	public void delBefore(SysFunction entity){
		super.delBefore(entity); 
		this.logger.info("业务物理删除前处理======");
	}
	
	/**
	 * 公共-（新增或更新）批量存盘前处理代码--可在各业务模块中加以扩展重写
	 * 此方法将在通过service 批量新增或修改数据时自动调用
	 * --供特殊业务逻辑用
	 * @param List<Entity> --批量（新增或更新）对象
	 */
	public void insertOrUpdateBatchBefore(List<SysFunction> EntiyList){
		if (null==EntiyList) return ; //为空则跳出
		super.insertOrUpdateBatchBefore(EntiyList);
		this.logger.info("业务批量存盘前处理======");
		
	}
	/**
	 * 公共-批量逻辑删除前处理代码--可在各业务模块中加以扩展重写
	 * 此方法将在通过service 批量逻辑删除数据时自动调用
	 * --供特殊业务逻辑用
	 * @param List<Entity> --批量（逻辑删除）对象
	 */
	public void lgdelBatchBefore(List<SysFunction> EntiyList){
		if (null==EntiyList) return ; //为空则跳出
		super.lgdelBatchBefore(EntiyList);
		this.logger.info("业务批量逻辑删除前处理======");
		
	}
	/**
	 * 公共-批量物理删除前处理代码--可在各业务模块中加以扩展重写
	 * 此方法将在通过service 批量物理修改据时自动调用
	 * --供特殊业务逻辑用
	 * @param List<Entity> --批量（物理删除）对象
	 */
	public void delBatchBefore(List<SysFunction> EntiyList){
		if (null==EntiyList) return ; //为空则跳出
		super.delBatchBefore(EntiyList);
		this.logger.info("业务批量物理删除前处理======");

	}

	///////////////////D-以上为数据库操作(增、删、改)类型执行前预处理方法////////////////////
	
	///-------数据库实际操作代码直接使用底层CrudService方法--简单的增删改查操作应避免直接调用DAO操作---///
	
	///////////////////E-以下为数据库操作(增、删、改)类型完成后的处理方法（不含查询动作），可完成如存盘后需要保存更新日志等业务需求///////////
	/////////////注：此段所有方法均扩展自底层CrudService
	/**
	 * 公共-（新增或更新）存盘后处理代码--可在各业务模块中加以扩展重写
	 * 此方法将在通过service 新增或修改数据时自动调用
	 * @param entity
	 */
	public void insertOrUpdateAfter(SysFunction entity){
		if (null==entity) return ; //为空则跳出
		super.insertOrUpdateAfter(entity); 
		this.logger.info("业务存盘后处理======");
	}
	
	/**
	 * 公共-逻辑删除后处理代码
	 * 此方法将在通过service逻辑删除数据时自动调用
	 * @param entity
	 */
	public void lgdelAfter(SysFunction entity){
		if (null==entity) return ; //为空则跳出
		super.lgdelAfter(entity);
		this.logger.info("业务删除后处理======");
	}
	
	/**
	 * 公共-物理删除后处理代码--可在各业务模块中加以扩展重写
	 * 此方法将在通过service物理删除数据时自动调用
	 * @param entity
	 */
	public void delAfter(SysFunction entity){
		if (null==entity) return ; //为空则跳出
		super.delAfter(entity);
		this.logger.info("业务删除后处理======");
	}
	
	/**
	 * 公共-（新增或更新）批量存盘后处理代码--可在各业务模块中加以扩展重写
	 * 此方法将在通过service 批量新增或修改数据时自动调用
	 * --供特殊业务逻辑用
	 * @param List<Entity> --批量（新增或更新）对象
	 */
	public void insertOrUpdateBatchAfter(List<SysFunction> EntiyList){
		this.logger.info("业务批量存盘后处理======");
		if (null==EntiyList) return ; //为空则跳出
		super.insertOrUpdateBatchAfter(EntiyList);
	}
	/**
	 * 公共-批量逻辑删除后处理代码--可在各业务模块中加以扩展重写
	 * 此方法将在通过service 批量逻辑删除数据时自动调用
	 * --供特殊业务逻辑用
	 * @param List<Entity> --批量（逻辑删除）对象
	 */
	public void lgdelBatchAfter(List<SysFunction> EntiyList){
		this.logger.info("业务批量逻辑删除后处理======");
		if (null==EntiyList) return ; //为空则跳出
		super.lgdelBatchAfter(EntiyList);
	}
	/**
	 * 公共-批量物理删除后处理代码--可在各业务模块中加以扩展重写
	 * 此方法将在通过service 批量物理修改据时自动调用
	 * --供特殊业务逻辑用
	 * @param List<Entity> --批量（物理删除）对象
	 */
	public void delBatchAfter(List<SysFunction> EntiyList){
		this.logger.info("业务批量物理删除后处理======");
		if (null==EntiyList) return ; //为空则跳出
		super.delBatchAfter(EntiyList);
	}

	
	
	////////////////////E-以上为数据库操作(增、删、改)类型完成后的处理方法//////////////////
	
	////////////////////F-以下为数据库操作(批量存盘操作)类型的处理方法////////////////////////
	/**
	 * 批量存盘 
	 * 支持多form或多grid
	 * @param map 前台传入的存盘参数 （每个form或grid的数据都单独作为map的一个元素）
	 * @return MessageResult 执行结果
	 *    其中message属性为提示文本，attach对象保存 详细的操作结果，每行格式为：
	 *         {
	 *         ser:原id(新增记录时一般为行序号)
	 *         return:id（新增记录时为最终id）
	 *         isError: 执行是否成功
	 *         message: 单行提示文本
	 *         } 
	 */
	@Transactional //采用事务模式/////////
	public MessageResult batchByCheckSysFunction(Map<String, Object> map) throws RuntimeException {
		//this.logger.info("request参数map==="+map); 
		DefaultMessageResult mes; //消息对象
		
		//注--如果要进行一对多关系的数据存盘，或多个表格数据存盘，必须在一个事务中处理
		
		////////////主表对象-map.get 获取的form名需要与前台保持一致//////////////
		
		 SysFunctionSch entity=JSON.parseObject((String)(map.get("schForm")), SysFunctionSch.class); //form名称：schForm 由前台页面指定
		 this.logger.info("主表数据debug=="+ClassUtil.convertObj2Map(entity) );
		 /*
	      * 
	      * 这里可以追加业务逻辑判断
	       //如涉及1对多关系存盘，则得到ID后需要将ID传递给批量更新的明细数据 		 
		 */
		
		
		
		 ////////////明细表格对象 dg//////////////
	   	 JSONObject jsondg =JSON.parseObject((String)map.get("dg")); //表格名称：dg 由前台页面指定
	   	 
	   	 if (null==jsondg || jsondg.isEmpty() ){
	   		 return this.error("没有表格数据,放弃存盘!");
	   	 }
	   	
	   	 List<SysFunction> entityInserted=JSON.parseArray((String)jsondg.get("inserted"), SysFunction.class);
	   	 List<SysFunction> entityUpdated=JSON.parseArray((String)jsondg.get("updated"), SysFunction.class);
	 	 List<SysFunction> entitylgDeleted=JSON.parseArray((String)jsondg.get("deleted"), SysFunction.class);
	 	 
	 	 ///////批量执行开始//////////////
      	//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	 	try{ 
	 	 	 mes=(DefaultMessageResult) this.savebatchByCheck(entityInserted,entityUpdated,entitylgDeleted,null, false); //批量执行，有错误就回滚
		 	 //if(null!=mes.getAttach()) resultList.addAll((List<Object>)mes.getAttach()); //获取执行的明细记录
	 	}catch(Exception e) {
			//由于系统原因（如EXCEL中数据格式不对等），无法获取明确的中文说明，不提供详细错误信息  //this.logger.info("错误原因："+e.getMessage());
			throw new RuntimeException("放弃存盘：" + e.getMessage());
		}
        //////////表格对象处理完毕///////////
	 	
	 	return mes;
   	
	}
	/////////////////////F-以上为数据库操作(批量存盘操作)类型的处理方法/////////////////////////////////
	//********************以上区域方法为系统默认产生，不可修改方法名，但应根据业务需求修改内部逻辑********************//
	/**
	 * 构建用户权限
	 * @param userId
	 * @return
	 */
	public UserPermission buildUserPermission(String userId) {
		List<SysFunction> userSysFuns = this.getUserSysFunction(userId);
		
		UserPermission userPermission = new UserPermission();
		
		for (SysFunction sysFun : userSysFuns) {
			userPermission.addPermission(String.valueOf(sysFun.getSrId())
					, new UserOperation(sysFun.getOperateCode(), sysFun.getUrl()));
		}
		System.out.println("userPermission======"+userPermission);
		return userPermission;
	}	
	/**
	 * 获取用户系统功能
	 * @param userId
	 * @return
	 */
	public List<SysFunction> getUserSysFunction(String userId){
		return this.getDao().findUserSysFunction(userId); 
	}
	/**
	 * 通过资源ID查找系统功能
	 * @param srId
	 * @return
	 */
	public List<SysFunction> getBySySResId(String id) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
    	query.add(new ValueExpression("srId", id));
    	
    	return find(query);
	}
	
	
	//********************以下为其他扩展业务类型（如导入）的处理方法////请自行补充********************//
	
	
	//********************以上为其他扩展业务类型（如导入）的处理方法////请自行补充********************//
	
}