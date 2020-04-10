package com.wiserun.devolop.system.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.wiserun.common.BaseEntity;
import com.wiserun.common.IUser;

/**
创建人：代码生成器
Create date: **请补充**
功能说明：sys.userinfo Entity实体 忽略 系统预定义字段 （但还是要定义id status 和 remark属性，用于特殊情况下的处理）
修订信息(**请补充**)
修订日期:**请补充**
原因:**请补充**
修订人:**请补充**
**/

/**
  sys.userinfo
*/
public class SysUserinfo extends BaseEntity implements IUser {
                   	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 用户名
	private String username;
 	// 密码
	private String password;
 	// 最近登录时间
	private Date lastlogindate;
 	// 真实姓名
	private String trueName;
 	// 用户编号
	private String usercode;
 	// 手机号
	private String phoneNo;
 	// 身份证号
	private String idnumber;
 	// 微信号
	private String weixinNo;
 	// 登录名（昵称）
	private String loginName;
 	// 头像
	private String headPortrait;
 	// 扩展1
	private String ext1;
 	// 扩展2
	private String ext2;
 	// 主部门ID
	private String mainDeptId;
 	// 主部门编号
	private String mainDeptCode;
 	// 主部门名称
	private String mainDeptName;
 	// 主角色ID
	private String mainRoleId;
 	// 主角色编号
	private String mainRoleCode;
 	// 主角色名称
	private String mainRoleName;

	/** 设置 用户名,对应字段 sys_userinfo.username */
	public void setUsername(String username){
		this.username = username;
	}
	/** 获取 用户名,对应字段 sys_userinfo.username */
	public String getUsername(){
		return this.username;
	}
	/** 设置 密码,对应字段 sys_userinfo.password */
	public void setPassword(String password){
		this.password = password;
	}
	/** 获取 密码,对应字段 sys_userinfo.password */
	public String getPassword(){
		return this.password;
	}
	/** 设置 最近登录时间,对应字段 sys_userinfo.lastlogindate */
	public void setLastlogindate(Date date){
		this.lastlogindate = date;
	}
	/** 获取 最近登录时间,对应字段 sys_userinfo.lastlogindate */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLastlogindate(){
		return this.lastlogindate;
	}
	/** 设置 真实姓名,对应字段 sys_userinfo.trueName */
	public void setTrueName(String trueName){
		this.trueName = trueName;
	}
	/** 获取 真实姓名,对应字段 sys_userinfo.trueName */
	public String getTrueName(){
		return this.trueName;
	}
	/** 设置 用户编号,对应字段 sys_userinfo.usercode */
	public void setUsercode(String usercode){
		this.usercode = usercode;
	}
	/** 获取 用户编号,对应字段 sys_userinfo.usercode */
	public String getUsercode(){
		return this.usercode;
	}
	/** 设置 手机号,对应字段 sys_userinfo.phoneNo */
	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}
	/** 获取 手机号,对应字段 sys_userinfo.phoneNo */
	public String getPhoneNo(){
		return this.phoneNo;
	}
	/** 设置 身份证号,对应字段 sys_userinfo.idnumber */
	public void setIdnumber(String idnumber){
		this.idnumber = idnumber;
	}
	/** 获取 身份证号,对应字段 sys_userinfo.idnumber */
	public String getIdnumber(){
		return this.idnumber;
	}
	/** 设置 微信号,对应字段 sys_userinfo.weixinNo */
	public void setWeixinNo(String weixinNo){
		this.weixinNo = weixinNo;
	}
	/** 获取 微信号,对应字段 sys_userinfo.weixinNo */
	public String getWeixinNo(){
		return this.weixinNo;
	}
	/** 设置 登录名（昵称）,对应字段 sys_userinfo.loginName */
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	/** 获取 登录名（昵称）,对应字段 sys_userinfo.loginName */
	public String getLoginName(){
		return this.loginName;
	}
	/** 设置 头像,对应字段 sys_userinfo.headPortrait */
	public void setHeadPortrait(String headPortrait){
		this.headPortrait = headPortrait;
	}
	/** 获取 头像,对应字段 sys_userinfo.headPortrait */
	public String getHeadPortrait(){
		return this.headPortrait;
	}
	/** 设置 扩展1,对应字段 sys_userinfo.ext1 */
	public void setExt1(String ext1){
		this.ext1 = ext1;
	}
	/** 获取 扩展1,对应字段 sys_userinfo.ext1 */
	public String getExt1(){
		return this.ext1;
	}
	/** 设置 扩展2,对应字段 sys_userinfo.ext2 */
	public void setExt2(String ext2){
		this.ext2 = ext2;
	}
	/** 获取 扩展2,对应字段 sys_userinfo.ext2 */
	public String getExt2(){
		return this.ext2;
	}
	/** 设置 主部门ID,对应字段 sys_userinfo.mainDeptId */
	public void setMainDeptId(String mainDeptId){
		this.mainDeptId = mainDeptId;
	}
	/** 获取 主部门ID,对应字段 sys_userinfo.mainDeptId */
	public String getMainDeptId(){
		return this.mainDeptId;
	}
	/** 设置 主部门编号,对应字段 sys_userinfo.mainDeptCode */
	public void setMainDeptCode(String mainDeptCode){
		this.mainDeptCode = mainDeptCode;
	}
	/** 获取 主部门编号,对应字段 sys_userinfo.mainDeptCode */
	public String getMainDeptCode(){
		return this.mainDeptCode;
	}
	/** 设置 主部门名称,对应字段 sys_userinfo.mainDeptName */
	public void setMainDeptName(String mainDeptName){
		this.mainDeptName = mainDeptName;
	}
	/** 获取 主部门名称,对应字段 sys_userinfo.mainDeptName */
	public String getMainDeptName(){
		return this.mainDeptName;
	}
	/** 设置 主角色ID,对应字段 sys_userinfo.mainRoleId */
	public void setMainRoleId(String mainRoleId){
		this.mainRoleId = mainRoleId;
	}
	/** 获取 主角色ID,对应字段 sys_userinfo.mainRoleId */
	public String getMainRoleId(){
		return this.mainRoleId;
	}
	/** 设置 主角色编号,对应字段 sys_userinfo.mainRoleCode */
	public void setMainRoleCode(String mainRoleCode){
		this.mainRoleCode = mainRoleCode;
	}
	/** 获取 主角色编号,对应字段 sys_userinfo.mainRoleCode */
	public String getMainRoleCode(){
		return this.mainRoleCode;
	}
	/** 设置 主角色名称,对应字段 sys_userinfo.mainRoleName */
	public void setMainRoleName(String mainRoleName){
		this.mainRoleName = mainRoleName;
	}
	/** 获取 主角色名称,对应字段 sys_userinfo.mainRoleName */
	public String getMainRoleName(){
		return this.mainRoleName;
	}
	
}
