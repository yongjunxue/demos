package com.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class TestDoubleCheckLock {
	
	private static TestDoubleCheckLock ins = null;
	
	private User u;
	
	private static int i=0;
	
	private TestDoubleCheckLock() {
		u = new User();
	}
	
	public static TestDoubleCheckLock getIns() {
		if(ins == null) {
			synchronized (TestDoubleCheckLock.class) {
				if(ins == null) {
					ins = new TestDoubleCheckLock();
				}
			}
		}
		return ins;
	}
	
	public static void test() {
		int tn = 10;
		CountDownLatch cd = new CountDownLatch(tn);
		CyclicBarrier cb = new CyclicBarrier(tn);
		for(int i=1;i<=tn;i++) {
			new Thread(() ->{
				try {
					cb.await();
					TestDoubleCheckLock l = getIns();
					l.i++;
					if(l == null) {
						System.out.println(Thread.currentThread().getId()+":l=null");
						return;
					}
					if(l.u == null) {
						System.out.println(Thread.currentThread().getId()+":u=null");return;
					}
					if(l.u.f1 == null) {
						System.out.println(Thread.currentThread().getId()+":f1=null");return;
					}
					if(l.u.f2 == null) {
						System.out.println(Thread.currentThread().getId()+":f2=null");return;
					}
					if(l.u.f3 == null) {
						System.out.println(Thread.currentThread().getId()+":f3=null");return;
					}
					if(l.u.f4 == null) {
						System.out.println(Thread.currentThread().getId()+":f4=null");return;
					}
					if(l.u.f5 == null) {
						System.out.println(Thread.currentThread().getId()+":f5=null");return;
					}
					if(l.u.f6 == null) {
						System.out.println(Thread.currentThread().getId()+":f6=null");return;
					}
					if(l.u.f7 == null) {
						System.out.println(Thread.currentThread().getId()+":f7=null");return;
					}
					if(l.u.f8 == null) {
						System.out.println(Thread.currentThread().getId()+":f8=null");return;
					}
					cd.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			} ).start();
		}
		try {
			cd.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(TestDoubleCheckLock.i);
	}
	
	public static void main(String[] args) {
		TestDoubleCheckLock.test();
	}
	
	class User{
		public String f1;
		public String f2;
		public String f3;
		public String f4;
		public String f5;
		public String f6;
		public String f7;
		public String f8;
		
		public User() {
			f1="f1";
			f2="f2";
			f3="f3";
			f4="f4";
			f5="f5";
			f6="f6";
			f7="f7";
			f8="f8";
		}
	}
}
