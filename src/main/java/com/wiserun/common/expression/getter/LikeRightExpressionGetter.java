package com.wiserun.common.expression.getter;

import java.lang.annotation.Annotation;

import org.springframework.util.StringUtils;

import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.annotation.LikeRightField;
import com.wiserun.common.expression.subexpression.LikeRightExpression;

/**
 * 构建右模糊查询条件
 * @author tanghc
 *
 */
public class LikeRightExpressionGetter implements ExpressionGetter{

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
			LikeRightField valueField = (LikeRightField) annotation;
			String fieldColumn = valueField.column();
			if (StringUtils.hasText(fieldColumn)) {
				column = fieldColumn;
			}
			return new LikeRightExpression(valueField.joint(),column, value);
		}
		
	}