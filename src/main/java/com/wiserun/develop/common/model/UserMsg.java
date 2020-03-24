package com.wiserun.develop.common.model;

public class UserMsg {
	private String usercode;
	private String username;
	private String email;
	private String token;
	private String department;
	private String role;
	private String[] departmentlist;
	private String[] rolelist;
	
	
	
	
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String[] getDepartmentlist() {
		return departmentlist;
	}
	public void setDepartmentlist(String[] departmentlist) {
		this.departmentlist = departmentlist;
	}
	public String[] getRolelist() {
		return rolelist;
	}
	public void setRolelist(String[] rolelist) {
		this.rolelist = rolelist;
	}
	
	
	
}
