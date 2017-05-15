package com.demo.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CylicBarrierTest {
	public static void main(String[] args) {
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("当前线程：" + Thread.currentThread().getName() + "已经到达集合地点1");
				try {
					cyclicBarrier.await();
					System.out.println("当前线程：" + Thread.currentThread().getName() + "已经到达");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(1000);
					System.out.println("当前线程：" + Thread.currentThread().getName() + "已经到达集合地点1");
					cyclicBarrier.await();
					System.out.println("当前线程：" + Thread.currentThread().getName() + "已经到达");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, "t2");

		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(1500);
					System.out.println("当前线程：" + Thread.currentThread().getName() + "已经到达集合地点1");
					cyclicBarrier.await();
					System.out.println("当前线程：" + Thread.currentThread().getName() + "已经到达");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, "t3");

		t1.start();
		t2.start();
		t3.start();
	}

}
