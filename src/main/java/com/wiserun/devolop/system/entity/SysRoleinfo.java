package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.roleinfo Entity实体 忽略 系统预定义字段 （但还是要定义id status 和 remark属性，用于特殊情况下的处理）
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

/**
  sys.roleinfo
*/
public class SysRoleinfo extends BaseEntity {
                   	// 角色编号
	private String roleCode;
 	// 角色名称
	private String roleName;
 	// 角色类型
	private String roleType;
	/** 设置 角色编号,对应字段 sys_roleinfo.roleCode */
	public void setRoleCode(String roleCode){
		this.roleCode = roleCode;
	}
	/** 获取 角色编号,对应字段 sys_roleinfo.roleCode */
	public String getRoleCode(){
		return this.roleCode;
	}
	/** 设置 角色名称,对应字段 sys_roleinfo.roleName */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	/** 获取 角色名称,对应字段 sys_roleinfo.roleName */
	public String getRoleName(){
		return this.roleName;
	}
	/** 设置 角色类型,对应字段 sys_roleinfo.roleType */
	public void setRoleType(String roleType){
		this.roleType = roleType;
	}
	/** 获取 角色类型,对应字段 sys_roleinfo.roleType */
	public String getRoleType(){
		return this.roleType;
	}
}
