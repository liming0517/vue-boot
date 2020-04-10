package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.function Entity实体 忽略 系统预定义字段 （但还是要定义id status 和 remark属性，用于特殊情况下的处理）
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

/**
  sys.function
*/
public class SysFunction extends BaseEntity {
                   	private String srId;
 	private String operateCode;
 	private String operateName;
 	private String url;
	public void setSrId(String srId){
		this.srId = srId;
	}
	public String getSrId(){
		return this.srId;
	}
	public void setOperateCode(String operateCode){
		this.operateCode = operateCode;
	}
	public String getOperateCode(){
		return this.operateCode;
	}
	public void setOperateName(String operateName){
		this.operateName = operateName;
	}
	public String getOperateName(){
		return this.operateName;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return this.url;
	}
}
