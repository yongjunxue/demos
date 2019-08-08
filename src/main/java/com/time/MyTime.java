package com.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.locks.LockSupport;

import com.date.DateUtils;

public class MyTime {
	public static void main(String[] args) {
		System.out.println(System.nanoTime());
		System.out.println(System.nanoTime());
		System.out.println(System.nanoTime());
		
		LockSupport.unpark(Thread.currentThread());
		System.out.println("a");
		LockSupport.park();
		System.out.println("b");
		
		
		System.out.println(DateUtils.stringToDate("2019-08-02 09:55:00", DateUtils.DATE_PATTERN));
		Date d2 = DateUtils.stringToDate("109-08-02 09:55:00", DateUtils.DATE_PATTERN);
		System.out.println(d2);
		System.out.println(d2.getTime());
		System.out.println(new Date(-58709174400000L));
		
		Date d3 = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		f.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		f2.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		
		SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(f3.getTimeZone());
		
		System.out.println(f.format(d3));
		System.out.println(f2.format(d3));
		System.out.println(f3.format(d3));
	}
}
