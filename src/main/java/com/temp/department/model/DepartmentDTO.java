package com.temp.department.model;

import com.temp.common.model.BaseDTO;
import com.temp.common.model.BaseListDTO;

public class DepartmentDTO extends BaseDTO{
	private String DeptCode;
	private String CName;
	private String EName;
	private String LEVEL;
	private String state;
	private String UserCode;
	private String DeptDesc;
	private String parId;
	private Integer order;
	private String type;
	private String resource;
	
	private String[] resource_list;
	public String getDeptCode() {
		return DeptCode;
	}
	public void setDeptCode(String deptCode) {
		DeptCode = deptCode;
	}
	public String getCName() {
		return CName;
	}
	public void setCName(String cName) {
		CName = cName;
	}
	public String getEName() {
		return EName;
	}
	public void setEName(String eName) {
		EName = eName;
	}
	public String getLEVEL() {
		return LEVEL;
	}
	public void setLEVEL(String lEVEL) {
		LEVEL = lEVEL;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	public String getDeptDesc() {
		return DeptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		DeptDesc = deptDesc;
	}
	public String getParId() {
		return parId;
	}
	public void setParId(String parId) {
		this.parId = parId;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Department [DeptCode=" + DeptCode + ", CName=" + CName + ", EName=" + EName + ", LEVEL=" + LEVEL
				+ ", state=" + state + ", UserCode=" + UserCode + ", DeptDesc=" + DeptDesc + ", parId=" + parId
				+ ", order=" + order + ", type=" + type + "]";
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String[] getResource_list() {
		return resource_list;
	}
	public void setResource_list(String[] resource_list) {
		this.resource_list = resource_list;
	}
	
	
	

}
