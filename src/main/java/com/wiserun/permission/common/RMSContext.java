package com.wiserun.permission.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.wiserun.common.SpringContext;
import com.wiserun.common.UserContext;
import com.wiserun.common.WebContext;
import com.wiserun.common.expression.Expression;
import com.wiserun.common.support.MyPropertyPlaceholderConfigurer;
import com.wiserun.devolop.system.entity.SysUserRole;
import com.wiserun.devolop.system.entity.SysUserinfo;
import com.wiserun.devolop.system.service.SysFunctionService;
import com.wiserun.devolop.system.service.SysResService;
import com.wiserun.devolop.system.service.SysUserRoleService;



public enum RMSContext {
	INS;

	private static final String USER_ROLE_IDS = "user_role_ids";
	private static final String ADMIN_ROLE_ID = "sys.adminRoleId";
	private static final String USE_SUPER_ADMIN = "sys.openSuperPermission";
	
	private static String adminRoleId;
	private static Boolean isOpenSuperAdmin;

	private static UserMenuContext userMenuContext = new UserMenuContext();
	private static UserPermissionContext permissionContext = new UserPermissionContext();
	
	public static RMSContext getInstance() {
		return INS;
	}

	
	/**
	 * 获取当前用户权限
	 * @return
	 */
	public UserPermission getCurrentUserPermission() {
		SysUserinfo user = UserContext.getInstance().getUser();
		//RUser user = UserContext.getInstance().getUser();
		if(user == null) {
			return new UserPermission();
		}
		//return this.getUserPermission(user.getUserId());
		return this.getUserPermission(user.getId());
	}
	
	public UserPermission getUserPermission(String userId) {
		return permissionContext.get(userId);
	}
	
	
	/**
	 * 刷新保存用权限数据.(系统功能=菜单+操作点)
	 */
	public void refreshUserRightData(String userId){
		SysFunctionService sysFunctionService = SpringContext.getBean(SysFunctionService.class);
		
		UserPermission userPermission = sysFunctionService.buildUserPermission(userId);
		
		permissionContext.put(userId, userPermission);
		
		//userSysFunctionMap.put(username, userSysFuns);
		
		saveUserRoleIds(userId);
		
		refreshUserMenu(userId);
	}
	
	/**
	 * 刷新用户菜单
	 * @param userId
	 */
	public void refreshUserMenu(String userId) {
		SysResService sysResService =SpringContext.getBean(SysResService.class);
		System.out.println("userId_menu==userId===="+userId);
		List<UserMenu> userMenu = sysResService.getUserMenu(userId);
		System.out.println("userId_menu======"+userMenu);
		userMenuContext.put(userId, userMenu);
	}
	
	/**
	 * 获取用户菜单
	 * @return
	 */
	public List<UserMenu> getUserMenu() {
		SysUserinfo user = UserContext.getInstance().getUser();
		//return userMenuContext.get(user.getUserId());
		return userMenuContext.get(user.getId());
	}
	
	/**
	 * 获取用户数据权限条件
	 * @return
	 *//*
	public List<Expression> getUserDataExpressions() {
		String srId = WebContext.getInstance().getRequest().getParameter("srId");
		
		if(srId == null){
			return Collections.emptyList();
		}
    	List<String> roleIds = getCurrentUserRoleIds();
    	RDataPermissionService dataPermissionService = SpringContext.getBean(RDataPermissionService.class);
    	
    	return dataPermissionService.buildDataExpresstions(roleIds, srId);//Integer.valueOf(srId));
	}*/
	
	/**
	 * 保存用户角色ID
	 * @param userId
	 */
	public void saveUserRoleIds(String userId){
		SysUserRoleService userRoleService = SpringContext.getBean(SysUserRoleService.class);
		List<SysUserRole> userRoles = userRoleService.getUserRole(userId);
		
		if(CollectionUtils.isNotEmpty(userRoles)){
			List<String> roleIds = new ArrayList<String>(userRoles.size());
			for (SysUserRole userRole : userRoles) {
				roleIds.add(userRole.getId());
			}
			System.out.println("user_role_ids======"+roleIds);
			WebContext.getInstance().setAttr(USER_ROLE_IDS, roleIds);
		}
	}
	
	/**
	 * 获取当前用户角色ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getCurrentUserRoleIds() {
		Object roleIds = WebContext.getInstance().getAttr(USER_ROLE_IDS);
		
		if(roleIds == null){
			return Collections.emptyList();
		}
		
		return (List<String>)roleIds;
	}
	
	/**
	 * 刷新所有用户的系统功能
	 */
	public void refreshAllUserRightData(){
		Set<String> userIdSet = permissionContext.keySet();
		for (String userId : userIdSet) {
			this.refreshUserRightData(userId);
		}
	}
	
	/**
	 * 移除用户权限数据,在用户注销或session失效可以用到
	 * @param userId
	 */
	public void clearUserRightData(String userId){
		if("".equalsIgnoreCase(userId)){
			return;
		}
		permissionContext.remove(userId);
		userMenuContext.remove(userId);
	}
	
	/**
	 * 移除当前用户权限数据
	 */
	public void clearCurrentUserRightData(){
		SysUserinfo user = UserContext.getInstance().getUser();
		if(user != null){
			//this.clearUserRightData(user.getUserId());
			this.clearUserRightData(user.getId());
		}
	}
	
	/**
	 * 获取超级管理员角色ID,该ID在config.properties中设置
	 * @return
	 */
	public String getAdminRoleId() {
		
		if(adminRoleId == null) {
			adminRoleId = MyPropertyPlaceholderConfigurer.getProperty(ADMIN_ROLE_ID);
			//adminRoleId = Integer.valueOf(adminRoleIdStr);
			
		}
		return adminRoleId;
	}
	
	/**
	 * 是否开启超级权限,开启的话,超级管理员角色可以访问所有内容
	 * @return
	 */
	private boolean isOpenSuperPermission() {
		//USE_SUPER_ADMIN
		if(isOpenSuperAdmin == null) {
			String useSuperAdmin = MyPropertyPlaceholderConfigurer.getProperty(USE_SUPER_ADMIN);
			isOpenSuperAdmin = "true".equals(useSuperAdmin);
		}
		return isOpenSuperAdmin;
	}
	
	/**
	 * 当前用户是否具有管理员角色
	 * @return
	 */
	private boolean isCurrentUserHaveAdminRole() {
		List<String> currentUserRoleIds = this.getCurrentUserRoleIds();
		
		String superAdminRoleId = this.getAdminRoleId();
		//int superAdminRoleId = this.getAdminRoleId();
		return currentUserRoleIds.contains(superAdminRoleId);
	}
	
	/**
	 * 当前用户是否具有超级权限
	 * @return
	 */
	public boolean isCurrentUserHaveSuperPermission() {
		return this.isOpenSuperPermission() && isCurrentUserHaveAdminRole();
	}
}
