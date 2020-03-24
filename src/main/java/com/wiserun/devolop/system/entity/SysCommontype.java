package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.commontype Entity实体 忽略 系统预定义字段 （但还是要定义id status 和 remark属性，用于特殊情况下的处理）
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

/**
  sys.commontype
*/
public class SysCommontype extends BaseEntity {
                   	private String typecode;
 	private String typename;
 	private String parentId;
 	private String parentCode;
 	private String parentName;
 	private String typevalue;
 	private String typedesc;
 	private Integer level;
	public void setTypecode(String typecode){
		this.typecode = typecode;
	}
	public String getTypecode(){
		return this.typecode;
	}
	public void setTypename(String typename){
		this.typename = typename;
	}
	public String getTypename(){
		return this.typename;
	}
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	public String getParentId(){
		return this.parentId;
	}
	public void setParentCode(String parentCode){
		this.parentCode = parentCode;
	}
	public String getParentCode(){
		return this.parentCode;
	}
	public void setParentName(String parentName){
		this.parentName = parentName;
	}
	public String getParentName(){
		return this.parentName;
	}
	public void setTypevalue(String typevalue){
		this.typevalue = typevalue;
	}
	public String getTypevalue(){
		return this.typevalue;
	}
	public void setTypedesc(String typedesc){
		this.typedesc = typedesc;
	}
	public String getTypedesc(){
		return this.typedesc;
	}
	public void setLevel(Integer level){
		this.level = level;
	}
	public Integer getLevel(){
		return this.level;
	}
}
