package com.wiserun.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import com.wiserun.common.DefaultMessageResult;
import com.wiserun.common.MessageResult;
import com.wiserun.common.SearchSupport;
import com.wiserun.common.UserContext;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wiserun.common.PublicCode;

/**
 * @author hades
 * 通用工具类
 * 基于现有各类工具做封装
 */
public abstract class MyToolsUtil extends org.springframework.beans.BeanUtils {

		public static final Logger logger = Logger.getLogger(MyToolsUtil.class);	   
		///////////A-以下操作与消息类有关--静态方法///////////////////////////////////////
		/**
		* 返回成功的视图
		* 
		* @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		*         方法返回自定义的MessageResult
		*/
		public static MessageResult success() {
			MessageResult msgResult = getMessageResult();
			msgResult.setSuccess(true);
			return msgResult;
		}
		
		/**
		* 返回成功
		* 
		* @param message 文本消息
		* @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		*         方法返回自定义的MessageResult
		*/
		public static MessageResult success(String message) {
			MessageResult msgResult = getMessageResult();
			msgResult.setSuccess(true);
			msgResult.setMessage(message);
			return msgResult;
		}
		
		/**
		* 返回成功
		* 
		* @param message 文本消息
		* @param attach 附加对象
		* @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		*         方法返回自定义的MessageResult
		*/
		public static MessageResult success(String message,Object attach) {
			MessageResult msgResult = getMessageResult();
			msgResult.setSuccess(true);
			msgResult.setMessage(message);
			msgResult.setAttach(attach);
			return msgResult;
		}
		
		/**
		* 返回错误的视图
		* 
		* @param errorMsg
		*            错误信息
		* @return 默认返回DefaultMessageResult对象,可以重写getMessageResult()
		*         方法返回自定义的MessageResult
		*/
		public static MessageResult error(String errorMsg) {
			MessageResult msgResult = getMessageResult();
			msgResult.setSuccess(false);
			msgResult.setMessage(errorMsg);
			return msgResult;
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
		public static MessageResult error(String errorMsg,Object attach) {
			MessageResult msgResult = getMessageResult();
			msgResult.setSuccess(false);
			msgResult.setMessage(errorMsg);
			msgResult.setAttach(attach);
			return msgResult;
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
		public static MessageResult error(String errorMsg, List<String> errorMsgs) {
			MessageResult msgResult = error(errorMsg);
			msgResult.setMessages(errorMsgs);
			return msgResult;
		}
		
		
		/**
		* 返回默认的消息实现类,可覆盖此方法返回自定义的消息实现类
		* 
		* @return
		*/
		public static MessageResult getMessageResult() {
			return new DefaultMessageResult();
		}
		///////////////A-以上为消息类操作有关/////////////////////////////////////
		
		/////////B-以下为与前台参数有关的一些公共方法//////////
		/**
		* 将 request 参数转换为 Map<String, Object>
		* hades
		* @param request
		* @return
		*/
		public static Map<String, Object> request2Map() {
			Map<String, Object> param = new HashMap<String, Object>();
			Enumeration<String> paramnames = getRequest().getParameterNames();
			while (paramnames.hasMoreElements()) {
				String paramName = paramnames.nextElement();
				String[] paramValue = getRequest().getParameterValues(paramName);
				if (paramValue != null) {
					if (paramValue.length > 1) {
						param.put(paramName, paramValue);
					} else {
						param.put(paramName, paramValue[0]);
					}
				}
			}
			return param;
		}
		
		/**
		* 将 request 参数转换为查询用Query 对象
		* hades
		* @param request
		* @return
		*/
		public static ExpressionQuery request2ExpressionQuery() {
			ExpressionQuery query = ExpressionQuery.buildQueryAll();
			Enumeration<String> paramnames = getRequest().getParameterNames();
		
			while (paramnames.hasMoreElements()) {
				String paramName = paramnames.nextElement();
				String[] paramValue = getRequest().getParameterValues(paramName);
				if (paramValue != null) {
					if (paramValue.length > 1) {
						query.add(new ValueExpression(paramName, paramValue));
					} else {
						query.add(new ValueExpression(paramName, paramValue[0]));
					}
				}
			}
			return query;
		
		}	
		////////B-以上为与前台参数有关的一些公共方法//////////
		
		////////C-以下为与查询前置处理有关的一些公共方法//////////	
		/**
		* 设置查询属性 无权限限制 （一般用于获取全局配置） 
		* @param query 传入查询用参数集合
		*/
		public static <F> void setBeforeQueryNoLimit(ExpressionQuery query) {
			query.updateValueExpression(new ValueExpression("state", "1"  ) );//状态
			
		}
		/**
		*设置默认查询属性  （带权限） 
		* @param query 传入查询用参数集合
		*/
		public static <F> void setBeforeQuery(ExpressionQuery query) {

			query.updateValueExpression(new ValueExpression("state", "1"  ) );//状态 默认有效
				
		}
		/**
		*设置默认查询属性  带权限） 
		* @param o 传入查询用参数对象
		*/
		public static <F> void setBeforeQuery(F o) {
			
			//设置默认属性
			Class<?> cls = o.getClass();
			Method[] methods = cls.getMethods();
			try {
				//String getter = "getCustomerId";
				//Method method = o.getClass().getMethod(getter, new Class[] {});
				//Object value = method.invoke(o, new Object[] {});
				//if (value==null){
					for (Method m:methods){
						if ("setStateSch".equals(m.getName())){ //状态（查询对象 默认有效）
							m.invoke(o, "1");
						}
						
						
						if ("setState".equals(m.getName())){ //状态（默认有效）
							m.invoke(o, "1");
						}
				
					}							
				//}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//C-以上为与查询前置处理有关的一些公共方法///////
		
		//D-以下为与表达式处理有关的一些公共方法///////
		/**
		* 将 查询对象转换为 查询表达式
		* @param searchEntity
		* @return
		*/
		public static  ExpressionQuery buildExpressionQuery(SearchSupport searchEntity) {
			ExpressionQuery query = new ExpressionQuery();
		
			query.addAnnotionExpression(searchEntity)
				.addPaginationInfo(searchEntity);
		
			return query;
		}
		//D-以上为与表达式处理有关的一些公共方法///////
		
		
		/**
		* 获取httpRequest
		* @return
		*/
		public static HttpServletRequest getRequest() {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		}
		
		//
}
