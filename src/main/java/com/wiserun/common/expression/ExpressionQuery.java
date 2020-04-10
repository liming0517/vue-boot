package com.wiserun.common.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import com.wiserun.common.SearchSupport;
import com.wiserun.common.expression.subexpression.ListExpression;
import com.wiserun.common.expression.subexpression.SqlExpression;
import com.wiserun.common.expression.subexpression.ValueExpression;
import com.wiserun.common.util.ClassUtil;

/**
 * 查询条件类
 * 
 * rebuild 2018-12-10
 */
public class ExpressionQuery {
	
	private static final String REG_SQL_INJECT = "([';\\*--\\|])+";
	private int start;
	private int limit = 10;
	// 排序信息
	private Set<String> orderInfo = new LinkedHashSet<String>();
	// 是否查询全部
	private boolean queryAll;
	
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	private List<ValueExpression> valueExprList = new ArrayList<ValueExpression>();
	private List<JoinExpression> joinExprList = new ArrayList<JoinExpression>();
	private List<ListExpression> listExprList = new ArrayList<ListExpression>();
	private List<SqlExpression> sqlExpreList = new ArrayList<SqlExpression>();
	
	private List<String> columns = Collections.emptyList();
	
	public static ExpressionQuery buildQueryAll(){
		ExpressionQuery query = new ExpressionQuery();
		query.queryAll = true;
		return query;
	}
	
	public void addAll(List<Expression> expressions){
		if(expressions != null){
			for (Expression expression : expressions) {
				this.add(expression);
			}
		}
	}
	
	/**
	 * 添加注解查询条件
	 * @param searchEntity
	 * @return
	 */
	public ExpressionQuery addAnnotionExpression(SearchSupport searchEntity) {
		List<Expression> expresList = ExpressionBuilder.buildExpressions(searchEntity);
		
		for (Expression express : expresList) {
			add(express);
		}
		
		return this;
	}
	
	/**
	 * 添加分页信息
	 */
	public ExpressionQuery addPaginationInfo(SearchSupport searchEntity){
		this.start = searchEntity.getStart();
		this.limit = searchEntity.getLimit();
		this.addSort(searchEntity.getDBSortname(), searchEntity.getSortorder());
		return this;
	}
	
	public ExpressionQuery addSqlExpression(SqlExpression expression) {
		sqlExpreList.add(expression);
		return this;
	}
	
	public ExpressionQuery addValueExpression(ValueExpression expression) {
		valueExprList.add(expression);
		return this;
	}

	public ExpressionQuery addJoinExpression(JoinExpression expression) {
		joinExprList.add(expression);
		return this;
	}

	public ExpressionQuery addListExpression(ListExpression expression) {
		listExprList.add(expression);
		return this;
	}

	public ExpressionQuery addParam(String name,Object value) {
		paramMap.put(name, value);
		return this;
	}


	public ExpressionQuery add(Expression expre) {
		expre.addToQuery(this);
		return this;
	}
	
	// ------ 设置分页信息 ------
	
	/**
	 * 设置分页信息
	 * @param start 记录索引
	 * @return
	 */
	public ExpressionQuery setStart(int start) {
		this.start = start;
		return this;
	}
	
	/**
	 * 设置分页信息
	 * @param limit 记录条数
	 * @return
	 */
	public ExpressionQuery setLimit(int limit) {
		this.limit = limit;
		return this;
	}
	
	/**
	 * 设置分页信息,第几页
	 * @param pageIndex
	 * @return
	 */
	public ExpressionQuery setPageIndex(int pageIndex) {
		if(pageIndex < 1) {
			pageIndex = 1;
		}
		this.setStart((int) ((pageIndex - 1) * this.getPageSize()));
		return this;
	}
	
	/**
	 * 设置每页条数
	 * @param pageSize
	 * @return
	 */
	public ExpressionQuery setPageSize(int pageSize) {
		this.setLimit(pageSize);
		return this;
	}
	
	public ExpressionQuery setFirstResult(int firstResult) {
		this.setStart(firstResult);
		return this;
	}
	
	public int getStart() {
		return start;
	}
	
	/**
	 * 
	 * 同getStart()
	 * @return
	 */
	public int getFirstResult() {
		return this.getStart();
	}
	
	public int getLimit() {
		return limit;
	}
	
	/**
	 * 同getLimit()
	 * @return
	 */
	public int getPageSize() {
		return this.getLimit();
	}
	
	// ------ 设置分页信息 end ------
	
	/**
	 * 是否查询全部
	 * @return
	 */
	public boolean getIsQueryAll() {
		return this.queryAll;
	}
	
	public ExpressionQuery setQueryAll(boolean queryAll) {
		this.queryAll = queryAll;
		return this;
	}
	
	/**
	 * 添加ASC排序字段,
	 * @param sortname 数据库字段名
	 * @return
	 */
	public ExpressionQuery addSort(String sortname){
		return this.addSort(sortname, null);
	}
	
	/**
	 * 添加排序字段
	 * @param sortname 数据库字段名
	 * @param sortorder 排序方式,ASC,DESC
	 * @return
	 */
	public ExpressionQuery addSort(String sortname,String sortorder) {
		
		if(StringUtils.isNotBlank(sortname)){
			// 简单防止SQL注入
			sortname = sortname.replaceAll(REG_SQL_INJECT,SqlContent.EMPTY);
			
			if(!SqlContent.ASC.equalsIgnoreCase(sortorder) 
					&& !SqlContent.DESC.equalsIgnoreCase(sortorder)){
				sortorder = SqlContent.ASC;
			}
			
			orderInfo.add(sortname + SqlContent.BLANK + sortorder);
		}
		
		return this;
	}
	
	/**
	 * 是否具备排序,是返回true
	 * @return
	 */
	public boolean getOrderable() {
		return orderInfo.size() > 0;
	}
	
	/**
	 * 返回排序信息
	 * @return 返回排序信息,如:id ASC,name ASC,date desc. 没有排序则返回""
	 */
	public String getOrder() {
		if(this.getOrderable()){
			return StringUtils.join(orderInfo, SqlContent.COMMA);
		}
		return null;
	}
	
	/**
	 * 兼容老版本,不推荐.改用getOrder()
	 * @return
	 */
	@Deprecated
	public String getSortname() {
		if(this.getOrderable()){
			return this.getOrder();
		}
		return null;
	}

	public List<ValueExpression> getValueExprList() {
		return valueExprList;
	}

	public List<JoinExpression> getJoinExprList() {
		return joinExprList;
	}

	public List<ListExpression> getListExprList() {
		return listExprList;
	}
	
	public List<SqlExpression> getSqlExpreList() {
		return sqlExpreList;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	/**
	 * 同getParamMap(),在mybatis中方便使用#{param.xxx}形式获取
	 * 在 where 条件以外使用的时候需要采用 $ {param.xxx}形式
	 * @return
	 */
	public Map<String, Object> getParam() {
		return this.getParamMap();
	}

	/**
	 * 更新检索条件 （如果没有相同的表达式则新增一个表达式）
	 * 根据表达式的 名称（字段名） 和 操作符（如 = > <）及 关系符（AND OR）进行匹配，相同则更新value （如果value 为 null 则 删除该表达式）
	 * 注：此操作将对所有符合条件的表达式做处理 
	 * hades 20181204
	 * @param expression
	 */
	public void updateValueExpression(ValueExpression expression){
		if(null ==expression) return ; //不处理null
		
		boolean isUpdate=false;
		List<ValueExpression> list= this.getValueExprList();
        if (list.size()>0){
        	for(int s=list.size()-1;s>=0;s--){
        		ValueExpression obj=list.get(s);
        		//判断 表达式是否相同（根据 名称（字段名） 和 操作符（如 = > <）及 关系符（AND OR））
        		if ( ("" +expression.getColumn()).equalsIgnoreCase(obj.getColumn())
        			&&  ("" +expression.getEqual()).equalsIgnoreCase(obj.getEqual())
        			&&  ("" +expression.getJoint()).equalsIgnoreCase(obj.getJoint()) ){
            		//System.out.println("查询条件"+expression.getColumn()+"已找到");
            		if (null==expression.getValue()){
            			list.remove(obj); //如果value 为null 则删除
            		}else{
            			obj.setValue(expression.getValue());
            		}
            		isUpdate=true;
            		//break;
            	}
            	else {
            		//System.out.println("查询条件"+ClassUtil.convertObj2Map(obj) );
            	}
        	}
        }
        //如果都没有找到，则新增
        if (!isUpdate){
        	//System.out.println("查询条件"+expression.getColumn()+"没找到，新增一个表达式");
        	this.addValueExpression(expression);
        }
	}
}
