package com.wiserun.common.expression.getter;

import java.lang.annotation.Annotation;

import com.wiserun.common.expression.Expression;

/**
 * 负责申明各种条件
 * @author tanghc
 * 2011-10-28
 */
public interface ExpressionGetter {

	Expression buildExpression(Annotation annotation, String column,
			Object value);

}
