package com.wiserun.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtil {

	private static  Logger logger=LoggerFactory.getLogger(PropertyUtil.class);
	
	private static Properties props = new Properties();
	
	public static void load() {
		if(props.keySet().size()==0){
			String filePath = "sys.properties";
			try {
				InputStream fis = new FileInputStream(PropertyUtil.class.getResource("/").getPath()+filePath);
				props.load(fis);
				
				for(Entry<Object,Object> entry:props.entrySet()){
					logger.debug(entry.getKey()+"="+entry.getValue());
				}
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}
	}
	
	public static String getValueByKey(String key){
		return props.getProperty(key);
	}
	
	public static String getValueByKey(String key,String defaultValue){
		return props.getProperty(key,defaultValue);
	}
	
	

}
