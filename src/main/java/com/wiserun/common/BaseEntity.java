package com.wiserun.common;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.MappedSuperclass;

import com.wiserun.common.UserContext;
import com.wiserun.common.util.DateUtil;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * 基类
 * @author Tommy Shen
 */
/**
 * @author Administrator
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 有效
	 */
	public static final String YES = "1";

	/**
	 * (无效)删除的
	 */
	public static final String NO = "0";

	protected String id;
	protected String createDeptId;//	创建人部门ID
	protected String createDeptCode;//	创建人部门编号
	protected String createDeptName;//	创建人部门名称
	protected String createAreaId;//	创建人分部ID
	protected String createAreaCode;//	创建人分部编号
	protected String createAreaName;//	创建人分部名称
	protected String createUserID;//	创建人Id
	protected String createUserCode;//	创建人编号
	protected String createUserName;//	创建人名字
	protected Date createDate;//	创建时间
	protected String updateUserID;//	更新人Id
	protected String updateUserCode;//	更新人用户编号
	protected String updateUserName;//	更新人名字
	protected Date updateDate;//	最近更新时间
	protected Integer rowOrder;//	排序
	protected String remark;//	备注
	protected String state;//	状态
	protected Integer dataVersion;//	状态

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Integer dataVersion) {
		this.dataVersion = dataVersion;
	}

	public Integer getRowOrder() {
		return rowOrder;
	}

	public void setRowOrder(Integer rowOrder) {
		this.rowOrder = rowOrder;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	public String getUpdateUserID() {
		return updateUserID;
	}

	public void setUpdateUserID(String updateUserID) {
		this.updateUserID = updateUserID;
	}

	public String getCreateDeptId() {
		return createDeptId;
	}

	public void setCreateDeptId(String createDeptId) {
		this.createDeptId = createDeptId;
	}

	public String getCreateDeptCode() {
		return createDeptCode;
	}

	public void setCreateDeptCode(String createDeptCode) {
		this.createDeptCode = createDeptCode;
	}

	public String getCreateDeptName() {
		return createDeptName;
	}

	public void setCreateDeptName(String createDeptName) {
		this.createDeptName = createDeptName;
	}

	public String getCreateAreaId() {
		return createAreaId;
	}

	public void setCreateAreaId(String createAreaId) {
		this.createAreaId = createAreaId;
	}

	public String getCreateAreaCode() {
		return createAreaCode;
	}

	public void setCreateAreaCode(String createAreaCode) {
		this.createAreaCode = createAreaCode;
	}

	public String getCreateAreaName() {
		return createAreaName;
	}

	public void setCreateAreaName(String createAreaName) {
		this.createAreaName = createAreaName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void savebefore() {
		if (this.getId() == null || "".equalsIgnoreCase(this.getId())) {// 新建
			this.setId(UUID.randomUUID().toString());// 生成新的ID
			this.setCreateDate(DateUtil.getCurrentDateTime());
			this.setCreateUserCode(UserContext.getInstance().getUser().getUsername());
			this.setUpdateDate(DateUtil.getCurrentDateTime());
			this.setUpdateUserCode(UserContext.getInstance().getUser().getUsername());
			//此处待扩展，需要增加

		} else {// 修改
			this.setUpdateDate(DateUtil.getCurrentDateTime());
			this.setUpdateUserCode(UserContext.getInstance().getUser().getUsername());

		}
		if (null==this.getState()){this.setState(this.YES);} //未设置状态时，默认状态为1
	}

}
