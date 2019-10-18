package com.DTproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



public class MyEnhancer {
	public static void main(String[] args) {
		new MyEnhancer().testSon1();
//		new MyEnhancer().testSon2();
	}
	
	/**
	 * 异常了            TODO
	 */
	void testSon1() {
		Enhancer en = new Enhancer();
		en.setSuperclass(Son1.class);
		en.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("aa");
//				Object o = proxy.invoke(obj, args);
//				Object o = proxy.invokeSuper(obj, args);
//				Object o = method.invoke(proxy, args);
				System.out.println("bb");
				return "cc";
			}
		});
		Son1 s = (Son1) en.create();
		System.out.println(s.test());
	}
	
	/**
	 * 正常
	 */
	void testSon2() {
		Enhancer en = new Enhancer();
		en.setSuperclass(Son2.class);
		en.setCallback(new MethodInterceptor() {
//			@Override
//			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//				System.out.println("aa");
//				Object o = proxy.invokeSuper(obj, args);
////				Object o = method.invoke(proxy, args);
//				System.out.println("bb");
//				return o;
//			}
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("aa");
				Object o = proxy.invokeSuper(obj, args);
//				Object o = method.invoke(proxy, args);
				System.out.println("bb");
				return o;
			}
		});
		Son2 s = (Son2) en.create();
		System.out.println(s.test());
	}
}

interface Son1{
	String test();
}
class Son2{
	String test() {
		System.out.println("11");
		return "22";
	}
}