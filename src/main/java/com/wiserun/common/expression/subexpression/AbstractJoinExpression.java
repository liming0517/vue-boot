package com.wiserun.common.expression.subexpression;

import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.JoinExpression;
import com.wiserun.common.expression.SqlContent;
import com.wiserun.common.expression.projection.ProjectionQuery;

/**
 * 连接查询
 * @author tanghc
 * 2014年6月12日
 *
 */
public abstract class AbstractJoinExpression implements Expression,JoinExpression {

	private String secondTableName;
	private String secondTableTableAlias = "";

	private String firstTableAlias = "t";

	private String firstTableColumn;
	private String secondTableColumn;
	
	/**
	 * 连接类型
	 * @return
	 */
	protected abstract String getJoinType();

	/**
	 * 
	 * @param secondTableName 第二张表名
	 * @param secondTableTableAlias 第二张表别名
	 * @param firstTablePK 第一张表主键
	 * @param secondTablePK 第二张表主键 
	 */
	public AbstractJoinExpression(String secondTableName,
			String secondTableTableAlias, String firstTableColumn,
			String secondTableColumn) {
		this.secondTableName = secondTableName;
		this.secondTableTableAlias = secondTableTableAlias;
		this.firstTableColumn = firstTableColumn;
		this.secondTableColumn = secondTableColumn;
	}
	
	@Override
	public void addToQuery(ExpressionQuery query) {
		query.addJoinExpression(this);
	}
	
	

	@Override
	public void addToHaving(ProjectionQuery query) {
	}

	@Override
	public String getExprString() {
		StringBuilder sb = new StringBuilder();
		sb.append(SqlContent.BLANK).append(getJoinType())
				.append(SqlContent.BLANK).append(secondTableName)
				.append(SqlContent.BLANK).append(secondTableTableAlias)
				.append(SqlContent.BLANK).append(SqlContent.ON)
				.append(SqlContent.BLANK).append(firstTableAlias).append(SqlContent.DOT)
				.append(firstTableColumn).append(SqlContent.EQUAL)
				.append(secondTableTableAlias).append(SqlContent.DOT)
				.append(secondTableColumn);
		
		return sb.toString();
	}

	public String getSecondTableName() {
		return secondTableName;
	}

	public void setSecondTableName(String secondTableName) {
		this.secondTableName = secondTableName;
	}

	public String getSecondTableTableAlias() {
		return secondTableTableAlias;
	}

	public void setSecondTableTableAlias(String secondTableTableAlias) {
		this.secondTableTableAlias = secondTableTableAlias;
	}

	public String getFirstTableAlias() {
		return firstTableAlias;
	}

	public void setFirstTableAlias(String firstTableAlias) {
		this.firstTableAlias = firstTableAlias;
	}

	public String getFirstTableColumn() {
		return firstTableColumn;
	}

	public void setFirstTableColumn(String firstTableColumn) {
		this.firstTableColumn = firstTableColumn;
	}

	public String getSecondTableColumn() {
		return secondTableColumn;
	}

	public void setSecondTableColumn(String secondTableColumn) {
		this.secondTableColumn = secondTableColumn;
	}

}
