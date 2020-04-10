package com.wiserun.common.controller;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.wiserun.common.DefaultMessageResult;
import com.wiserun.common.MessageResult;
import com.wiserun.common.util.MyToolsUtil;
import com.wiserun.common.util.RequestUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 提供基础能力的Controller,如果一个Controller具备简单功能可以继承这个类
 * 
 * rebuild 20181210
 */
public abstract class BaseController {
	private static final String DEF_ERROR_PAGE_NAME = "error";

	private Logger logger = Logger.getLogger(getClass());

	private ConcurrentMap<Class<?>,PropertyEditor> propertyEditorStore = new ConcurrentHashMap<Class<?>,PropertyEditor>();

	/**
	 * 获取httpRequest
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取httpSession
	 * @return
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获取客户端真实IP
	 * @return
	 */
	public String getClientIP() {
		return RequestUtil.getClientIP(getRequest());
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		this.initCustomEditor(binder);
	}

	protected void initCustomEditor(WebDataBinder binder) {
		Class<? extends BaseController> clazz = this.getClass();
		// 默认时间转换
		PropertyEditor propertyEditor = propertyEditorStore.get(clazz);
		if (propertyEditor == null) {
			DateFormat dateFormat = new SimpleDateFormat(getDateFormatPattern());
			propertyEditor = new CustomDateEditor(dateFormat, true);
			propertyEditorStore.put(clazz,propertyEditor);
		}

		binder.registerCustomEditor(Date.class, propertyEditor);
	}

	protected String getDateFormatPattern() {
		return "yyyy-MM-dd HH:mm:ss";
	}

	@ExceptionHandler
	protected Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logger.error(e.getMessage(), e);
		ModelAndView mav = this.newModelView(this.getErrorPageName());
		mav.addObject("error", e.getMessage());
		mav.addObject("e", e);
		return mav;
	}

	public Logger getLogger() {
		return logger;
	}
	public ModelAndView newModelView(String viewName) {
		return new ModelAndView(viewName);
	}

	public ModelAndView newModelView(String viewName, Map<String, ?> model) {
		return new ModelAndView(viewName, model);
	}

	public ModelAndView newModelView(String viewName, String modelName, Object modelObject) {
		return new ModelAndView(viewName, modelName, modelObject);
	}

	public ModelMap newModel() {
		return new ModelMap();
	}
	
	/**
	 * 返回默认的错误页面,抛出的Exception将会跳转这个页面
	 * @return
	 */
	protected String getErrorPageName() {
		return DEF_ERROR_PAGE_NAME;
	}
	
    ////////////////////////////////////////////	
	//以下涉及消息类的处理代码均由工具类实现

		/**
		 * 返回成功的视图
		 * 
		 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		 *         方法返回自定义的MessageResult
		 */
		public MessageResult success() {
			return MyToolsUtil.success();
		}

		/**
		 * 返回成功
		 * 
		 * @param message 文本消息
		 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		 *         方法返回自定义的MessageResult
		 */
		public MessageResult success(String message) {
			return MyToolsUtil.success(message);
		}
		
		/**
		 * 返回成功
		 * 
		 * @param message 文本消息
		 * @param attach 附加对象
		 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		 *         方法返回自定义的MessageResult
		 */
		public MessageResult success(String message,Object attach) {
			return MyToolsUtil.success(message, attach);
		}

		/**
		 * 返回错误的视图
		 * 
		 * @param errorMsg
		 *            错误信息
		 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		 *         方法返回自定义的MessageResult
		 */
		public MessageResult error(String errorMsg) {
			return MyToolsUtil.error(errorMsg);
		}
		
		/**
		 * 返回错误的视图
		 * 
		 * @param errorMsg
		 *            错误信息
		 * @param attach 附加对象
		 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		 *         方法返回自定义的MessageResult
		 */
		public MessageResult error(String errorMsg,Object attach) {
			return MyToolsUtil.error(errorMsg, attach);
		}

		/**
		 * 返回错误信息
		 * 
		 * @param errorMsg
		 *            错误信息
		 * @param errorMsgs
		 *            更多错误信息
		 * @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		 *         方法返回自定义的MessageResult
		 */
		public MessageResult error(String errorMsg, List<String> errorMsgs) {
			return MyToolsUtil.error(errorMsg, errorMsgs);
		}
	   ////////////////////////////////////////////	
}
