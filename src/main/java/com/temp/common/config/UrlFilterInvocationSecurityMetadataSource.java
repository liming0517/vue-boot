package com.temp.common.config;


import com.temp.permission.consts.BackendConst;
import com.temp.permission.entity.Resource;
import com.temp.permission.entity.Role;
import com.temp.permission.entity.User;
import com.temp.permission.mapper.UserMapper;
import com.temp.permission.model.OauthUser;
import com.temp.permission.service.LoginService;
import com.temp.permission.service.ResourceService;
import com.temp.permission.util.ConsoleUtil;
import com.wiserun.develop.cas.CasAPI;
import com.wiserun.develop.file.controller.FileController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private LoginService loginService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
	private HttpSession session;
    
    @Autowired
    private UserMapper umapper;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Value("${cas.casSwitch}")
    private  boolean casSwitch=true;
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if ("/user/login".equals(requestUrl) || requestUrl.startsWith("/api")) {
            return null;
        }

        //String token = request.getHeader("X-TOKEN");
        String token= (String)request.getSession().getAttribute("token");
        System.out.println("-----------------::::"+request.getSession().getAttribute("token"));
//        User u=new User();
//        u=umapper.queryOne1(token);  
//        if(u==null) {
//        	CasAPI ca=new CasAPI();
//        	ca.find1(request);
//
//        }else {
//        	 session.setAttribute("user", u.getUserPhone());  //登陆用户名放入session
//             session.setAttribute("token", token);
//        }
       
      //  System.out.println("+++++++++++++++++++++++++++用户名token放入session1:"+session.getAttribute("token"));
      //  System.out.println("+++++++++++++++++++++++++++登陆用户名放入session2:"+session.getAttribute("user"));
        logger.info("========UrlFilterInvocationSecurityMetadataSource token==========="+token);
        //根据前端的token来验证用户的登陆信息
        if (token != null) {
        	logger.info("========UrlFilterInvocationSecurityMetadataSource not null token===========");
            OauthUser oauthUser = (OauthUser) this.loginService.loadUserByToken(token);
            logger.info("========UrlFilterInvocationSecurityMetadataSource loadUserByToken  done===========");
            if(oauthUser==null) {
            	logger.info("========UrlFilterInvocationSecurityMetadataSource oauthUser  null===========");
            	return null;
            }
            //System.out.println("+++++++++++++++++++++++++++用户名token放入session3:"+token);
            //System.out.println("+++++++++++++++++++++++++++登陆用户名放入session4:"+oauthUser.getUserPhone());
            // session.setAttribute("user", oauthUser.getUserPhone());  //登陆用户名放入session
            //session.setAttribute("token", token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    oauthUser, null, oauthUser.getAuthorities());
            logger.info("========UrlFilterInvocationSecurityMetadataSource UsernamePasswordAuthenticationToken===========");
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                    request));
            logger.info("========UrlFilterInvocationSecurityMetadataSource setDetails  done===========");
            //把用户设置到权限验证的上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //如果是超级管理员直接返回
            List<Role> list = oauthUser.getResourceList();
            for (Role role : list) {
            	 logger.info("getRoleId==========="+role.getRoleId());
                if (role.getRoleId() == BackendConst.ROLE_SUPER_ADMIN) {
                    return null;
                }
            }
            ConsoleUtil.formatPrint(authentication);
        } else {
        	//是否使用cas配置
        	if(casSwitch) {
        		//未登陆时跳转主页，由统一登录拦截器处理登录页跳转
	        	logger.info("UrlFilterInvocationSecurityMetadataSource===============当前用户未登录=============");
	        	CasAPI casapi=new CasAPI();
	        	logger.info("UrlFilterInvocationSecurityMetadataSource===============前往testapi.find");
	        	casapi.find1(request);
	        	logger.info("UrlFilterInvocationSecurityMetadataSource===============前往testapi.checkLogin");
	        	casapi.checkLogin(request);
	        	return null;
        	}else {
        		if (request.getMethod().equals("OPTIONS")) {
                    return null;
                }
        	}
        }
        ConsoleUtil.formatPrint("request-url: " + requestUrl);
        logger.info("request-url==========="+requestUrl);
        //todo 这里应该可以根据接口来查询需要的权限？有空再改
        //查询有角色关联了的接口，这样的接口说明其是需要权限验证的
        List<Resource> resourcesList = resourceService.getAllByType(BackendConst.RESOURCE_TYPE_API);
        for (Resource resource : resourcesList) {
            List<Role> roleList = resource.getRoleList();
            ConsoleUtil.formatPrint("database-url: " + resource.getResourceTarget());
            ConsoleUtil.formatPrint("roleList.size(): " + roleList.size());
            if (antPathMatcher.match(resource.getResourceTarget(), requestUrl) && roleList.size() > 0) {
                int size = roleList.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roleList.get(i).getRoleName();  //拥有这个接口权限的角色，赋值到value
                }
                ConsoleUtil.formatPrint("array value: " + values.toString());
                logger.info("values==========="+values.toString());
                return SecurityConfig.createList(values);
            }
        }
        logger.info("request-url==========="+requestUrl);
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
