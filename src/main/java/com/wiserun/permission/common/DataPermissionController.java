package com.wiserun.permission.common;

import java.util.List;

import com.wiserun.common.SearchSupport;
import com.wiserun.common.controller.CrudController;
import com.wiserun.common.dao.BaseDao;
import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.service.CrudService;

/**
 * 用到数据权限检查的功能,可以继承这个类
 * @author hc.tang
 *
 * @param <Entity>
 * @param <Service>
 */
public class DataPermissionController<Entity, Service extends CrudService<Entity, ? extends BaseDao<Entity>>> extends CrudController<Entity, Service> {

	
	/*@Override
	protected ExpressionQuery buildExpressionQuery(SearchSupport searchEntity) {
		ExpressionQuery query = super.buildExpressionQuery(searchEntity);
		List<Expression> userDataExpressions = RMSContext.getInstance().getUserDataExpressions();
		query.addAll(userDataExpressions);
		return query;
	}
	
	public ExpressionQuery buildExpressionQuery() {
		ExpressionQuery query = new ExpressionQuery();
		List<Expression> userDataExpressions = RMSContext.getInstance().getUserDataExpressions();
		query.addAll(userDataExpressions);
		return query;
	}*/
	
}
