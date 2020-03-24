package com.wiserun.develop.process.model;

public class Process {
	//流程公共类
	
	
	
	//流程模块
	private String Model;
	//流程模块主键
	private String ModelCode;
	//操作
	private String Action;
	//操作结果
	private String Result;
	//描述
	private String Description;
	//流程批次
	private String Batch;
	//流程配置编号
	private String ConfigNo;
	//版本号
	private String Version;
	//步骤编号
	private String Step;
	//配置角色
	private String Role;
	
	private String LoginUser;
	
	
	
	
	
	
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getModelCode() {
		return ModelCode;
	}
	public void setModelCode(String modelCode) {
		ModelCode = modelCode;
	}
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getBatch() {
		return Batch;
	}
	public void setBatch(String batch) {
		Batch = batch;
	}
	public String getConfigNo() {
		return ConfigNo;
	}
	public void setConfigNo(String configNo) {
		ConfigNo = configNo;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getStep() {
		return Step;
	}
	public void setStep(String step) {
		Step = step;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getLoginUser() {
		return LoginUser;
	}
	public void setLoginUser(String loginUser) {
		LoginUser = loginUser;
	}
	
	

}
