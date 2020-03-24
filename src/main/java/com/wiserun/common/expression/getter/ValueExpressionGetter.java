package com.wiserun.common.expression.getter;

import java.lang.annotation.Annotation;

import org.springframework.util.StringUtils;

import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.annotation.ValueField;
import com.wiserun.common.expression.subexpression.ValueExpression;

/**
 * 构建单值查询条件工厂
 */
public class ValueExpressionGetter implements ExpressionGetter {

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
			ValueField valueField = (ValueField) annotation;
			String fieldColumn = valueField.column();
			if (StringUtils.hasText(fieldColumn)) {
				column = fieldColumn;
			}
			return new ValueExpression(valueField.joint(), column,
					valueField.equal(), value);
		}

	}