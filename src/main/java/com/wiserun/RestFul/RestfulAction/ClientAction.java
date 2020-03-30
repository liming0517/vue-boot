package com.wiserun.RestFul.RestfulAction;

import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class ClientAction {

	private static final Logger logger = LoggerFactory.getLogger(ClientAction.class);

	public JSONObject ClientPost(JSONObject parm) {
		HttpClient httpClient = HttpClients.createDefault();
        //HttpGet request = new HttpGet(apiURL);
		String apiURL=parm.getString("apiURL");
		HttpPost request =new HttpPost(apiURL);
        HttpResponse execute;
        String result="";
        JSONObject jsonObject=null;
        // 浏览器表示
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0");
        // 传输的类型 不正确时会报错
        request.addHeader("Content-Type", "application/json;charset=utf-8");
        request.addHeader("X-TOKEN",parm.getString("token"));
		try {
	        // 创建请求参数
	        JSONObject json = new JSONObject();
	        //========循环自动加入开始=====
	        for(Map.Entry<String,Object> entry:parm.entrySet()) {
	        	logger.info("ClientAction.ClientPost.param.key==>"+entry.getKey()+"#param.value==>"+(String) entry.getValue());
	        	 json.put(entry.getKey(), (String) entry.getValue());
	        }
	        //========循环自动加入结束=====
	        StringEntity entityParam = new StringEntity(json.toJSONString(),"utf-8");
	        request.setEntity(entityParam);
	        //执行请求
			execute = httpClient.execute(request);
			HttpEntity entity = execute.getEntity();
			logger.info("ClientAction.ClientPost.entity==>"+entity);
	        result=EntityUtils.toString(entity, "GBK");
	        logger.info("ClientAction.ClientPost.result==>"+result);
	        jsonObject = JSON.parseObject(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return result;
		return jsonObject;
	}
		
	public JSONObject ClientLoginPost(JSONObject parm) {
		HttpClient httpClient = HttpClients.createDefault();
        //HttpGet request = new HttpGet(apiURL);
		String apiURL=parm.getString("apiURL");
		HttpPost request =new HttpPost(apiURL);
        HttpResponse execute;
        String result="";
        JSONObject jsonObject=null;
        // 浏览器表示
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0");
        // 传输的类型 不正确时会报错
        //request.addHeader("Content-Type", "application/json;charset=utf-8");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        //request.addHeader("X-TOKEN",parm.getString("token"));
		try {
	        // 创建请求参数
	        JSONObject json = new JSONObject();
	        //========循环自动加入开始=====
	        for(Map.Entry<String,Object> entry:parm.entrySet()) {
	        	logger.info("ClientAction.ClientPost.param.key==>"+entry.getKey()+"#param.value==>"+(String) entry.getValue());
	        	 json.put(entry.getKey(), (String) entry.getValue());
	        }
	        //========循环自动加入结束=====
	        StringEntity entityParam = new StringEntity(json.toJSONString(),"utf-8");
	        request.setEntity(entityParam);
	        //执行请求
			execute = httpClient.execute(request);
			HttpEntity entity = execute.getEntity();
			logger.info("ClientAction.ClientPost.entity==>"+entity);
	        result=EntityUtils.toString(entity, "GBK");
	        logger.info("ClientAction.ClientPost.result==>"+result);
	        jsonObject = JSON.parseObject(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return result;
		return jsonObject;
	}
}
