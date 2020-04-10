package com.wiserun.devolop.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wiserun.common.MessageResult;
import com.wiserun.common.PublicCode;
import com.wiserun.common.UserContext;
import com.wiserun.common.ValidateHolder;
import com.wiserun.common.controller.BaseController;
import com.wiserun.common.util.DateUtil;
import com.wiserun.common.util.ValidateUtil;
import com.wiserun.devolop.system.entity.SysUserinfo;
import com.wiserun.devolop.system.service.SysUserinfoService;
import com.wiserun.permission.common.RMSContext;
import com.wiserun.permission.util.PasswordUtil;

@Controller
//@RequestMapping("sys")  
public class LoginController extends BaseController{

	@Autowired
	private SysUserinfoService rUserService;

	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	@RequestMapping("login.do")
	public @ResponseBody MessageResult login(@RequestBody SysUserinfo backUser) {
		
		ValidateHolder validateHolder = ValidateUtil.validate(backUser);	
		if(validateHolder.isSuccess()){
			if (StringUtils.hasText(backUser.getUsername())) {
				SysUserinfo user = rUserService.getByUsername(backUser.getUsername());
				//System.out.println("id========="+user.getId());
				if (user == null || !user.getUsername().equals(backUser.getUsername())) {
					return this.error("用户名密码不正确");
				}
				
				String password = backUser.getPassword();
				String correctHash = user.getPassword();
				
				boolean isPswdCorrect = PasswordUtil.validatePassword(password,
						correctHash);
				
				if (isPswdCorrect) {
					if(user.getState() == PublicCode.Status_Yes) {
						return this.error("无法登录,请联系管理员");
					}
					doLogin(user);
					return this.success();
				}
			}
		}


		return this.error("用户名密码不正确");
	}
	
	private void doLogin(@RequestBody SysUserinfo user) {
		// 缓存当前用户角色权限
		System.out.println("用户id========="+user.getId());
		RMSContext.getInstance().refreshUserRightData(user.getId());
		
		user.setLastlogindate(DateUtil.getCurrentDateTime());
		UserContext.getInstance().setUser(user);
		rUserService.update(user);
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	@RequestMapping("logout.do")
	public @ResponseBody MessageResult logout() {
		RMSContext.getInstance().clearCurrentUserRightData();
		UserContext.getInstance().setUser(null);
		return this.success();
	}

}
