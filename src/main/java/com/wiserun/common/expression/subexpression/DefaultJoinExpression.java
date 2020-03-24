package com.wiserun.common.expression.subexpression;

import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.JoinExpression;
import com.wiserun.common.expression.projection.ProjectionQuery;

public class DefaultJoinExpression implements JoinExpression,Expression {

	private String joinSql;
	
	/**
	 * 自定义连接语句
	 * @param joinSql inner join table1 t1 on t.xx = t1.xx
	 */
	public DefaultJoinExpression(String joinSql){
		this.joinSql = joinSql;
	}
	
	@Override
	public String getExprString() {
		return joinSql;
	}

	@Override
	public void addToQuery(ExpressionQuery query) {
		query.addJoinExpression(this);
	}
	
	@Override
	public void addToHaving(ProjectionQuery query) {
		
	}

}
