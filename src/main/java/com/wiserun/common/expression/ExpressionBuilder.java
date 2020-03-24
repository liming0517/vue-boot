package com.wiserun.common.expression;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.wiserun.common.expression.annotation.LikeDoubleField;
import com.wiserun.common.expression.annotation.LikeLeftField;
import com.wiserun.common.expression.annotation.LikeRightField;
import com.wiserun.common.expression.annotation.ListField;
import com.wiserun.common.expression.annotation.ValueField;
import com.wiserun.common.expression.getter.ExpressionGetter;
import com.wiserun.common.expression.getter.LikeDoubleExpressionGetter;
import com.wiserun.common.expression.getter.LikeLeftExpressionGetter;
import com.wiserun.common.expression.getter.LikeRightExpressionGetter;
import com.wiserun.common.expression.getter.ListExpressionGetter;
import com.wiserun.common.expression.getter.ValueExpressionGetter;


/**
 * 从bean中获取Expression
 * 
 * @author tanghc 2011-10-25
 */
public class ExpressionBuilder {

	private static final String PREFIX_GET = "get";

	// init
	static {
		AnnoExprStore.addExpressionGetter(ListField.class,new ListExpressionGetter());
		AnnoExprStore.addExpressionGetter(ValueField.class,new ValueExpressionGetter());
		AnnoExprStore.addExpressionGetter(LikeLeftField.class,new LikeLeftExpressionGetter());
		AnnoExprStore.addExpressionGetter(LikeRightField.class,new LikeRightExpressionGetter());
		AnnoExprStore.addExpressionGetter(LikeDoubleField.class,new LikeDoubleExpressionGetter());
	}

	/**
	 * 获取条件表达式
	 * @param obj
	 * @return
	 */
	public static List<Expression> buildExpressions(Object obj) {
		if (obj == null) {
			return null;
		}
		List<Expression> expList = new ArrayList<Expression>();
		Method[] methods = obj.getClass().getDeclaredMethods();
		try {
			for (Method method : methods) {
				String methodName = method.getName();
				Annotation[] annotations = method.getAnnotations();

				if (couldBuildExpression(methodName, annotations)) {
					Object value = method.invoke(obj, new Object[] {});

					String column = buildColumn(methodName);

					expList.addAll(buildExpression(annotations, column, value));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expList;
	}

	private static List<Expression> buildExpression(Annotation[] annotations,
			String column, Object value) {
		List<Expression> expList = new ArrayList<Expression>();

		for (Annotation annotation : annotations) {

			ExpressionGetter expressionGetter = AnnoExprStore.get(annotation);
			
			Expression expression = expressionGetter.buildExpression(
					annotation, column, value);

			if (expression != null) {
				expList.add(expression);
			}
		}

		return expList;
	}

	// 构建列名
	private static String buildColumn(String methodName) {
		return methodName.substring(3, 4).toLowerCase()
				+ methodName.substring(4);

	}

	// 能否构建表达式
	private static boolean couldBuildExpression(String methodName,
			Annotation[] annotations) {
		return methodName.startsWith(PREFIX_GET)
				&& hasExpressionAnnotation(annotations);
	}

	// 是否有注解
	private static boolean hasExpressionAnnotation(Annotation[] annotations) {
		if (annotations == null || annotations.length == 0) {
			return false;
		}
		for (Annotation annotation : annotations) {
			if (AnnoExprStore.get(annotation) != null) {
				return true;
			}
		}
		return false;
	}

}
