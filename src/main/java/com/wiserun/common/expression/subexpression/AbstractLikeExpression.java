package com.wiserun.common.expression.subexpression;

import com.wiserun.common.expression.SqlContent;

public abstract class AbstractLikeExpression extends ValueExpression {

	public AbstractLikeExpression(String column, Object value) {
		super(column, value);
	}

	public AbstractLikeExpression(String joint, String column, Object value) {
		super(joint, column, SqlContent.LIKE, value);
	}

	@Override
	public String getEqual() {
		return SqlContent.LIKE;
	}

}
