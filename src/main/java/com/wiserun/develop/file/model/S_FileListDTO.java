package com.wiserun.develop.file.model;

import com.temp.common.model.BaseListDTO;

public class S_FileListDTO extends BaseListDTO{

	//附件编号
	private String SF_Code;
	//创建时间
	private String SF_CreateTime;
	//创建人
	private String SF_CreateUser;
	//状态
	private String SF_State;
	//业务表编号
	private String SF_BussinessCode;
	//附件类型
	private String SF_FileType;
	//附件名称
	private String SF_FileName;
	//附件路径
	private String SF_Path;
	//临时路径
	private String SF_TempPath;
	
	
	//模板编号
	private String  SFT_Code;
	public String getSF_Code() {
		return SF_Code;
	}
	public void setSF_Code(String sF_Code) {
		SF_Code = sF_Code;
	}
	public String getSF_CreateTime() {
		return SF_CreateTime;
	}
	public void setSF_CreateTime(String sF_CreateTime) {
		SF_CreateTime = sF_CreateTime;
	}
	public String getSF_CreateUser() {
		return SF_CreateUser;
	}
	public void setSF_CreateUser(String sF_CreateUser) {
		SF_CreateUser = sF_CreateUser;
	}
	public String getSF_State() {
		return SF_State;
	}
	public void setSF_State(String sF_State) {
		SF_State = sF_State;
	}
	public String getSF_BussinessCode() {
		return SF_BussinessCode;
	}
	public void setSF_BussinessCode(String sF_BussinessCode) {
		SF_BussinessCode = sF_BussinessCode;
	}
	public String getSF_FileType() {
		return SF_FileType;
	}
	public void setSF_FileType(String sF_FileType) {
		SF_FileType = sF_FileType;
	}
	public String getSF_FileName() {
		return SF_FileName;
	}
	public void setSF_FileName(String sF_FileName) {
		SF_FileName = sF_FileName;
	}
	public String getSF_Path() {
		return SF_Path;
	}
	public void setSF_Path(String sF_Path) {
		SF_Path = sF_Path;
	}
	public String getSF_TempPath() {
		return SF_TempPath;
	}
	public void setSF_TempPath(String sF_TempPath) {
		SF_TempPath = sF_TempPath;
	}
	public String getSFT_Code() {
		return SFT_Code;
	}
	public void setSFT_Code(String sFT_Code) {
		SFT_Code = sFT_Code;
	}
	
}
