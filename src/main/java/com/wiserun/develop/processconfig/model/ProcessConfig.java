package com.wiserun.develop.processconfig.model;

public class ProcessConfig {
	//系统表_流程配置表
	//记录各类流程记录信息
	
	
	
	
	//流程配置编号
	private String SPC_Code;
	//创建时间
	private String SPC_CreateTime;
	//创建人
	private String SPC_CreateUser;
	//状态
	private String SPC_State;
	//流程模块
	private String SPC_Model;
	//版本号
	private String SPC_Version;
	//步骤编号
	private String SPC_Step;
	//配置角色
	private String SPC_Role;
	//创建开始时间
	private String SPC_CreateTimeStart;
	//创建结束时间
	private String SPC_CreateTimeEnd;
	//配置角色列表
	private String[] SPC_Rolelist;
	private Object Rolelist;
	//模块名
	private String SPC_ModelName;
	
	
	public String getSPC_Code() {
		return SPC_Code;
	}
	public void setSPC_Code(String sPC_Code) {
		SPC_Code = sPC_Code;
	}
	public String getSPC_CreateTime() {
		return SPC_CreateTime;
	}
	public void setSPC_CreateTime(String sPC_CreateTime) {
		SPC_CreateTime = sPC_CreateTime;
	}
	public String getSPC_CreateUser() {
		return SPC_CreateUser;
	}
	public void setSPC_CreateUser(String sPC_CreateUser) {
		SPC_CreateUser = sPC_CreateUser;
	}
	public String getSPC_State() {
		return SPC_State;
	}
	public void setSPC_State(String sPC_State) {
		SPC_State = sPC_State;
	}
	public String getSPC_Model() {
		return SPC_Model;
	}
	public void setSPC_Model(String sPC_Model) {
		SPC_Model = sPC_Model;
	}
	public String getSPC_Version() {
		return SPC_Version;
	}
	public void setSPC_Version(String sPC_Version) {
		SPC_Version = sPC_Version;
	}
	public String getSPC_Step() {
		return SPC_Step;
	}
	public void setSPC_Step(String sPC_Step) {
		SPC_Step = sPC_Step;
	}
	public String getSPC_Role() {
		return SPC_Role;
	}
	public void setSPC_Role(String sPC_Role) {
		SPC_Role = sPC_Role;
	}
	public String getSPC_CreateTimeStart() {
		return SPC_CreateTimeStart;
	}
	public void setSPC_CreateTimeStart(String sPC_CreateTimeStart) {
		SPC_CreateTimeStart = sPC_CreateTimeStart;
	}
	public String getSPC_CreateTimeEnd() {
		return SPC_CreateTimeEnd;
	}
	public void setSPC_CreateTimeEnd(String sPC_CreateTimeEnd) {
		SPC_CreateTimeEnd = sPC_CreateTimeEnd;
	}

	public String getSPC_ModelName() {
		return SPC_ModelName;
	}
	public void setSPC_ModelName(String sPC_ModelName) {
		SPC_ModelName = sPC_ModelName;
	}
	public String[] getSPC_Rolelist() {
		return SPC_Rolelist;
	}
	public void setSPC_Rolelist(String[] sPC_Rolelist) {
		SPC_Rolelist = sPC_Rolelist;
	}
	public Object getRolelist() {
		return Rolelist;
	}
	public void setRolelist(Object rolelist) {
		Rolelist = rolelist;
	}

	
	
	
	
	
	
	
}
