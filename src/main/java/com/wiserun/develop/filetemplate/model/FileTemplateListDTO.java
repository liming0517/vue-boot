package com.wiserun.develop.filetemplate.model;

import com.temp.common.model.BaseListDTO;

public class FileTemplateListDTO extends BaseListDTO{
	//模板编号
	private String SFT_Code;
	//创建时间
	private String SFT_CreateTime;
	//创建人
	private String SFT_CreateUser;
	//状态
	private String SFT_State;
	//模板类型
	private String SFT_TemplateType;
	//模块编号
	private String SFT_ModelCode;
	public String getSFT_Code() {
		return SFT_Code;
	}
	public void setSFT_Code(String sFT_Code) {
		SFT_Code = sFT_Code;
	}
	public String getSFT_CreateTime() {
		return SFT_CreateTime;
	}
	public void setSFT_CreateTime(String sFT_CreateTime) {
		SFT_CreateTime = sFT_CreateTime;
	}
	public String getSFT_CreateUser() {
		return SFT_CreateUser;
	}
	public void setSFT_CreateUser(String sFT_CreateUser) {
		SFT_CreateUser = sFT_CreateUser;
	}
	public String getSFT_State() {
		return SFT_State;
	}
	public void setSFT_State(String sFT_State) {
		SFT_State = sFT_State;
	}
	public String getSFT_TemplateType() {
		return SFT_TemplateType;
	}
	public void setSFT_TemplateType(String sFT_TemplateType) {
		SFT_TemplateType = sFT_TemplateType;
	}
	public String getSFT_ModelCode() {
		return SFT_ModelCode;
	}
	public void setSFT_ModelCode(String sFT_ModelCode) {
		SFT_ModelCode = sFT_ModelCode;
	}

	
}
