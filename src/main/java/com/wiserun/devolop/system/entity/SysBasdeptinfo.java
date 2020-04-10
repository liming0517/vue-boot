package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.basdeptinfo Entity实体 忽略 系统预定义字段 （但还是要定义id status 和 remark属性，用于特殊情况下的处理）
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

/**
  sys.basdeptinfo
*/
public class SysBasdeptinfo extends BaseEntity {
                   	// 职能部门编号
	private String basdeptCode;
 	// 职能部门名称
	private String basdeptName;
 	// 职能部门类型
	private String basdeptType;
	/** 设置 职能部门编号,对应字段 sys_basdeptinfo.basdeptCode */
	public void setBasdeptCode(String basdeptCode){
		this.basdeptCode = basdeptCode;
	}
	/** 获取 职能部门编号,对应字段 sys_basdeptinfo.basdeptCode */
	public String getBasdeptCode(){
		return this.basdeptCode;
	}
	/** 设置 职能部门名称,对应字段 sys_basdeptinfo.basdeptName */
	public void setBasdeptName(String basdeptName){
		this.basdeptName = basdeptName;
	}
	/** 获取 职能部门名称,对应字段 sys_basdeptinfo.basdeptName */
	public String getBasdeptName(){
		return this.basdeptName;
	}
	/** 设置 职能部门类型,对应字段 sys_basdeptinfo.basdeptType */
	public void setBasdeptType(String basdeptType){
		this.basdeptType = basdeptType;
	}
	/** 获取 职能部门类型,对应字段 sys_basdeptinfo.basdeptType */
	public String getBasdeptType(){
		return this.basdeptType;
	}
}
