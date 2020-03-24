package com.wiserun.common.expression.subexpression;

import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.SqlContent;
import com.wiserun.common.expression.projection.ProjectionQuery;

/**
 * 拼接SQL语句
 * 
 * @author tanghc
 * 
 */
public class SqlExpression implements Expression {

	private String joint = SqlContent.AND;
	private String sql;

	public SqlExpression(String sql) {
		this.sql = sql;
	}
	
	public SqlExpression(String joint,String sql) {
		this.joint = joint;
		this.sql = sql;
	}

	@Override
	public void addToQuery(ExpressionQuery query) {
		query.addSqlExpression(this);
	}
	
	@Override
	public void addToHaving(ProjectionQuery query) {
		query.addHavingSqlExpression(this);
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getJoint() {
		return joint;
	}

	public void setJoint(String joint) {
		this.joint = joint;
	}
	
}
