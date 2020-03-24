package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.area Entity实体 忽略 系统预定义字段 （但还是要定义id status 和 remark属性，用于特殊情况下的处理）
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

/**
  sys.area
*/
public class SysArea extends BaseEntity {
                   	// 校区编号
	private String areaCode;
 	// 校区名称
	private String areaName;
 	// 校区类型
	private String areaType;
 	// 父校区id
	private String parentId;
 	// 父校区编号
	private String parentCode;
 	// 父校区名称
	private String parentName;
	/** 设置 校区编号,对应字段 sys_area.areaCode */
	public void setAreaCode(String areaCode){
		this.areaCode = areaCode;
	}
	/** 获取 校区编号,对应字段 sys_area.areaCode */
	public String getAreaCode(){
		return this.areaCode;
	}
	/** 设置 校区名称,对应字段 sys_area.areaName */
	public void setAreaName(String areaName){
		this.areaName = areaName;
	}
	/** 获取 校区名称,对应字段 sys_area.areaName */
	public String getAreaName(){
		return this.areaName;
	}
	/** 设置 校区类型,对应字段 sys_area.areaType */
	public void setAreaType(String areaType){
		this.areaType = areaType;
	}
	/** 获取 校区类型,对应字段 sys_area.areaType */
	public String getAreaType(){
		return this.areaType;
	}
	/** 设置 父校区id,对应字段 sys_area.parentId */
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	/** 获取 父校区id,对应字段 sys_area.parentId */
	public String getParentId(){
		return this.parentId;
	}
	/** 设置 父校区编号,对应字段 sys_area.parentCode */
	public void setParentCode(String parentCode){
		this.parentCode = parentCode;
	}
	/** 获取 父校区编号,对应字段 sys_area.parentCode */
	public String getParentCode(){
		return this.parentCode;
	}
	/** 设置 父校区名称,对应字段 sys_area.parentName */
	public void setParentName(String parentName){
		this.parentName = parentName;
	}
	/** 获取 父校区名称,对应字段 sys_area.parentName */
	public String getParentName(){
		return this.parentName;
	}
}
