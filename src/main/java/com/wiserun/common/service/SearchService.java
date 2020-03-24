package com.wiserun.common.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import com.wiserun.common.EntityProcessor;
import com.wiserun.common.MessageResult;
import com.wiserun.common.Sch;
import com.wiserun.common.SearchSupport;
import com.wiserun.common.dao.BaseDao;
import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.QBC;
import com.wiserun.common.expression.projection.ProjectionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;
import com.wiserun.common.util.ClassUtil;
import com.wiserun.common.util.MyToolsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 负责查询的Service
 * 
 * rebuild 2018-12-10
 * 
 * @param <Entity>
 * @param <Dao>
 */
public abstract class SearchService<Entity, Dao extends BaseDao<Entity>> implements Sch<Entity>{
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private Dao dao;
	
	//方法排序顺序-依照：A-通用类操作方法、B-与前台提交参数有关、C-查询前处理类型方法、D-查询类型方法、E-消息类相关方法 
	
	///////////A-以下为通用类操作方法////////////////////////////
	public Dao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	/**
	 * 获取httpRequest
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	///////////A-以上为通用类操作方法////////////////////////////
	
		
	////////////B-以下为与前台提交参数有关的一些公共方法///////
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
	
	//以上为与前台提交参数有关的一些公共方法///////	

	
	//////C-以下为与查询前处理有关的一些公共方法///////
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
		
	//以上为与查询前处理有关的一些公共方法///////
	
	///////////D-以下为查询类操作方法////////////////////////////
	/**
	 * 通过对象获取记录,可以传主键值,也可以传整个对象
	 * @param id 如:get(21)或get(student)
	 * @return 返回实体对象
	 */
	@Override
	public Entity get(Object id) {
		return dao.get(id);
	}

	/**
	 * 带入条件查询,返回结果集
	 * @param query
	 * @return 返回结果一定不为null,如果没有数据则返回一个空List
	 */
	@Override
	public List<Entity> find(ExpressionQuery query) {
		List<Entity> list = dao.find(query);
		if(list == null) {
			list = Collections.emptyList();
		}
		return list;
	}

	/**
	 * 带入条件查询总数
	 * @param query
	 * @return 返回的结果一定大于等于0
	 */
	@Override
	public int findTotalCount(ExpressionQuery query) {
		Integer total = dao.findTotalCount(query);
		return total == null ? 0 : total;
	}

	/**
	 * 根据条件查找单条记录
	 */
	@Override
	public Entity getByExpression(ExpressionQuery query) {
		return dao.getByExpression(query);
	}
	
	/**
	 * 根据字段查询一条记录
	 * @param column 数据库字段名
	 * @param value 字段值
	 * @return
	 */
	public Entity getByProperty(String column,Object value) {
		QBC<Entity> qbc = QBC.create(dao);
		return qbc.eq(column, value).listOne();
	}
	
	/**
	 * 根据字段查询集合
	 * @param column 数据库字段名
	 * @return
	 */
	public List<Entity> listByProperty(String column) {
		return this.listByProperty(column, null);
	}
	
	/**
	 * 根据字段查询集合
	 * @param column
	 * @param expressions
	 * @return
	 */
	public List<Entity> listByProperty(String column,List<Expression> expressions) {
		QBC<Entity> qbc = QBC.create(dao);
		qbc.addExpressions(expressions);
		return qbc.listAll();
	}

	@Override
	/**
	 * 聚合查询--一般用于统计
	 */
	public List<Map<String,Object>> findProjection(ProjectionQuery query) {
		return dao.findProjection(query);
	}

	///////////D-以上为查询类操作方法////////////////////////////
	



    ///////////E-以下为消息类相关方法/////////////////////////////////	
	//以下涉及消息类的处理代码均由工具类实现

	/**
	 * 返回成功的视图
	 * 
	 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
	 *         方法返回自定义的MessageResult
	 */
	public MessageResult success() {
		return MyToolsUtil.success();
	}

	/**
	 * 返回成功
	 * 
	 * @param message 文本消息
	 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
	 *         方法返回自定义的MessageResult
	 */
	public MessageResult success(String message) {
		return MyToolsUtil.success(message);
	}
	
	/**
	 * 返回成功
	 * 
	 * @param message 文本消息
	 * @param attach 附加对象
	 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
	 *         方法返回自定义的MessageResult
	 */
	public MessageResult success(String message,Object attach) {
		return MyToolsUtil.success(message, attach);
	}

	/**
	 * 返回错误的视图
	 * 
	 * @param errorMsg
	 *            错误信息
	 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
	 *         方法返回自定义的MessageResult
	 */
	public MessageResult error(String errorMsg) {
		return MyToolsUtil.error(errorMsg);
	}
	
	/**
	 * 返回错误的视图
	 * 
	 * @param errorMsg
	 *            错误信息
	 * @param attach 附加对象
	 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
	 *         方法返回自定义的MessageResult
	 */
	public MessageResult error(String errorMsg,Object attach) {
		return MyToolsUtil.error(errorMsg, attach);
	}

	/**
	 * 返回错误信息
	 * 
	 * @param errorMsg
	 *            错误信息
	 * @param errorMsgs
	 *            更多错误信息
	 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
	 *         方法返回自定义的MessageResult
	 */
	public MessageResult error(String errorMsg, List<String> errorMsgs) {
		return MyToolsUtil.error(errorMsg, errorMsgs);
	}
   ////////E-以上为消息类相关方法////////////////////////////////////		
}
