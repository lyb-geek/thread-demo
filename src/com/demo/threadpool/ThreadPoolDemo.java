package com.demo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(3);
		// ExecutorService service = Executors.newCachedThreadPool();
		// ExecutorService service = Executors.newSingleThreadExecutor();
		for (int i = 1; i <= 10; i++) {
			final int task = i;
			service.execute(new Runnable() {

				@Override
				public void run() {
					for (int j = 1; j <= 10; j++) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + "execute j :" + j + "; runTask:" + task);
					}

				}
			});
		}

		service.shutdown();
		System.out.println("all task already commit");

		ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(3);
		scheduled.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("booming");

			}
		}, 6, 2, TimeUnit.SECONDS);
	}

}
