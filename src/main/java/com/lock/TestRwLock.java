package com.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestRwLock {
	
	public volatile int a = 0;
	
	public void test1() {
		Long s = System.nanoTime();
		ReadWriteLock rw = new ReentrantReadWriteLock();
		Lock r = rw.readLock();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int j=1;j<=10000000;j++) {
					a++;
				}
			}
		}).start();
		for(int i=1;i<=10;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j=1;j<=10000000;j++) {
						int r = a;
					}
				}
			}).start();
		}
		System.out.println(System.nanoTime()-s);
	}
	
	/**
	 * 一个线程修改对象，一个线程读取对象。不加锁，读线程只读了一半
	 */
	public void test2() {
		UserTest u = new UserTest();
		new Thread(new Runnable() {
			@Override
			public void run() {
				u.age=10;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				u.name="王五";
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("age:"+u.age);
				System.out.println("name:"+u.name);
			}
		}).start();
	}
	
	public void test3() {
		ReadWriteLock rw = new ReentrantReadWriteLock();
		rw.readLock().lock();
		System.out.println("a");
		rw.writeLock().lock();
		System.out.println("b");
	}
	
	public static void main(String[] args) {
//		new TestRwLock().test2();
		new TestRwLock().test3();
	}
	
	class UserTest{
		public int age;
		public String name;
	}
}
