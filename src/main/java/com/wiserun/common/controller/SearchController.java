package com.wiserun.common.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wiserun.common.DefaultGridResult;
import com.wiserun.common.EntityProcessor;
import com.wiserun.common.GridResult;
import com.wiserun.common.Sch;
import com.wiserun.common.SearchSupport;
import com.wiserun.common.UserContext;
import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;
import com.wiserun.common.util.ClassUtil;
import com.wiserun.common.util.MyToolsUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wiserun.common.PublicCode;

/**
 * 负责查询的Controller,新建的Controller如果只有查询功能可以继承这个类
 * 
 * rebuild 20181210
 * 
 * @param <Entity>
 *            实体类
 * @param <Service>
 *            查询的Service
 */
public abstract class SearchController<Entity, Service extends Sch<Entity>> 
extends BaseController {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	public SearchController(){
		super();
	}

	
	//方法排序顺序-依照：A-通用类操作方法、B-与前台提交参数有关、C-查询前处理类型方法、D-查询类型方法
	
	///////////A-以下为通用类操作方法////////////////////////////
	private static final List<Expression> EMPTY_EXPRESSIONS = Collections.emptyList();
	
	private Service service;

	public Service getService() {
		return service;
	}

	@Autowired
	public void setService(Service service) {
		this.service = service;
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
	///////////A-以上为通用类操作方法////////////////////////////
	
	
	///////////以下为B-与前台提交参数有关////////////////////////////
	/**
	 * 将 查询对象转换为 查询表达式
	 * @param searchEntity
	 * @return
	 */
	protected ExpressionQuery buildExpressionQuery(SearchSupport searchEntity) {
		return MyToolsUtil.buildExpressionQuery(searchEntity);
	}
	
	/**
	 * 将 request 参数转换为 Map<String, Object>
	 * hades
	 * @param request
	 * @return
	 */
	public Map<String, Object> request2Map() {
		
		return MyToolsUtil.request2Map();
	}

	/**
	 * 将 request 参数转换为Query 对象
	 * hades
	 * @param request
	 * @return
	 */
	public ExpressionQuery request2ExpressionQuery() {
		return MyToolsUtil.request2ExpressionQuery();
	}	
	///////////以上为B-与前台提交参数有关///////////////////////////

	
	///////////以下为C-查询前处理类型方法////////////////////////////
	/**
	 * 设置查询属性 不带权限（一般用于获取全局配置） 
	 * @param query 传入查询用参数集合
	 */
	protected <F> void setBeforeQueryNoLimit(ExpressionQuery query) {
		MyToolsUtil.setBeforeQueryNoLimit(query);
	}
	/**
	 *设置默认查询属性  （带权限） 
	 * @param query 传入查询用参数集合
	 */
	protected <F> void setBeforeQuery(ExpressionQuery query) {
		MyToolsUtil.setBeforeQuery(query);
	}
	/**
	 *设置默认查询属性  （带权限） 
	 * @param o 传入查询用参数对象
	 */
	protected <F> void setBeforeQuery(F o) {
		MyToolsUtil.setBeforeQuery(o);
	}
	///////////以上为C-查询前处理类型方法///////////////////////////

	///////////以下为D-查询类型方法////////////////////////////
	public GridResult success(List<Entity> list){
		GridResult result = getGridResult();
		
		result.setList(list);
		result.setTotal(list.size());
		result.setPageCount(1);
		result.setPageIndex(1);
		
		return result;
	}
	
	/**
	 * 返回默认的结果类
	 * @return 默认的结果类
	 */
	protected GridResult getGridResult() {
		return new DefaultGridResult();
	}
	
	/**
	 * 通过对象获取记录,可以传主键值,也可以传整个对象
	 * @param id 如:get(21)或get(student)
	 * @return 返回实体对象
	 */
	public Entity get(Object id) {
		return this.service.get(id);
	}
	
	/**
	 * 根据查询类查询
	 * @param searchEntity
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult query(SearchSupport searchEntity) {
		return this.query(searchEntity, EMPTY_EXPRESSIONS);
	}
	
	/**
	 * 根据查询类查询
	 * @param searchEntity
	 * @param expressions 附加查询条件
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult query(SearchSupport searchEntity,List<Expression> expressions) {
		ExpressionQuery query = this.buildExpressionQuery(searchEntity);
		query.addAll(expressions);
		return this.query(query);
	} 
	
	/**
	 * 根据查询条件查询
	 * @param query
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult query(ExpressionQuery query) {
		return this.queryWithProcessor(query, null);
	}
	
	/**
	 * 根据查询类查询全部
	 * @param searchEntity 查询类
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult queryAll(SearchSupport searchEntity) {
		return this.queryAll(searchEntity, EMPTY_EXPRESSIONS);
	}
	
	/**
	 * 根据查询类查询全部
	 * @param searchEntity 查询类
	 * @param expressions 附加查询条件
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult queryAll(SearchSupport searchEntity,List<Expression> expressions) {
		ExpressionQuery query = this.buildExpressionQuery(searchEntity);
		query.addAll(expressions);
		return this.queryAll(query);
	}
	
	/**
	 * 根据查询条件查询全部
	 * @param query
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult queryAll(ExpressionQuery query) {
		query.setQueryAll(true);
		return this.query(query);
	}
	
	/**
	 * 根据查询类查询
	 * @param searchEntity 查询类
	 * @param processor 结果处理器
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult queryWithProcessor(SearchSupport searchEntity,EntityProcessor<Entity> processor) {
		return this.queryWithProcessor(searchEntity, EMPTY_EXPRESSIONS, processor);
	}
	
	/**
	 * 根据查询类查询
	 * @param searchEntity 查询类
	 * @param expressions 附加查询条件
	 * @param processor 结果处理器
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult queryWithProcessor(SearchSupport searchEntity,List<Expression> expressions,EntityProcessor<Entity> processor) {
		ExpressionQuery query = this.buildExpressionQuery(searchEntity);
		query.addAll(expressions);
		return this.queryWithProcessor(query, processor);
	}
	
	/**
	 * 查询全部
	 * @param query
	 * @param processor 结果处理器
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult queryAllWithProcessor(ExpressionQuery query,EntityProcessor<Entity> processor) {
		query.setQueryAll(true);
		return this.queryWithProcessor(query, processor);
	}
	
	/**
	 * 根据查询条件查询
	 * @param query 查询条件
	 * @param processor 结果处理器
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult queryWithProcessor(ExpressionQuery query,
			EntityProcessor<Entity> processor) {
		//改用 通用方法执行查询命令 hades 20181229
		return this.queryWithProcessor(null, null, query, processor);
		
		/*
		GridResult result = getGridResult();
		
		try{
			
			List<Entity> list = this.service.find(query);
			
			int total = 0; // 总条数
			int start = query.getStart();
			int pageSize = query.getPageSize(); // 每页记录数
			int pageIndex = (start / pageSize) + 1; // 当前第几页
			
			if(list.size() > 0) {
				// 总数
				// 如果是查询全部则直接返回结果集条数
				// 如果是分页查询则还需要带入条件执行一下sql
				total = query.getIsQueryAll() ? list.size() : this.service.findTotalCount(query);
			}
					
			
			result.setList(list);
			result.setTotal(total);
			result.setStart(start);
			result.setPageIndex(pageIndex);
			result.setPageSize(pageSize);
			
			int pageCount = DefaultGridResult.calcPageCount(total, pageSize);
			
			result.setPageCount(pageCount);
			
			if(processor != null) {
				List<Object> jsonObjList = processEntityToJSONObject(list, processor);
				result.setList(jsonObjList);
			}
			
		}catch(Exception e) {
			result.setMessage(e.getMessage());
			result.setSuccess(false);
		}
		
		return result;
		*/
	}
	
	/**
	 * 动态指定查询SQL方法id，根据这一特定方法查询，如 findwithtables
	 * 重要注意事项：
	 * 1-mapper 文件中需要存在id为 QueryId 的代码段如 findwithtables
	 * 2-mapper文件中必须存在id为QueryId并附加TotalCount后缀的代码段，如findwithtablesTotalCount
	 * 3-DAO接口对象中必须定义QueryId 及 QueryId+TotalCount 的接口方法，供调用
	 * @param DaoClass 对象的DAO接口对象
	 * @param QueryId SQL 方法id，必须与 Mapper 文件中 id 一致
	 * @param query 查询条件
	 * @param processor 结果处理器
	 * @return 默认返回DefaultGridResult对象,可以重写getGridResult()方法返回自定义的GridResult
	 */
	public GridResult queryWithProcessor(Object DaoClass, String QueryId,ExpressionQuery query,
			EntityProcessor<Entity> processor) {
		
		    GridResult result = getGridResult();
		
		try{
			List<Entity> list;
			if(null==DaoClass || null==QueryId){
				//默认情况下，通过service调用DAO find的方法，执行查询（mapper中id为'find'的代码）
			    list = this.service.find(query);
			}else{
				//如果指定了查询接口就使用自定义查询，通过DAO对象调用QueryId方法（mapper中id为QueryId的代码）
				list =(List<Entity>) ClassUtil.invoke(DaoClass, QueryId, query); //自定义查询
			}
			int total = 0; // 总条数
			int start = query.getStart();
			int pageSize = query.getPageSize(); // 每页记录数
			int pageIndex = (start / pageSize) + 1; // 当前第几页
			
			if(list.size() > 0) {
				// 总数
				// 如果是查询全部则直接返回结果集条数
				// 如果是分页查询则还需要带入条件执行一下sql
				if(null==DaoClass || null==QueryId){
					//默认情况下，通过service调用DAO findTotalCount的方法，执行查询（mapper中id为'findTotalCount'的代码）
					total = query.getIsQueryAll() ? list.size() : this.service.findTotalCount(query);
				}	
				else{
					//如果指定了查询接口就使用自定义查询，通过DAO对象调用QueryId+TotalCount方法（mapper中id为QueryId+TotalCount的代码）
					total = query.getIsQueryAll() ? list.size() : (int) ClassUtil.invoke(DaoClass, QueryId+"TotalCount", query);;	
				}
			}
					
			
			result.setList(list);
			result.setTotal(total);
			result.setStart(start);
			result.setPageIndex(pageIndex);
			result.setPageSize(pageSize);
			
			int pageCount = DefaultGridResult.calcPageCount(total, pageSize);
			
			result.setPageCount(pageCount);
			
			if(processor != null) {
				List<Object> jsonObjList = processEntityToJSONObject(list, processor);
				result.setList(jsonObjList);
			}
			
		}catch(Exception e) {
			result.setMessage(e.getMessage());
			result.setSuccess(false);
		}
		
		return result;
	}
	///////////以上为D-查询类型方法////////////////////////////
	
	
	///////////以下为E-消息类相关方法////////////////////////////
	
	
	///////////以上为E-消息类相关方法///////////////////////////

}
