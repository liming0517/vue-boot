package com.wiserun.permission.common;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.wiserun.common.UserContext;
import com.wiserun.devolop.system.entity.SysUserinfo;

public class RMSSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	/**
	 * 用户session失效,移除用户权限数据
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		SysUserinfo user = UserContext.getInstance().getUser(event.getSession());
		if(user != null){
			RMSContext.getInstance().clearUserRightData(user.getId());
		}
	}

}
