package com.temp.permission.entity;

import java.util.Date;

public class Role {
    private Integer roleId;
    private Integer roleParentId;
    private String roleName;
    private String roleDesc;
    private Integer roleStatus;
    private Integer roleType;
    private Date roleCreateAt;
    private Date roleUpdateAt;
    
    private String user_token;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleParentId() {
        return roleParentId;
    }

    public void setRoleParentId(Integer roleParentId) {
        this.roleParentId = roleParentId;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Date getRoleCreateAt() {
        return roleCreateAt;
    }

    public void setRoleCreateAt(Date roleCreateAt) {
        this.roleCreateAt = roleCreateAt;
    }

    public Date getRoleUpdateAt() {
        return roleUpdateAt;
    }

    public void setRoleUpdateAt(Date roleUpdateAt) {
        this.roleUpdateAt = roleUpdateAt;
    }

	public String getUser_token() {
		return user_token;
	}

	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}
}
