package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.department Entity实体 忽略 系统预定义字段 （但还是要定义id status 和 remark属性，用于特殊情况下的处理）
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

/**
  sys.department
*/
public class SysDepartment extends BaseEntity {
                   	private String deptCode;
 	private String deptName;
 	private String parentId;
 	private String parentCode;
 	private String parentName;
 	private String areaId;
 	private String areaCode;
 	private String areaName;
 	private String powerPerson;
	public void setDeptCode(String deptCode){
		this.deptCode = deptCode;
	}
	public String getDeptCode(){
		return this.deptCode;
	}
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}
	public String getDeptName(){
		return this.deptName;
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
	public void setAreaId(String areaId){
		this.areaId = areaId;
	}
	public String getAreaId(){
		return this.areaId;
	}
	public void setAreaCode(String areaCode){
		this.areaCode = areaCode;
	}
	public String getAreaCode(){
		return this.areaCode;
	}
	public void setAreaName(String areaName){
		this.areaName = areaName;
	}
	public String getAreaName(){
		return this.areaName;
	}
	public void setPowerPerson(String powerPerson){
		this.powerPerson = powerPerson;
	}
	public String getPowerPerson(){
		return this.powerPerson;
	}
}
