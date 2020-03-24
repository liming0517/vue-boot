package com.wiserun.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * 针对类的操作，支持：
 * 1-动态调用类方法
 * 2-将类转为map（便于调试）
 * @author tanghc
 * @version 20181229
 */
public class ClassUtil {
	private static final Logger logger = Logger.getLogger(ClassUtil.class);
	
	private static final String PREFIX_GET = "get";

	private ClassUtil() {
	};

	private static String classPath = "";

	private static ClassUtil classUtil = new ClassUtil();


	/**
	 * 返回定义类时的泛型参数的类型. <br>
	 * 如:定义一个BookManager类<br>
	 * <code>{@literal public BookManager extends GenricManager<Book,Address>}{...} </code><br>
	 * 调用getSuperClassGenricType(getClass(),0)将返回Book的Class类型<br>
	 * 调用getSuperClassGenricType(getClass(),1)将返回Address的Class类型
	 * 
	 * @param clazz
	 *            从哪个类中获取
	 * @param index
	 *            泛型参数索引,从0开始
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index)
			throws IndexOutOfBoundsException {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	private String _getClassPath() {
		if ("".equals(classPath)) {
			classPath = getClass().getClassLoader().getResource("").getPath();
		}
		return classPath;
	}

	/**
	 * 获取class根目录
	 * 
	 * @return
	 */
	public static String getClassRootPath() {
		return classUtil._getClassPath();
	}
	
	/**
	 * 返回类名并且第一个字母小写
	 * @param clazz
	 * @return
	 * @author tanghc
	 */
	public static String getClassSimpleName(Class<?> clazz) {
		String className = clazz.getSimpleName();
		return className.substring(0, 1).toLowerCase()
				+ className.substring(1);
	}
	/**
	 * 将对象转map
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> convertObj2Map(Object obj){
		if(obj == null){
			return Collections.emptyMap();
		}
		Method[] methods = obj.getClass().getMethods(); // .getClass().getDeclaredMethods(); 改为获取所有方法属性
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			for (Method method : methods) {
				String methodName = method.getName();
	
				if (methodName.startsWith(PREFIX_GET)) {
					String fieldName = buildFieldName(methodName);
					Object value = method.invoke(obj, new Object[] {});
					map.put(fieldName, value);
				}
			}
		}catch (Exception e) {
			return Collections.emptyMap();
		}
		
		return map;
	}
	
	// 构建列名
	private static String buildFieldName(String methodName) {
		return methodName.substring(3, 4).toLowerCase()
				+ methodName.substring(4);

	}
	
	
	
	/**
	 * 调用方法执行
	 * @param o
	 * @param methodMap
	 * @param methodName
	 * @return
	 */
	@Deprecated
	public static Object invoke(Object o, Map<String, Method> methodMap, String methodName) {
		Method md = methodMap.get(methodName.toLowerCase());
		try {
			return md.invoke(o);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 动态调用某个类的特定方法
	 * @param clazzName 类名（含完整路径，如com.wiserun.develop.system.userinfo）
	 * @param methodName 调用方法名
	 * @param params 调用方法所需要的参数
	 * @return Object 可根据需要转换
	 */
    public static Object invoke(String clazzName,String methodName,Object...params){
        try {
            Class<?> clazz=Class.forName(clazzName);
            Object obj=clazz.newInstance();
            Method[] methods = clazz.getMethods(); //可调用继承的方法
          //以下逻辑与 调用某个实例类的特定方法一致
            Method callMethod=null;
            for(Method method:methods){
                if(method.getName().equals(methodName)){
                    callMethod=method;
                    break;
                }
            }
            callMethod.setAccessible(true);
            return (Object) callMethod.invoke(obj,params);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * 动态调用某个实例类的特定方法
	 * @param obj 类对象
	 * @param methodName 调用方法名
	 * @param params 调用方法所需要的参数
	 * @return Object 可根据需要转换
	 */
    public static Object invoke(Object obj,String methodName,Object...params){
        try {
            Method[] methods = obj.getClass().getMethods(); //可调用继承的方法
            Method callMethod=null;
            for(Method method:methods){
                if(method.getName().equals(methodName)){
                    callMethod=method;
                    break;
                }
            }
            callMethod.setAccessible(true);
            return (Object) callMethod.invoke(obj,params);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	/**
	 * 针对对象中类型为String 的属性做trim操作
	 * @param obj
	 * @return
	 */
	public static void ObjTrim(Object obj){
		//logger.info("obj======"+ convertObj2Map(obj)); 

		if(obj == null){
			return;
		}
		// 拿到该类  
		Class<?> clz = obj.getClass();
		// 获取实体类的所有属性，返回Field数组  
		Field[] fields = clz.getDeclaredFields(); 

		for (Field field : fields) {
			
			
			if (field.getGenericType().toString().equals(
					"class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名 
				 
				//允许读写该字段
				 if (!field.isAccessible()) field.setAccessible(true);
				 try {
					 
					 //取值
					String oldValue=(String)field.get(obj); 
					 if(oldValue!=null){

							
						 //赋值 
						 field.set(obj, oldValue.trim());
					 }
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				 
				
			}
		}
		
		//logger.info("NEW obj======"+ convertObj2Map(obj)); 
		
	}
	
	     // 把一个字符串的第一个字母大写、效率是最高的、  
	     private static String getMethodName(String fildeName) throws Exception{  
	         byte[] items = fildeName.getBytes();  
	         items[0] = (byte) ((char) items[0] - 'a' + 'A');  
	         return new String(items);  
	     }  
	
	public static void main(String[] args) {
		
	}

}
