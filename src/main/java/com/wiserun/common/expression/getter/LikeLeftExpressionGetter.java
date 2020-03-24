package com.wiserun.common.expression.getter;

import java.lang.annotation.Annotation;

import org.springframework.util.StringUtils;

import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.annotation.LikeLeftField;
import com.wiserun.common.expression.subexpression.LikeLeftExpression;

/**
 * 构建左模糊查询条件
 * @author tanghc
 */
public class LikeLeftExpressionGetter implements ExpressionGetter{

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
			LikeLeftField valueField = (LikeLeftField) annotation;
			String fieldColumn = valueField.column();
			if (StringUtils.hasText(fieldColumn)) {
				column = fieldColumn;
			}
			return new LikeLeftExpression(valueField.joint(),column, value);
		}
		
	}