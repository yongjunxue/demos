package com.jfreechart.demo;

import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.Random;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;

public class Test {
	
	public volatile double cost=0;
	public volatile LinkedList<Double> ll = new LinkedList<>();
	public volatile Random r = new Random();
	
	public void setCost() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					synchronized (Test.class) {
						ll.add((double) (r.nextInt(5)+1));
						if(ll.size()>5) {
							ll.removeFirst();
						}
					}
					try {
						Thread.currentThread().sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					synchronized (Test.class) {
						int i=0;
						double total=0;
						if(!ll.isEmpty()) {
							for(double cost : ll) {
								total+=cost;
								i++;
							}
							cost = total/i;
							cost = (double)Math.round(cost*10)/10;
						}
						for(double cost : ll) {
							System.out.print(cost+",");
						}
						System.out.println("平均耗时："+cost);
					}
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t2.start();
	}
	
	public static void main(String[] args) {
//		new Test().setCost();
		
	}
}
