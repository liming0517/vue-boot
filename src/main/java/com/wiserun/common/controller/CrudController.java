package com.wiserun.common.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.wiserun.common.DurcException;
import com.wiserun.common.Edit;
import com.wiserun.common.MessageResult;
import com.wiserun.common.ValidateHolder;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;
import com.wiserun.common.util.MyBeanUtil;
import com.wiserun.common.util.ValidateUtil;

import com.wiserun.common.BaseEntity;

/**
 * 增删改查的Controller
 * 
 * rebuild 20181210
 * 
 * @param <Entity>
 *            实体类
 * @param <Service>
 *            增删改查的Service
 */
public abstract class CrudController<Entity, Service extends Edit<Entity>>
		extends SearchController<Entity, Service> {

	//方法排序顺序-依照：A-通用类操作、B-数据库操作（增删改）方法
	
	////////以下是A-通用类操作方法/////////////////
	
	
	////////以上是A-通用类操作方法/////////////////
	
	////////以下是B-数据库操作（增删改）方法/////////////////

	/**
	 * 新增记录
	 * 
	 * @param entity
	 * @return
	 */
	@Deprecated
	public MessageResult save(Entity entity) {
		 //考虑到兼容性
		ValidateHolder validateHolder = ValidateUtil.validate(entity);
		
		if (validateHolder.isSuccess()) {
			this.getService().saveNotNull(entity); //20181206 考虑到兼容性，暂时使用 saveNotNull,最终需要改为 insertNotNull
			return success();
		}
		
		return error("添加失败", validateHolder.buildValidateErrors());
	}

	/**
	 * 修改记录
	 * 
	 * @param entity
	 * @return
	 */
	@Deprecated
	public MessageResult update(Entity entity) {
		Entity e = this.get(entity);
		if (e == null) {
			throw new DurcException("修改失败-该记录不存在");
		}

		ValidateHolder validateHolder = ValidateUtil.validate(entity);
		
		if (validateHolder.isSuccess()) {
			MyBeanUtil.copyProperties(entity, e);
			getService().updateNotNull(e); //20181027 不处理 null值
			return success();
		}
		
		return error("修改失败", validateHolder.buildValidateErrors());
	}

	/**
	 * 删除记录
	 * 
	 * @param entity
	 * @return
	 */
	@Deprecated
	public MessageResult delete(Entity entity) {
		//考虑到兼容性
		getService().del(entity);
		return success(); 
	}	
	
	////////以上是B-数据库操作（增删改）方法/////////////////
	
	
}
