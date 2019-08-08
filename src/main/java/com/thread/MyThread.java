package com.thread;

import java.util.concurrent.locks.LockSupport;

public class MyThread {
	public static void main(String[] args) {
//		LockSupport.park();
//		LockSupport.parkNanos(20000000000L);
		LockSupport.parkNanos(Thread.currentThread(),20000000000L);
		System.out.println("aab");
	}
}
