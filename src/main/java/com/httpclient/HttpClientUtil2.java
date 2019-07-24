package com.httpclient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil2 {
	
	public static JSONObject doGet(String url) {
		String respstr = "";
		try {
			GetMethod postMethod = new GetMethod(url);
			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(postMethod);
			InputStream inputStream = postMethod.getResponseBodyAsStream();  
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
			StringBuffer stringBuffer = new StringBuffer();  
			String str= "";  
			while((str = br.readLine()) != null){  
				stringBuffer .append(str );  
			}
			respstr=stringBuffer.toString();
			
			System.out.println("请求结果："+respstr);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		JSONObject result = JSONObject.parseObject(respstr);
        return result;
    }
	
	public static JSONObject doPost(String url, String params) {
		String respstr = "";
		try {
			if(params != null) {
				System.out.println("请求参数："+params);
				PostMethod postMethod = new PostMethod(url);
				RequestEntity se = new StringRequestEntity(params, "application/json", "UTF-8");
				postMethod.setRequestEntity(se);
				HttpClient httpclient = new HttpClient();
				httpclient.executeMethod(postMethod);
				
				InputStream inputStream = postMethod.getResponseBodyAsStream();  
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
				StringBuffer stringBuffer = new StringBuffer();  
				String str= "";  
				while((str = br.readLine()) != null){  
					stringBuffer .append(str );  
				}
				respstr=stringBuffer.toString();
				
				System.out.println("请求结果："+respstr);
			}else {
				PostMethod d = new PostMethod(url);
				HttpClient httpclient = new HttpClient();
				httpclient.executeMethod(d);
				InputStream inputStream = d.getResponseBodyAsStream();  
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
				StringBuffer stringBuffer = new StringBuffer();  
				String str= "";  
				while((str = br.readLine()) != null){  
					stringBuffer .append(str );  
				}
				respstr=stringBuffer.toString();
				
				System.out.println("请求结果："+respstr);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		JSONObject result = JSONObject.parseObject(respstr);
        return result;
    }
	
	public static JSONObject doPut(String url, String params){
		String respstr = "";
		try {
			System.out.println("请求参数："+params);
			PutMethod postMethod = new PutMethod(url);
			RequestEntity se = new StringRequestEntity(params, "application/json", "UTF-8");
	        postMethod.setRequestEntity(se);
	        HttpClient httpclient = new HttpClient();
	        httpclient.executeMethod(postMethod);
//	        respstr = postMethod.getResponseBodyAsString();
			InputStream inputStream = postMethod.getResponseBodyAsStream();  
		    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
		    StringBuffer stringBuffer = new StringBuffer();  
		    String str= "";  
		    while((str = br.readLine()) != null){  
		    	stringBuffer .append(str );  
		    }
		    respstr=stringBuffer.toString();
	        
	        System.out.println("请求结果："+respstr);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		JSONObject result = JSONObject.parseObject(respstr);
        return result;
    }
	
	public static JSONObject doDelete(String url,String params){
		String respstr = "";
		try {
			
			if(params != null) {
				System.out.println("请求参数："+params);
				DeleteMethodWithBody postMethod = new DeleteMethodWithBody(url);
				RequestEntity se = new StringRequestEntity(params, "application/json", "UTF-8");
				postMethod.setRequestEntity(se);
				HttpClient httpclient = new HttpClient();
				httpclient.executeMethod(postMethod);
//				respstr = postMethod.getResponseBodyAsString();
				
				InputStream inputStream = postMethod.getResponseBodyAsStream();  
			    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
			    StringBuffer stringBuffer = new StringBuffer();  
			    String str= "";  
			    while((str = br.readLine()) != null){  
			    	stringBuffer .append(str );  
			    }
			    respstr=stringBuffer.toString();

//			    System.out.println("请求结果："+respstr);
			}else {
				DeleteMethod d = new DeleteMethod(url);
				HttpClient httpclient = new HttpClient();
				httpclient.executeMethod(d);
				respstr = d.getResponseBodyAsString();
			}
			
	        System.out.println("请求结果："+respstr);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		JSONObject result = JSONObject.parseObject(respstr);
        return result;
    }
	
}
