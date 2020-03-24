package com.wiserun.develop.message.model;

import com.temp.common.model.BaseDTO;

public class MessageDTO extends BaseDTO{
	//通知编号
	private String SM_Code;
	//创建时间
	private String SM_CreateTime;
	//创建人
	private String SM_CreateUser;
	//状态
	private String SM_State;
	//通知内容
	private String SM_Content;
	//关联业务主键
	private String SM_BussinessCode;
	//关联模块
	private String SM_Model;
	//关联模块的操作
	private String SM_Operate;
	//详情地址
	private String SM_URL;
	//被通知人
	private String SM_Notified;
	//已阅人
	private String SM_Readed;
	
	
	
	
	
	//创建开始时间
	private String SM_CreateTimeStart;
	//创建结束时间
	private String SM_CreateTimeEnd;
	private String ApplyCode;
	private String OrderCode;
	//判断页面上信息已读或未读
	private String SM_YoN;
	private String LoginUser;
	private String SM_CreateUserName;
	public String getSM_Code() {
		return SM_Code;
	}
	public void setSM_Code(String sM_Code) {
		SM_Code = sM_Code;
	}
	public String getSM_CreateTime() {
		return SM_CreateTime;
	}
	public void setSM_CreateTime(String sM_CreateTime) {
		SM_CreateTime = sM_CreateTime;
	}
	public String getSM_CreateUser() {
		return SM_CreateUser;
	}
	public void setSM_CreateUser(String sM_CreateUser) {
		SM_CreateUser = sM_CreateUser;
	}
	public String getSM_State() {
		return SM_State;
	}
	public void setSM_State(String sM_State) {
		SM_State = sM_State;
	}
	public String getSM_Content() {
		return SM_Content;
	}
	public void setSM_Content(String sM_Content) {
		SM_Content = sM_Content;
	}
	public String getSM_BussinessCode() {
		return SM_BussinessCode;
	}
	public void setSM_BussinessCode(String sM_BussinessCode) {
		SM_BussinessCode = sM_BussinessCode;
	}
	public String getSM_Model() {
		return SM_Model;
	}
	public void setSM_Model(String sM_Model) {
		SM_Model = sM_Model;
	}
	public String getSM_Operate() {
		return SM_Operate;
	}
	public void setSM_Operate(String sM_Operate) {
		SM_Operate = sM_Operate;
	}
	public String getSM_URL() {
		return SM_URL;
	}
	public void setSM_URL(String sM_URL) {
		SM_URL = sM_URL;
	}
	public String getSM_Notified() {
		return SM_Notified;
	}
	public void setSM_Notified(String sM_Notified) {
		SM_Notified = sM_Notified;
	}
	public String getSM_Readed() {
		return SM_Readed;
	}
	public void setSM_Readed(String sM_Readed) {
		SM_Readed = sM_Readed;
	}
	public String getSM_CreateTimeStart() {
		return SM_CreateTimeStart;
	}
	public void setSM_CreateTimeStart(String sM_CreateTimeStart) {
		SM_CreateTimeStart = sM_CreateTimeStart;
	}
	public String getSM_CreateTimeEnd() {
		return SM_CreateTimeEnd;
	}
	public void setSM_CreateTimeEnd(String sM_CreateTimeEnd) {
		SM_CreateTimeEnd = sM_CreateTimeEnd;
	}
	public String getApplyCode() {
		return ApplyCode;
	}
	public void setApplyCode(String applyCode) {
		ApplyCode = applyCode;
	}
	public String getOrderCode() {
		return OrderCode;
	}
	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}
	public String getSM_YoN() {
		return SM_YoN;
	}
	public void setSM_YoN(String sM_YoN) {
		SM_YoN = sM_YoN;
	}
	public String getLoginUser() {
		return LoginUser;
	}
	public void setLoginUser(String loginUser) {
		LoginUser = loginUser;
	}
	public String getSM_CreateUserName() {
		return SM_CreateUserName;
	}
	public void setSM_CreateUserName(String sM_CreateUserName) {
		SM_CreateUserName = sM_CreateUserName;
	}

	
}
