package com.wiserun.common.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.wiserun.common.DefaultMessageResult;
import com.wiserun.common.DurcException;
import com.wiserun.common.Edit;
import com.wiserun.common.MessageResult;
import com.wiserun.common.dao.BaseDao;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;
import com.wiserun.common.util.ClassUtil;
import com.wiserun.common.util.DateUtil;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wiserun.common.BaseEntity;
import com.wiserun.common.PublicCode;

/**
 * 负责增删改查的Service
 * 
 * @author tanghc
 * 
 * @param <Entity>
 * @param <Dao>
 */
public abstract class CrudService<Entity, Dao extends BaseDao<Entity>> extends
		SearchService<Entity, Dao> implements Edit<Entity> {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	//********************//
	/** 新增动作 */
    private  final int Oper_Insert= 0;
    /** 修改动作 */
    private  final int Oper_Update= 1;
    /** 逻辑删除动作 */
    private  final int Oper_Lgdel= 2;
    /** 物理删除动作 */
    private  final int Oper_Del= 3;


	//方法排序顺序-依照：O-通用类操作、A-数据库操作(增、删、改)执行前可执行性的判定方法、B-数据库操作（增删改）前置方法、C-数据库操作方法(增删改)、D-数据库批量操作、E-数据库操作（增删改）后置方法 
	
	///////////O-以下为通用类操作方法////////////////////////////
	
	
	
	/**
	 * 对象是否存在
	 * 
	 * @param entity
	 *            对象
	 * @return 存在返回true
	 */
	public boolean exist(Entity entity) {
		return this.get(entity) != null;
	}
	/**
	 * 不对空对象操作
	 * @param entity
	 */
	private void checkNull(Entity entity) {
		if (entity == null) {
			throw new DurcException("对象不能为null");
		}
	}
	///////////以上为通用类操作方法////////////////////////////
	
	
	//////////以下为A-数据库操作(增、删、改)执行前可执行性的判定方法//////////////
		
	/**
	 * （新增）存盘前可执行性的判定方法----可在各业务模块中加以扩展重写
	 * 此方法具有一定限制性
	 * 仅在 Service 的 insertByCheck ， insertNotNullByCheck 自动调用；或在业务逻辑中 手动调用 
	 * @param entity
	 * @return MessageResult 如 MessageResult.isError 则表示判断失败
	 */
	public MessageResult insertCheck(Entity entity){
		this.logger.info("底层逻辑新增前判定======"); 
		MessageResult mes; //消息对象 
		////////底层逻辑默认无判断--根据全局需要扩展////////////////
		mes=this.success(); 
	    return mes;
	}
	/**
	 * （修改）存盘前可执行性的判定方法----可在各业务模块中加以扩展重写
	 * 此方法具有一定限制性
	 * 仅在 Service 的 updateByCheck ， updateNotNullByCheck 自动调用；或在业务逻辑中 手动调用 
	 * @param entity
	 * @return MessageResult 如 MessageResult.isError 则表示判断失败
	 */
	public MessageResult updateCheck(Entity entity){
		this.logger.info("底层逻辑修改前判定======");
		MessageResult mes; //消息对象
		////////底层逻辑默认无判断--根据全局需要扩展////////////////
		mes=this.success();
	    return mes;
	}
	/**
	 * （物理删除）前可执行性的判定方法----可在各业务模块中加以扩展重写
	 * 此方法具有一定限制性
	 * 仅在 Service 的 delByCheck  自动调用；或在业务逻辑中 手动调用 
	 * @param entity
	 * @return MessageResult 如 MessageResult.isError 则表示判断失败
	 */
	public MessageResult delCheck(Entity entity){
		MessageResult mes; //消息对象
		////////底层逻辑默认无判断--根据全局需要扩展////////////////
		mes=this.success();
	    return mes;
	}
	/**
	 * （逻辑删除）存盘前可执行性的判定方法----可在各业务模块中加以扩展重写：
	 * 此方法具有一定限制性
	 * 仅在 Service 的 lgdelByCheck 自动调用；或在业务逻辑中 手动调用 
	 * @param entity
	 * @return MessageResult 如 MessageResult.isError 则表示判断失败
	 */
	public MessageResult lgdelCheck(Entity entity){
		this.logger.info("底层逻辑删除前判定======");
		MessageResult mes; //消息对象
		////////底层逻辑默认无判断--根据全局需要扩展////////////////		
		mes=this.success();
	    return mes;
	}

	//////////以上为A2-数据库操作(增、删、改)执行前可执行性的判定方法//////////////

	
	
	//以下为B-数据库操作（增删改）前置方法，一般在业务模块中加以扩展，自动调用///////
		/**
		 * 公共-（新增或更新）存盘前处理代码--可在各业务模块中加以扩展重写
		 * @param entity
		 */
		public void insertOrUpdateBefore(Entity entity){
			this.logger.info("底层存盘前预处理======");
			if (null==entity) return ; //为空则跳出
			//清除存盘数据前后空格
			ClassUtil.ObjTrim(entity);
		}
		
		/**
		 * 公共-逻辑删除前处理代码
		 * @param entity
		 */
		public void lgdelBefore(Entity entity){
			this.logger.info("底层删除前预处理======");
			//根据当前状态取反（用于删除或恢复）
			if (null==entity) return ; //为空则跳出
			String sStatus="";
			sStatus=((BaseEntity)entity).getState();
			if (PublicCode.Status_No.equalsIgnoreCase(sStatus)){
	         	//如果记录属于删除状态，则恢复
				sStatus=PublicCode.Status_Yes;
	         }else{
	         	//其它情况下执行删除操作
	        	sStatus=PublicCode.Status_No;
	         }
			((BaseEntity)entity).setState(sStatus);
			/*
			Class<?> cls = entity.getClass();
			Method[] methods = cls.getMethods();
			try {
				String sStatus="";
				for (Method m:methods){
					if ("getStatus".equals(m.getName())){ //状态（获取）
						sStatus=m.invoke(entity).toString() ;
					}
				}
		        
				if ("0".equalsIgnoreCase(sStatus)){
		         	//如果记录属于删除状态，则恢复
					sStatus="1";
		         }else{
		         	//其它情况下执行删除操作
		        	 sStatus="0";
		         }
				for (Method m:methods){
					if ("setStatus".equals(m.getName())){ //状态（设置）
						m.invoke(entity, sStatus);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
		}
		
		/**
		 * 公共-物理删除前处理代码--可在各业务模块中加以扩展重写
		 * @param entity
		 */
		public void delBefore(Entity entity){
			if (null==entity) return ; //为空则跳出
		}
		

		/**
		 * 公共-（新增或更新）批量存盘后处理代码--可在各业务模块中加以扩展重写
		 * --供特殊业务逻辑用
		 * @param List<Entity> --批量（新增或更新）对象
		 */
		public void insertOrUpdateBatchBefore(List<Entity> EntiyList){
			this.logger.info("底层批量存盘前处理======");
			if (null==EntiyList) return ; //为空则跳出
		}
		/**
		 * 公共-批量逻辑删除后处理代码--可在各业务模块中加以扩展重写
		 * --供特殊业务逻辑用
		 * @param List<Entity> --批量（逻辑删除）对象
		 */
		public void lgdelBatchBefore(List<Entity> EntiyList){
			this.logger.info("底层批量逻辑删除前处理======");
			if (null==EntiyList) return ; //为空则跳出
		}
		/**
		 * 公共-批量物理删除后处理代码--可在各业务模块中加以扩展重写
		 * --供特殊业务逻辑用
		 * @param List<Entity> --批量（物理删除）对象
		 */
		public void delBatchBefore(List<Entity> EntiyList){
			this.logger.info("底层批量物理删除前处理======");
			if (null==EntiyList) return ; //为空则跳出
		}
		
		
	//以上为数据库操作（增删改）前置方法，一般在业务模块中加以扩展///////
	
		
	//以下为C-数据库操作方法(增删改)///(部分简单增删改方法带存盘前判定)////
	//////////---C1 含存盘前判定的方法	
		
	/**
	 * 新增或修改(带存盘前校验) -忽略空值 预定义方法 	
     * 根据对象 entity ID值判断，如为NULL则按 新增 处理，否则按 修改 处理
  	 * @param entity
	 *            实体对象
	 * @return MessageResult
	 */
	public MessageResult insertOrUpdateNotNullByCheck(Entity entity) {
		if (null== ((BaseEntity) entity).getId() || (!this.exist(entity)) ){
	    	//如果ID为空或者对象在数据库中不存在，则新增
	    	return this.insertNotNullByCheck(entity) ;
	    }else{
	    	//否则 修改
	    	return this.updateNotNullByCheck(entity) ;
	    }
	}	
		
	/**
	 * 新增数据(带存盘前校验)
	 * 
	 * @param entity
	 *            实体对象
	 * @return MessageResult 如果成功返回新id，失败则返回错误原因
	 */
	public MessageResult insertByCheck(Entity entity) {
		if (null==entity) return this.error("无可操作对象") ; //为空则跳出
		
		((BaseEntity)entity).setId(null); //强制设置id为 null，确保可用重新获取id
		
		//需要做执行前判断
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)this.insertCheck(entity);  //执行前判定
		if(mes.getIsError())  {
			return mes; //不执行操作，直接退出
		}else{
			
		}
			
		if (this.insert(entity)>0){
			return this.success(((BaseEntity)entity).getId()); //执行完毕，返回ID
		}else{
			return this.error("存盘失败,请检查数据!");
		}
		
	}
	/**
	 * 新增数据(带存盘前校验),忽略空值
	 * 
	 * @param entity
	 *            实体对象
	 * @return  MessageResult 如果成功返回新id，失败则返回错误原因
	 */
	public MessageResult insertNotNullByCheck(Entity entity) {
		if (null==entity) return this.error("无可操作对象") ; //为空则跳出
		((BaseEntity)entity).setId(null); //强制设置id为 null，确保可用重新获取id
		
		//需要做执行前判断
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)this.insertCheck(entity);  //执行前判定
		if(mes.getIsError())  {
			return mes; //不执行操作，直接退出
		}else{
			
		}
			
		if (this.insertNotNull(entity)>0){
			return this.success(((BaseEntity)entity).getId()); //执行完毕，返回ID
		}else{
			return this.error("存盘失败,请检查数据!");
		}
		
	}
	/**
	 * 修改数据(带存盘前校验)
	 * 
	 * @param entity
	 *            实体对象
	 * @return MessageResult 如果成功返回id，失败则返回错误原因
	 */
	public MessageResult updateByCheck(Entity entity) {
		if (null==entity) return this.error("无可操作对象") ; //为空则跳出
		
		//需要做执行前判断
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)this.updateCheck(entity);  //执行前判定
		if(mes.getIsError())  {
			return mes; //不执行操作，直接退出
		}else{
			
		}
			
		if (this.update(entity)>0){
			return this.success(((BaseEntity)entity).getId()); //执行完毕，返回ID
		}else{
			return this.error("存盘失败,请检查数据!");
		}
		
	}
	/**
	 * 修改数据(带存盘前校验)，忽略空值
	 * 
	 * @param entity
	 *            实体对象
	 * @return MessageResult 如果成功返回id，失败则返回错误原因
	 */
	public MessageResult updateNotNullByCheck(Entity entity) {
		if (null==entity) return this.error("无可操作对象") ; //为空则跳出
		
		//需要做执行前判断
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)this.updateCheck(entity);  //执行前判定
		if(mes.getIsError())  {
			return mes; //不执行操作，直接退出
		}else{
			
		}
			
		if (this.updateNotNull(entity)>0){
			return this.success(((BaseEntity)entity).getId()); //执行完毕，返回ID
		}else{
			return this.error("存盘失败,请检查数据!");
		}
		
	}
	
	/**
	 * 删除数据(带存盘前校验)
	 * 
	 * @param entity
	 *            实体对象
	 * @return MessageResult 如果成功返回id，失败则返回错误原因
	 */
	public MessageResult delByCheck(Entity entity) {
		if (null==entity) return this.error("无可操作对象") ; //为空则跳出
		
		//需要做执行前判断
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)this.delCheck(entity);  //执行前判定
		if(mes.getIsError())  {
			return mes; //不执行操作，直接退出
		}else{
			
		}
			
		if (this.del(entity)>0){
			return this.success(((BaseEntity)entity).getId()); //执行完毕，返回ID
		}else{
			return this.error("删除失败,请检查数据!");
		}
		
	}
	/**
	 * 逻辑删除数据(带存盘前校验)
	 * 
	 * @param entity
	 *            实体对象
	 * @return MessageResult 如果成功返回id，失败则返回错误原因
	 */
	public MessageResult lgdelByCheck(Entity entity) {
		if (null==entity) return this.error("无可操作对象") ; //为空则跳出
		
		//需要做执行前判断
		DefaultMessageResult mes; //消息对象
		mes=(DefaultMessageResult)this.lgdelCheck(entity);  //执行前判定
		if(mes.getIsError())  {
			return mes; //不执行操作，直接退出
		}else{
			
		}
			
		if (this.lgdel(entity)>0){
			return this.success(((BaseEntity)entity).getId()); //执行完毕，返回ID
		}else{
			return this.error("删除失败,请检查数据!");
		}
		
	}
	
	
	//////////---C2 不含存盘前判定的方法
	/**
	 * 新增数据--不推荐，改用 insert
	 * 
	 * @param entity
	 *            实体对象
	 * @return 受到影响的行数
	 */
	@Deprecated
	@Override
	public int save(Entity entity) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.insertOrUpdateBefore(entity);//存盘前处理
		i= this.getDao().save(entity);
		if(i>0){
			this.insertOrUpdateAfter(entity);//操作成功则进行：存盘后处理
		}
		return i;
	}
	/**
	 * 新增（忽略空数据）
	 * //新增-考虑到统一命名规范，等同save
	 * @param entity
	 *            实体对象
	 * @return 受到影响的行数
	 */
	@Override
	public int insert(Entity entity) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.insertOrUpdateBefore(entity);//存盘前处理
		i= this.getDao().insert(entity);
		if(i>0){
			this.insertOrUpdateAfter(entity); //操作成功则进行：存盘后处理
		}
		return i;
	}
	/**
	 * 根据判断来保存数据
	 * 
	 * @param entity
	 * @param saveHandler
	 *            实现该接口用来判断能否保存,返回true则调用save(entity)方法,返回false不保存
	 * @return 受到影响的行数
	 */
	@Deprecated
	public int save(Entity entity, SaveHandler<Entity> saveHandler) {
		if (saveHandler.canSave(entity)) {
			return this.save(entity);
		}
		return 0;
	}
	
	/**
	 * 新增（忽略空数据）--不推荐 ，改用 insertNotNull
	 * @param entity
	 *            实体对象
	 * @return 受到影响的行数
	 */
	@Deprecated
	@Override
	public int saveNotNull(Entity entity) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.insertOrUpdateBefore(entity);//存盘前处理
		i= this.getDao().saveNotNull(entity);
		if(i>0){
			this.insertOrUpdateAfter(entity); //操作成功则进行：存盘后处理
		}
		return i;
	}
	/**
	 * 新增（忽略空数据）
	 * //新增-考虑到统一命名规范，等同saveNotNull
	 * @param entity
	 *            实体对象
	 * @return 受到影响的行数
	 */
	@Override
	public int insertNotNull(Entity entity) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.insertOrUpdateBefore(entity);//存盘前处理
		i= this.getDao().insertNotNull(entity);
		if(i>0){
			this.insertOrUpdateAfter(entity); //操作成功则进行：存盘后处理
		}
		return i;
	}



	/**
	 * 删除
	 * 
	 * @param entity
	 *            实体对象
	 * @return 受到影响的行数
	 */
	@Override
	public int del(Entity entity) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.delBefore(entity); //删除前预处理
		i= this.getDao().del(entity);
		if(i>0){
			this.delAfter(entity); //操作成功则进行：存盘后处理
		}
		return i;
	}
	/**
	 * 根据条件删除
	 * @param query 条件对象
	 * @return 受到影响的行数
	 */
	@Override
	public int delByExpression(ExpressionQuery query) {
		//-----批量删除无任何前置或后置操作
		int i;
		this.delBefore(null); 
		i= this.getDao().delByExpression(query);
		if(i>0){
			this.delAfter(null); //操作成功则进行：存盘后处理
		}
		return i;
		
	}
	
	
	/**
	 * 逻辑删除
	 * @param entity
	 * @return
	 */
	public int lgdel(Entity entity) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.lgdelBefore(entity); //存盘前处理
		i= this.getDao().updateNotNull(entity); //实际上是更新状态位
		if(i>0){
			this.lgdelAfter(entity);//操作成功则进行：存盘后处理
		}
		return i;
	}
	

	/**
	 * 修改
	 * 
	 * @param entity
	 *            实体对象
	 * @return 受到影响的行数
	 */
	@Override
	public int update(Entity entity) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.insertOrUpdateBefore(entity);//存盘前处理
		i= this.getDao().update(entity);
		if(i>0){
			this.insertOrUpdateAfter(entity); //操作成功则进行：存盘后处理
		}
		return i;
	}
	  
	/**
	 * 根据条件更新所有字段
	 * @param entity
	 *            实体对象
	 * @param query 条件对象
	 * @return 受到影响的行数
	 */
	@Override
	public int updateByExpression(Entity entity, ExpressionQuery query) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.insertOrUpdateBefore(entity);//存盘前处理
		i= this.getDao().updateByExpression(entity, query);
		if(i>0){
			this.insertOrUpdateAfter(entity); //操作成功则进行：存盘后处理
		}
		return i;
	}

	/**
	 * 根据主键更新不为null的字段
	 * @param entity
	 *            实体对象
	 * @return 受到影响的行数
	 */
	@Override
	public int updateNotNull(Entity entity) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.insertOrUpdateBefore(entity);//存盘前处理
		i= this.getDao().updateNotNull(entity);
		if(i>0){
			this.insertOrUpdateAfter(entity); //操作成功则进行：存盘后处理
		}
		return i;
	}

	/**
	 * 根据条件更新不为null的字段
	 * @param entity
	 *            实体对象
	 * @param query 条件对象
	 * @return 受到影响的行数
	 */
	@Override
	public int updateNotNullByExpression(Entity entity, ExpressionQuery query) {
		int i;
		checkNull(entity); //对象为空则抛出异常
		this.insertOrUpdateBefore(entity);//存盘前处理
		i= this.getDao().updateNotNullByExpression(entity, query);
		if(i>0){
			this.insertOrUpdateAfter(entity); //操作成功则进行：存盘后处理
		}
		return i;
	}

	
	//
	//以上为数据库操作方法(增删改)///////
	
	
	//以下为D-数据库批量操作方法/////（部分方法含存盘前判定）//////////////////
	private String getErrMsg(String sOperType,int rowNumber,String sErrMsg){
		return "第 "+ (rowNumber) +" 条"+sOperType+"记录 "+sErrMsg+" ";
	}
	/**
	 * 批量数据库操作（不含存盘前判定）
	 * @param iOperType 0-新增，1-修改，2-逻辑删除 3- 物理删除
	 * @param EntiyList 实体对象LIST
	 * @return MessageResult 消息对象
	 * @throws RuntimeException
	 */
	public MessageResult dbBatchNotNull(int iOperType ,List<Entity> EntiyList) throws RuntimeException {
        return this.dbBatchNotNullByCheck(iOperType, EntiyList, false, false, false);
	}	
	/**
	 * 批量数据库操作-----含存盘前判定
	 * @param iOperType 0-新增，1-修改，2-逻辑删除 3- 物理删除
	 * @param EntiyList 实体对象LIST
	 * @param isNeedByCheck --//是否需要操作前 判定
	 * @param isNeedCheckAll--//是否需要判定所有数据（如为false则在 第一次判定出错时就回滚事务退出 ）
	 * @param isNeedRunChecked--//如为前台grid传递，这个值必须为false  ； 表示是否需要完成已通过判定的数据（如为false则 有一个判定出错，最终就回滚事务退出 ）-依赖  isNeedCheckAll=true；
	 * @return MessageResult 消息对象 注意：这个对象一般用在操作全部成功，或者导入数据时允许忽略错误； 
	 *                                     否则如发生了需要回滚的错误，那么无法获取到返回值；
	 *      其中message属性为提示文本，attach对象保存 详细的操作结果，每行格式为：
	 *         {
	 *         ser:原id(新增记录时一般为行序号)
	 *         return:id（新增记录时为最终id）
	 *         isError: 执行是否成功
	 *         message: 单行提示文本
	 *         } 
	 * @throws RuntimeException
	 */
	public MessageResult dbBatchNotNullByCheck(int iOperType ,List<Entity> EntiyList,boolean isNeedByCheck,boolean isNeedCheckAll,boolean isNeedRunChecked) throws RuntimeException {
		if(null==EntiyList) return this.success(); //无执行内容直接退出
		
		String sOperType=""; //当前进行  类型 操作文本（用于提示文本）
		String sErrMsg=""; //当前提示信息
		
		/*
		 * 记录错误提示文本（无论是判定错误还是其他操作错误）
		 * 如无须校验，则此结果集为空
		 * 最终放置在返回 MessageResult的message属性
		 */
		List<String> errMsgList= new ArrayList<String>();
		
		/*
		 * 记录执行结果信息（原id(新增记录时一般为行序号)，id（新增记录时为最终id），执行是否成功，返回的提示文本
		 * 如无须校验，则每行执行结果都是成功（例外失败将直接回滚事务）
		 * 最终转字符串后 ，放置在返回 MessageResult的attach属性
		*/
		List<Object> resultList=new ArrayList<Object>(); //只保存出错提示
		//JSONObject jsonResult=new  JSONObject(); 
		
		
		DefaultMessageResult mes; //消息对象
		this.logger.info("开始批量"+sOperType+"时间===="+DateUtil.getCurrentDateTime());
		
		//批量操作前处理---判断批量操作类型
		switch(iOperType){
			case 0:
				sOperType="新增";//新增;
				this.insertOrUpdateBatchBefore(EntiyList); //批量操作前处理
			    break;
			case 1:
				sOperType="修改";//修改...;
				this.insertOrUpdateBatchBefore(EntiyList); //批量操作前处理
			    break;
			case 2:
				sOperType="删除";//逻辑删除...;
				this.lgdelBatchBefore(EntiyList); //批量操作前处理
			    break;
			case 3:
				sOperType="删除";//物理删除...;
				this.delBatchBefore(EntiyList); //批量操作前处理
			    break;
			default:
				sOperType=""; //...;
			    break;
		}
		
		int i=0; 
		for (Entity entity : EntiyList) {
			Map<String,Object> map=new HashMap<String, Object>();  //本行执行结果初始化
			map.put("preid", ((BaseEntity)entity).getId()); //原id(新增记录时一般为行序号)
			map.put("returnId", ((BaseEntity)entity).getId()); //id（新增记录时为最终id）
			map.put("isError",false); //执行是否成功，默认为成功
			map.put("message",""); //返回的提示文本
				
			//循环操作，如果本行操作成功，则mes 中返回 id 信息
			try{
				i++;
				switch(iOperType){
				case 0:
					//新增;
					if (isNeedByCheck){
						mes= (DefaultMessageResult)this.insertNotNullByCheck (entity); //调用单条记录处理代码
					}else{
						if (this.insertNotNull(entity)>0) {mes=(DefaultMessageResult)this.success(((BaseEntity)entity).getId());} else{mes=(DefaultMessageResult)this.error("");}
					}
				    break;
				case 1:
					//修改...;
					if (isNeedByCheck){
						mes= (DefaultMessageResult)this.updateNotNullByCheck (entity); //调用单条记录处理代码
					}else{
						if (this.updateNotNull(entity)>0) {mes=(DefaultMessageResult)this.success(((BaseEntity)entity).getId());} else{mes=(DefaultMessageResult)this.error("");}
					}
				    break;
				case 2:
					//逻辑删除...;
					if (isNeedByCheck){
						mes= (DefaultMessageResult)this.lgdelByCheck (entity); //调用单条记录处理代码
					}else{
						if (this.lgdel(entity)>0) {mes=(DefaultMessageResult)this.success(((BaseEntity)entity).getId());} else{mes=(DefaultMessageResult)this.error("");}
					}
				    break;
				case 3:
					//物理删除...;
					if (isNeedByCheck){
						mes= (DefaultMessageResult)this.delByCheck (entity); //调用单条记录处理代码
					}else{
						if (this.del(entity)>0) {mes=(DefaultMessageResult)this.success(((BaseEntity)entity).getId());} else{mes=(DefaultMessageResult)this.error("");}
					}

				    break; 
				default:
					 //无操作...;
					mes=(DefaultMessageResult)this.error("无效操作");
				    break;
				}
			}catch(Exception e) {
				//由于系统原因（如EXCEL中数据格式不对等），无法获取明确的中文说明，不提供详细错误信息  //this.logger.info("错误原因："+e.getMessage());
				throw new RuntimeException(sOperType+ "失败,请检查(第 "+ (i) +" 条记录)!");
			}	
			
			//仅在需要校验时处理(无须校验的情况下)
			if (isNeedByCheck ){
				//如果有错误，应当记录出错的数据原ID及现ID（考虑到有新增情况，原ID值一般为行序号）
				if (mes.getIsError()){
					//本行执行失败，由于校验失败，记录异常
					//提示文本
					sErrMsg=this.getErrMsg(sOperType, i, mes.getMessage()) ;
					//提示文本集合
					errMsgList.add(sErrMsg); //
					 //本行执行结果
					map.put("returnId", ""); //id（新增记录时为最终id）
					map.put("isError",true); //执行成功
					map.put("message",mes.getMessage()); //返回的提示文本
					
					if (!isNeedCheckAll){//无须判断所有记录时，有错误就回滚退出
						throw new RuntimeException(sOperType+"失败:"+errMsgList.toString()+"!");
					}
				}else{
					//本行执行成功
					/*
					map.put("returnId", mes.getMessage()); //id（新增记录时为最终id）
					map.put("isError",false); //执行成功
					map.put("message",mes.getMessage()); //返回的提示文本
					*/
				}
			}else{
				//无须校验的情况下，每行执行情况必定为：成功
				/*
				map.put("returnId", mes.getMessage()); //id（新增记录时为最终id）
				map.put("isError",false); //执行成功
				map.put("message",mes.getMessage()); //返回的提示文本
				*/
			}
			//记录执行结果
			this.logger.info("完成map=="+map);
			resultList.add(map);
		 }
		this.logger.info("完成批量"+sOperType+"时间===="+DateUtil.getCurrentDateTime());
		
		this.logger.info("完成resultList=="+resultList.size()+"=="+resultList.toString());
		
		
		//批量操作后处理
		switch(iOperType){
			case 0:
				//sOperType="新增";//修改...;
				this.insertOrUpdateBatchAfter(EntiyList); //批量操作后处理
			    break;
			case 1:
				//sOperType="修改";//修改...;
				this.insertOrUpdateBatchAfter(EntiyList); //批量操作后处理
			    break;
			case 2:
				//sOperType="删除";//逻辑删除...;
				this.lgdelBatchAfter(EntiyList); //批量操作后处理
			    break;
			case 3:
				//sOperType="删除";//物理删除...;
				this.delBatchAfter(EntiyList); //批量操作后处理
			    break;
			default:
				sOperType=""; //...;
			    break;
		}
		
				
		//准备返回		
				
		
		if (i==0){
			return this.success(""); //无数据可操作时，不做任何提示 sOperType+"失败,无可处理数据!"
		}else{
			//循环执行完毕后判断
			if (errMsgList.size()>0  ){ //有判定错误
				if (!isNeedRunChecked){
				     //无须完成已通过判定的数据 且 判定过程中有错误发生
					throw new RuntimeException(errMsgList.toString()); //抛出出错记录描述
				}else{
					//须完成已通过判定的数据 且 判定过程中有错误发生
					//返回执行结果（含每行执行结果）
					mes=(DefaultMessageResult) this.error("已"+ sOperType+": "+ (i-errMsgList.size()) +" 条数据、有: "+errMsgList.size()+"条数据未处理，原因:"+errMsgList.toString());
					mes.setAttach(resultList); 
					//返回
					return mes;
				}
			}else{
				return this.success("已"+ sOperType+": "+i+" 条数据 ");
			}
		}
	}
	
	/**
	 * 批量存盘 
	 * 支持同时存在 insert update lgdel del 操作
	 * 前台传入的存盘参数 下标 0-新增数据 1-修改数据 2-逻辑删除数据 3-物理删除数据
	 * 如不需要某类操作，可以传入null
	 * @param   entityInserted    新增数据      
	 * @param   entityUpdated     修改数据
	 * @param   entitylgDeleted   逻辑删除数据
	 * @param   entityDeleted     物理删除数据
	 *             
	 * @param isNeedCheckAll //是否需要判定所有数据（如为false则在 第一次判定出错时就回滚事务退出 ）
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
	public MessageResult savebatchByCheck(List<Entity> entityInserted ,List<Entity> entityUpdated ,List<Entity> entitylgDeleted ,List<Entity> entityDeleted ,boolean isNeedCheckAll) throws RuntimeException {
		DefaultMessageResult mes=null; //消息对象
		 ////////////明细表格对象//////////////

	
	 	 ///////批量执行开始//////////////
		 List<String> errList= new ArrayList<String>();//获取错误操作提示，根据执行过程中是否遇到非回滚操作类型错误（无论是判定错误还是其他操作错误）
		 List<String> scuList= new ArrayList<String>();//获取成功操作提示
		 //List<Object> resultList= new ArrayList<Object>();//获取增删改全部操作的明细结果

      	//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	 	try{ 
	 		for(int i=0;i<4;i++){
	 			switch(i){
				case 0:
					//新增操作
			 		//批量新增////
				 	mes=(DefaultMessageResult) this.insertBatchNotNullByCheck(entityInserted,isNeedCheckAll ) ; //执行批量新增(含执行前判定)
				 	this.logger.info("批量新增结果=="+ClassUtil.convertObj2Map(mes)  );
					break;
				case 1:
				 	//批量修改////
				 	mes=(DefaultMessageResult) this.updateBatchNotNullByCheck(entityUpdated,isNeedCheckAll) ; //执行批量修改(含执行前判定)
				 	this.logger.info("批量修改结果=="+ClassUtil.convertObj2Map(mes)  );
					break;
				case 2:
				 	//批量逻辑删除
				 	mes=(DefaultMessageResult)this.lgdelBatchByCheck(entitylgDeleted,isNeedCheckAll) ; //执行批量逻辑删除(含执行前判定)
				 	this.logger.info("批量删除结果=="+ClassUtil.convertObj2Map(mes)  );
					break;
				case 3:
				 	//批量物理删除
				 	mes=(DefaultMessageResult)this.delBatchByCheck(entityDeleted,isNeedCheckAll) ; //执行批量物理删除(含执行前判定)
				 	this.logger.info("批量物理结果=="+ClassUtil.convertObj2Map(mes)  );
					break;
	 			}	
			 	if (mes.getIsError()){
					//由于部分校验失败，记录异常
			 		errList.add(mes.getMessage()) ;
				}else{
					if (!"".equals(mes.getMessage())) scuList.add(mes.getMessage()) ;
				}
	 		}
	 	}catch(Exception e) {
			//由于系统原因（如EXCEL中数据格式不对等），无法获取明确的中文说明，不提供详细错误信息  //this.logger.info("错误原因："+e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	 	
	 	//待定--如 isNeedCheckAll =true 则循环中如果捕获异常，暂时不抛出，循环结束后统一抛出
	 	
	 	
	 	//this.logger.info("最终errList=="+errList.size()+"=="+errList.toString());
	 	//this.logger.info("最终resultList=="+resultList.size()+"=="+resultList.toString());
	 	
	 	if (errList.size()>0){
	 		//执行过程有错误(还是执行了)
	 		scuList.addAll(errList);
	 	}
	 	
	 	mes= (DefaultMessageResult) this.success(scuList.toString());
	 	//mes.setAttach(resultList); //获取增删改全部操作的明细结果
	 	this.logger.info("最终执行结果=="+ClassUtil.convertObj2Map(mes)  );
	 	
	 	///////批量执行结束//////////////
	 	return mes;
   	
	}
	
	//D1-含存盘前判定的 批量处理
	/**
	 * 批量新增（忽略空数据）---含存盘前判定（有错误即终止并回滚）
	 * @param EntiyList
	 *            实体对象LIST
	 * @return MessageResult
	 * @throws RuntimeException  
	 */
	@Transactional //采用事务模式
	public MessageResult insertBatchNotNullByCheck(List<Entity> EntiyList) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNullByCheck(this.Oper_Insert, EntiyList, true, false, false);
	}    
	/**
	 * 批量新增（忽略空数据）---含存盘前判定（判定所有数据，如有错误，回滚不存盘）
	 * 注：此类操作影响性能，慎用！
	 * @param EntiyList
	 *            实体对象LIST
	 * @param isNeedCheckAll true:判定所有数据 false:有错误即终止判定
	 * @return MessageResult
	 * @throws RuntimeException  
	 */
	@Transactional //采用事务模式
	public MessageResult insertBatchNotNullByCheck(List<Entity> EntiyList,boolean isNeedCheckAll) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNullByCheck(this.Oper_Insert, EntiyList, true, isNeedCheckAll, false);
	}  	
	/**
	 * 批量新增（忽略空数据）---含存盘前判定（判定所有数据，忽略有错误的记录不操作）
	 * 注：此类操作影响性能，慎用！
	 * @param EntiyList
	 *            实体对象LIST
	 * @param isNeedCheckAll true:判定所有数据 false:有错误即终止判定
	 * @param isNeedRunChecked true:可以保存部分判定无错的数据  false：不可以保存部分判定无错的数据            
	 * @return MessageResult
	 * @throws RuntimeException  
	 */
	@Transactional //采用事务模式
	public MessageResult insertBatchNotNullByCheck(List<Entity> EntiyList,boolean isNeedCheckAll,boolean isNeedRunChecked) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNullByCheck(this.Oper_Insert, EntiyList, true, isNeedCheckAll, isNeedRunChecked);
	}
	
	/**
	 * 批量更新（忽略空数据）---含存盘前判定
	 * @param EntiyList
	 *            实体对象LIST
	 * @return MessageResult
	 * @throws RuntimeException  如有例外错误，返回信息格式固定为：存盘失败!请检查(第 "+ (i) +" 条记录)! + 校验返回提示
	 */
	@Transactional //采用事务模式
	public MessageResult updateBatchNotNullByCheck(List<Entity> EntiyList) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
		 return this.dbBatchNotNullByCheck(this.Oper_Update, EntiyList, true, true, true);
	}
	/**
	 * 批量修改（忽略空数据）---含存盘前判定（判定所有数据，如有错误，回滚不存盘）
	 * 注：此类操作影响性能，慎用！
	 * @param EntiyList
	 *            实体对象LIST
	 * @param isNeedCheckAll true:判定所有数据 false:有错误即终止判定
	 * @return MessageResult
	 * @throws RuntimeException  
	 */
	@Transactional //采用事务模式
	public MessageResult updateBatchNotNullByCheck(List<Entity> EntiyList,boolean isNeedCheckAll) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNullByCheck(this.Oper_Update, EntiyList, true, isNeedCheckAll, false);
	}  	
	/**
	 * 批量修改（忽略空数据）---含存盘前判定（判定所有数据，忽略有错误的记录不操作）
	 * 注：此类操作影响性能，慎用！
	 * @param EntiyList
	 *            实体对象LIST
	 * @param isNeedCheckAll true:判定所有数据 false:有错误即终止判定
	 * @param isNeedRunChecked true:可以保存部分判定无错的数据  false：不可以保存部分判定无错的数据            
	 * @return MessageResult
	 * @throws RuntimeException  
	 */
	@Transactional //采用事务模式
	public MessageResult updateBatchNotNullByCheck(List<Entity> EntiyList,boolean isNeedCheckAll,boolean isNeedRunChecked) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNullByCheck(this.Oper_Update, EntiyList, true, isNeedCheckAll, isNeedRunChecked);
	}
	
	/**
	 * 批量逻辑删除（忽略空数据）---含存盘前判定
	 * @param EntiyList
	 *            实体对象LIST
	 * @return MessageResult
	 * @throws RuntimeException 
	 */
	@Transactional //采用事务模式
	public MessageResult lgdelBatchByCheck(List<Entity> EntiyList) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
		 return this.dbBatchNotNullByCheck(this.Oper_Lgdel, EntiyList, true, true, true);
	}
	/**
	 * 批量逻辑删除（忽略空数据）---含存盘前判定（判定所有数据，如有错误，回滚不存盘）
	 * 注：此类操作影响性能，慎用！
	 * @param EntiyList
	 *            实体对象LIST
	 * @param isNeedCheckAll true:判定所有数据 false:有错误即终止判定
	 * @return MessageResult
	 * @throws RuntimeException  
	 */
	@Transactional //采用事务模式
	public MessageResult lgdelBatchByCheck(List<Entity> EntiyList,boolean isNeedCheckAll) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNullByCheck(this.Oper_Lgdel, EntiyList, true, isNeedCheckAll, false);
	}  	
	/**
	 * 批量逻辑删除（忽略空数据）---含存盘前判定（判定所有数据，忽略有错误的记录不操作）
	 * 注：此类操作影响性能，慎用！
	 * @param EntiyList
	 *            实体对象LIST
	 * @param isNeedCheckAll true:判定所有数据 false:有错误即终止判定
	 * @param isNeedRunChecked true:可以保存部分判定无错的数据  false：不可以保存部分判定无错的数据            
	 * @return MessageResult
	 * @throws RuntimeException  
	 */
	@Transactional //采用事务模式
	public MessageResult lgdelBatchByCheck(List<Entity> EntiyList,boolean isNeedCheckAll,boolean isNeedRunChecked) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNullByCheck(this.Oper_Lgdel, EntiyList, true, isNeedCheckAll, isNeedRunChecked);
	}
	
	/**
	 * 批量物理删除---含存盘前判定
	 * @param EntiyList
	 *            实体对象LIST
	 * @return MessageResult
	 * @throws RuntimeException 
	 */
	@Transactional //采用事务模式
	public MessageResult delBatchByCheck(List<Entity> EntiyList) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
		return this.dbBatchNotNullByCheck(this.Oper_Del, EntiyList, true, true, true);
	}
	/**
	 * 批量物理删除（忽略空数据）---含存盘前判定（判定所有数据，如有错误，回滚不存盘）
	 * 注：此类操作影响性能，慎用！
	 * @param EntiyList
	 *            实体对象LIST
	 * @param isNeedCheckAll true:判定所有数据 false:有错误即终止判定
	 * @return MessageResult
	 * @throws RuntimeException  
	 */
	@Transactional //采用事务模式
	public MessageResult delBatchByCheck(List<Entity> EntiyList,boolean isNeedCheckAll) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNullByCheck(this.Oper_Del, EntiyList, true, isNeedCheckAll, false);
	}  	
	/**
	 * 批量物理删除（忽略空数据）---含存盘前判定（判定所有数据，忽略有错误的记录不操作）
	 * 注：此类操作影响性能，慎用！
	 * @param EntiyList
	 *            实体对象LIST
	 * @param isNeedCheckAll true:判定所有数据 false:有错误即终止判定
	 * @param isNeedRunChecked true:可以保存部分判定无错的数据  false：不可以保存部分判定无错的数据            
	 * @return MessageResult
	 * @throws RuntimeException  
	 */
	@Transactional //采用事务模式
	public MessageResult delBatchByCheck(List<Entity> EntiyList,boolean isNeedCheckAll,boolean isNeedRunChecked) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNullByCheck(this.Oper_Del, EntiyList, true, isNeedCheckAll, isNeedRunChecked);
	}	
	///
	
	
	//D2-不含存盘前判定的 批量处理
	/**
	 * 批量新增（忽略空数据）
	 * @param EntiyList
	 *            实体对象LIST
	 * @return MessageResult
	 * @throws RuntimeException 
	 */
	@Transactional //采用事务模式
	public MessageResult insertBatchNotNull(List<Entity> EntiyList) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNull(this.Oper_Insert, EntiyList);
	}
	
	/**
	 * 批量更新（忽略空数据）
	 * @param EntiyList
	 *            实体对象LIST
	 * @return MessageResult
	 * @throws RuntimeException 
	 */
	@Transactional //采用事务模式
	public MessageResult updateBatchNotNull(List<Entity> EntiyList) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNull(this.Oper_Update, EntiyList);
	}
	
	/**
	 * 批量逻辑删除（忽略空数据）
	 * @param EntiyList
	 *            实体对象LIST
	 * @return MessageResult
	 * @throws RuntimeException 
	 */
	@Transactional //采用事务模式
	public MessageResult lgdelBatch(List<Entity> EntiyList) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNull(this.Oper_Lgdel, EntiyList);
	 }
	
	/**
	 * 批量物理删除
	 * @param EntiyList
	 *            实体对象LIST
	 * @return MessageResult
	 * @throws RuntimeException 
	 */
	@Transactional //采用事务模式
	public MessageResult delBatch(List<Entity> EntiyList) throws RuntimeException {
		//以下操作如果需要回滚，必须通过抛出RuntimeException方式进行
	 	//若返回mes.getIsError()=true 表示 执行过程有错误，但还是继续执行了
	    return this.dbBatchNotNull(this.Oper_Del, EntiyList);
	}
	
	//以上为数据库批量操作方法///////
	
	
	
	//以下为E-数据库操作（增删改）后置方法，一般在业务模块中加以扩展，自动调用///////
		/**
		 * 公共-（新增或更新）存盘后处理代码--可在各业务模块中加以扩展重写
		 * @param entity
		 */
		public void insertOrUpdateAfter(Entity entity){
			this.logger.info("底层存盘后处理======");
			if (null==entity) return ; //为空则跳出
		}
		
		/**
		 * 公共-逻辑删除后处理代码
		 * @param entity
		 */
		public void lgdelAfter(Entity entity){
			this.logger.info("底层删除后处理======");
			if (null==entity) return ; //为空则跳出
		}
		
		/**
		 * 公共-物理删除后处理代码--可在各业务模块中加以扩展重写
		 * @param entity
		 */
		public void delAfter(Entity entity){
			if (null==entity) return ; //为空则跳出
		}
		
		/**
		 * 公共-（新增或更新）批量存盘后处理代码--可在各业务模块中加以扩展重写
		 * --供特殊业务逻辑用
		 * @param List<Entity> --批量（新增或更新）对象
		 */
		public void insertOrUpdateBatchAfter(List<Entity> EntiyList){
			this.logger.info("底层批量存盘后处理======");
			if (null==EntiyList) return ; //为空则跳出
		}
		/**
		 * 公共-批量逻辑删除后处理代码--可在各业务模块中加以扩展重写
		 * --供特殊业务逻辑用
		 * @param List<Entity> --批量（逻辑删除）对象
		 */
		public void lgdelBatchAfter(List<Entity> EntiyList){
			this.logger.info("底层批量逻辑删除后处理======");
			if (null==EntiyList) return ; //为空则跳出
		}
		/**
		 * 公共-批量物理删除后处理代码--可在各业务模块中加以扩展重写
		 * --供特殊业务逻辑用
		 * @param List<Entity> --批量（物理删除）对象
		 */
		public void delBatchAfter(List<Entity> EntiyList){
			this.logger.info("底层批量物理删除后处理======");
			if (null==EntiyList) return ; //为空则跳出
		}
		
     //以上为数据库操作（增删改）后置方法，一般在业务模块中加以扩展///////

}
