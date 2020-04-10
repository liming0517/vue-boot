package com.wiserun.common;

import org.apache.ibatis.annotations.Param;

import com.wiserun.common.expression.ExpressionQuery;


/**
 * 数据库基础操作接口类
 * 
 * rebuild 2018-12-10
 */
public interface Edit<Entity> extends Sch<Entity> {
	/**
	 * 新增,新增所有字段--不推荐 ，改用 insert
	 * 
	 * @param entity
	 * @return 受到影响的行数
	 */
	@Deprecated
	int save(Entity entity);
	/**
	 * 新增,新增所有字段
	 * 
	 * @param entity
	 * @return 受到影响的行数
	 */

	int insert(Entity entity); //新增-考虑到统一命名规范，等同save
	/**
	 * 新增（忽略空数据）--不推荐 ，改用 insertNotNull
	 * @param entity
	 * @return 受到影响的行数
	 */
	@Deprecated
	int saveNotNull(Entity entity);
	/**
	 * 新增（忽略空数据）
	 * @param entity
	 * @return 受到影响的行数
	 */

	int insertNotNull(Entity entity); //新增-考虑到统一命名规范，等同saveNotNull
	/**
	 * 修改,修改所有字段
	 * 
	 * @param entity
	 * @return 受到影响的行数
	 */
	int update(Entity entity);
	
	/**
	 * 根据条件更新所有字段
	 * @param entity
	 * @param query
	 * @return 受到影响的行数
	 */
	int updateByExpression(@Param("entity")Entity entity,@Param("query")ExpressionQuery query);
	
	/**
	 * 根据主键更新不为null的字段
	 * @param entity
	 * @return 受到影响的行数
	 */
	int updateNotNull(Entity entity);
	
	/**
	 * 根据条件更新不为null的字段
	 * @param entity
	 * @param query
	 * @return 受到影响的行数
	 */
	int updateNotNullByExpression(@Param("entity")Entity entity,@Param("query")ExpressionQuery query);

	/**
	 * 删除
	 * 
	 * @param entity
	 * @return 受到影响的行数
	 */
	int del(Entity entity);
	
	/**
	 * 根据条件删除
	 * @param entity
	 * @param query
	 * @return 受到影响的行数
	 */
	int delByExpression(ExpressionQuery query);
}
