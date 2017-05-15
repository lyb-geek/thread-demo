package com.demo.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	public static void main(String[] args) {
		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("当前线程：" + Thread.currentThread().getName() + "进入");
				try {
					Thread.sleep(1000);
					System.out.println("当前线程：" + Thread.currentThread().getName() + "退出");
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("当前线程：" + Thread.currentThread().getName() + "进入");
				try {
					countDownLatch.await();
					System.out.println("当前线程：" + Thread.currentThread().getName() + "退出");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, "t2");

		t1.start();

		t2.start();
	}

}
