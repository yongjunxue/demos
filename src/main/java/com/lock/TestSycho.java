package com.lock;

public class TestSycho {
	public static void test1() {
		synchronized (new String("abc").intern()) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("111");
		}
	}
	public static void test2() {
		synchronized ("abc") {
			System.out.println("222");
		}
	}
	public static void main(String[] args) {
		new Thread( () -> {
			test1();
		}).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread( () -> {
			test2();
		}).start();
	
			
	}
}
