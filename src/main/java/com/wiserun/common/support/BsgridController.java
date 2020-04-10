package com.wiserun.common.support;

import com.wiserun.common.Edit;
import com.wiserun.common.GridResult;
import com.wiserun.common.controller.CrudController;

public class BsgridController<Entity, Service extends Edit<Entity>> extends CrudController<Entity, Service> {

	@Override
	protected GridResult getGridResult() {
		return new BsgridResult();
	}
	
}
