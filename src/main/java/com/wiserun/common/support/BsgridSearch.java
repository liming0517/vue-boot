package com.wiserun.common.support;

import com.wiserun.common.SearchEntity;

public class BsgridSearch extends SearchEntity {

	public void setCurPage(int curPage) {
		this.setPageIndex(curPage);
	}
	
	public void setSortName(String sortName) {
		this.setSortname(sortName);
	}
	
	public void setSortOrder(String sortOrder) {
		this.setSortorder(sortOrder);
	}
}
