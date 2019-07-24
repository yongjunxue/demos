package com.readurl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import com.httpclient.HttpClientUtil2;

public class Readfile {
	
	public static JSONObject read1(String urlStr) {
		URL url = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer(1024*32);
		try {
			url = new URL(urlStr);
			is = url.openStream();
			isr = new InputStreamReader(is,"utf-8");
			br = new BufferedReader(isr);
			char[] c = new char[512];
			while(br.read(c) != -1) {
				final String ss = new String(c);
				sb.append(ss);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(sb.toString());
		JSONObject j = JSONObject.parseObject(sb.toString());
		return j;
	}
	
	public static JSONObject read2(String urlStr) {
		JSONObject jb = HttpClientUtil2.doGet(urlStr);
		System.out.println(jb.toJSONString());
		return jb;
	}
	
	public static void main(String[] args) {
//		read1("https://mvw-testing.oss-cn-beijing.aliyuncs.com/360/upload/report/190/data/5_02151483379d4a3f8b8874d1c302d242.js");
		read2("https://mvw-testing.oss-cn-beijing.aliyuncs.com/360/upload/report/190/data/5_02151483379d4a3f8b8874d1c302d242.js");
	}
	
}
