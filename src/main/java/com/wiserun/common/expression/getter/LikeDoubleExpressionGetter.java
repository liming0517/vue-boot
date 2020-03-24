package com.wiserun.common.expression.getter;

import java.lang.annotation.Annotation;

import org.springframework.util.StringUtils;

import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.annotation.LikeDoubleField;
import com.wiserun.common.expression.subexpression.LikeDoubleExpression;

/**
 * 构建两边模糊查询条件
 * @author tanghc
 */
public class LikeDoubleExpressionGetter implements ExpressionGetter{

		@Override
		public Expression buildExpression(Annotation annotation, String column,
				Object value) {
			if (value == null) {
				return null;
			}
			if (value instanceof String) {
				if (!StringUtils.hasText((String) value)) {
					return null;
				}
			}
			LikeDoubleField valueField = (LikeDoubleField) annotation;
			String fieldColumn = valueField.column();
			if (StringUtils.hasText(fieldColumn)) {
				column = fieldColumn;
			}
			return new LikeDoubleExpression(valueField.joint(),column, value);
		}
		
	}