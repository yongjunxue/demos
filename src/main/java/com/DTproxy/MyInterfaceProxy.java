package com.DTproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInterfaceProxy {
	public static void main(String[] args) {
		new MyInterfaceProxy().test1();
//		new MyInterfaceProxy().test2();
//		new MyInterfaceProxy().test3();
	}
	
	public void test1() {
		IUser u = (IUser) Proxy.newProxyInstance(IUser.class.getClassLoader(), 
				new Class[] {IUser.class}, 
//				IUser.class.getInterfaces(), 
				(proxy,method,args2) -> {
					/*不能代理实现类
					 * if(Object.class.equals(method.getDeclaringClass())) {
						return method.invoke(proxy, args2);
					}*/
					if(method.getName().equals("test")) {
						System.out.println("test---test");
					}
					return "返回值:test1";
		});
		System.out.println(u.test());
	}
	
	public void test2() {
//		IUser u = (IUser) Proxy.newProxyInstance(IUser.class.getClassLoader(), 
//				new Class[] {IUser.class}, 
////				IUser.class, 
//				new InvocationHandler() {
//					@Override
//					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//						return method.invoke(proxy, args);
//					}
//			
//			});
//		u.test();
	}
	
	/**
	 * jdk动态代理不能代理实现类
	 */
	public void test3() {
		User3 u = (User3) Proxy.newProxyInstance(IUser.class.getClassLoader(), 
				new Class[] {User3.class}, 
				(proxy,method,args2) -> {
					if(Object.class.equals(method.getDeclaringClass())) {
						return method.invoke(proxy, args2);
					}
					if(method.getName().equals("test")) {
						System.out.println("test---test");
					}
					return null;
		});
		u.test();
	}
}
interface IUser{
	String test();
}
interface IUser2{
	String test();
}
class User3{
	String test() {
		System.out.println("abc");
		return "user3";
	}
}
