package com.xjx.jdktest.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		System.out.println(Runtime.getRuntime().availableProcessors());
		for (int i = 0; i < 10; i++) {
		final int index = i;
		fixedThreadPool.execute(new Runnable() {
		 
		@Override
		public void run() {
		try {
		System.out.println(index);
		Thread.sleep(2000);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		});
		}
		
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			 
			@Override
			public void run() {
			System.out.println("delay 1 seconds, and excute every 3 seconds");
			}
			}, 1, 3, TimeUnit.SECONDS);
	}
}
