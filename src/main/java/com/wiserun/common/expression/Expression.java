package com.wiserun.common.expression;

import com.wiserun.common.expression.projection.ProjectionQuery;

/**
 * 查询条件接口
 * 
 * @author tanghc 2011-10-28
 */
public interface Expression {
	void addToQuery(ExpressionQuery query);
	void addToHaving(ProjectionQuery query);
}
