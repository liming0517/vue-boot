package com.wiserun.common;

public interface SearchSupport {
	/** 返回第一条记录的索引值 */
	int getStart();
	/** 返回每页大小 */
	int getLimit();
	/** 返回排序字段 */
	String getSortname();
	/** 返回排序方式 */
	String getSortorder();
	/** 返回数据库的排序字段 */
	String getDBSortname();
}
