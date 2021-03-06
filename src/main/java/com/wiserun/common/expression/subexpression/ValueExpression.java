package com.wiserun.common.expression.subexpression;

import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.SqlContent;
import com.wiserun.common.expression.projection.ProjectionQuery;


/**
 * 值查询
 * 
 * @author tanghc 2011-10-28
 */
public class ValueExpression implements Expression {

	private String column = "";
	private String equal = SqlContent.EQUAL;
	private Object value;
	private String joint = SqlContent.AND;

	public ValueExpression(String column, Object value) {
		this.column = column;
		this.value = value;
	}

	public ValueExpression(String column, String equal, Object value) {
		this(column, value);
		this.equal = equal;
	}

	public ValueExpression(String joint, String column, String equal,
			Object value) {
		this(column, equal, value);
		this.joint = joint;
	}

	@Override
	public void addToQuery(ExpressionQuery query) {
		query.addValueExpression(this);
	}
	
	@Override
	public void addToHaving(ProjectionQuery query) {
		query.addHavingValueExpression(this);
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getEqual() {
		return equal;
	}

	public void setEqual(String equal) {
		this.equal = equal;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getJoint() {
		return joint;
	}

	public void setJoint(String joint) {
		this.joint = joint;
	}

}
