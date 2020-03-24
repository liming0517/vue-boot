package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;
import com.wiserun.permission.common.TreeAware;


/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.res Entity实体 忽略 系统预定义字段 （但还是要定义id status 和 remark属性，用于特殊情况下的处理）
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

/**
  sys.res
*/
public class SysRes extends BaseEntity implements TreeAware<SysRes> {
                   	private String parentId;
 	private String resName;
 	private String tabId;
 	private String url;
 	private String resType;
 	private String tabName;
 	private List<SysRes> children;
	private List<SysFunction> sysFuns;
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	@Override
	public String getParentId(){
		return this.parentId;
	}
	public void setResName(String resName){
		this.resName = resName;
	}
	public String getResName(){
		return this.resName;
	}
	public void setTabId(String tabId){
		this.tabId = tabId;
	}
	public String getTabId(){
		return this.tabId;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return this.url;
	}
	public void setResType(String resType){
		this.resType = resType;
	}
	public String getResType(){
		return this.resType;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	
	public List<SysFunction> getSysFuns() {
		return sysFuns;
	}
	public void setSysFuns(List<SysFunction> sysFuns) {
		this.sysFuns = sysFuns;
	}
	@JSONField(name = "_parentId")
	public String  getEasyUIParentId() {
		return parentId;
	}

	@Override
	public List<SysRes> getChildren() {
		return children;
	}

	public void setChildren(List<SysRes> children) {
		this.children = children;
	}

	@Override
	public String  getId() {
		return this.id;
	}

	@Override
	public String getText() {
		return resName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return this.id == ((SysRes) obj).id;
	}

	
	
}
