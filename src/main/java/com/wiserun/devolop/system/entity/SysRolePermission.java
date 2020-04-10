package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.rolepermission Entity实体 忽略 系统预定义字段 （但还是要定义id status 和 remark属性，用于特殊情况下的处理）
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

/**
  sys.rolepermission
*/
public class SysRolePermission extends BaseEntity {
                   	private String roleId;
 	private String sfId;
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getRoleId(){
		return this.roleId;
	}
	public void setSfId(String sfId){
		this.sfId = sfId;
	}
	public String getSfId(){
		return this.sfId;
	}
}
