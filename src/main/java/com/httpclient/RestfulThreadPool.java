package com.httpclient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RestfulThreadPool {
	public static void test1() {
		ExecutorService es = Executors.newFixedThreadPool(2);
		es.execute(new Thread(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("1");
			HttpClientUtil2.doGet("http://192.168.8.109:8082/");
		}));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		es.execute(new Thread(() -> {
			System.out.println("2");
			HttpClientUtil2.doGet("http://192.168.8.109:8082/abc");
		}));
	}
	
	public static void main(String[] args) {
//		test1();
//		test2();
		new RestfulThreadPool().test3();
	}
	
	public static class MyRejectedHandler implements RejectedExecutionHandler {
		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//			System.out.println("------------------sdf------------------");
			throw new RuntimeException("sdfsdfsdfsd");
		}
	}
	
	public static void test2() {
//		ThreadPoolExecutor pool = new ThreadPoolExecutor(1,1,2,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1),new MyRejectedHandler());
		ThreadPoolExecutor pool = new ThreadPoolExecutor(1,11,1,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1),new MyRejectedHandler());
		for(int i=1;i<=10;i++) {
			pool.execute(new Thread(() -> {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getId());
			}));
		}
		pool.execute(new Thread(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("1");
			HttpClientUtil2.doGet("http://192.168.8.109:8082/abc");
		}));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.execute(new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("2");
			HttpClientUtil2.doGet("http://192.168.8.109:8082/abc");
		}));
	}
	
	
	Object lock = new Object();
	public void test3() {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(1,1,1,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1),new MyRejectedHandler());
		for(int i=1;i<=1;i++) {
			final Map<String,Object> map = new HashMap<>();
			Future<Map<String,Object>> f = pool.submit(new Thread(() -> {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				map.put("TId", Thread.currentThread().getId());
				map.put("TName", Thread.currentThread().getName());
			}),map);
			try {
				System.out.println(map+"------"+f.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
}
