package com.demo.lock;

/**
 * 
 * <p>
 * Title:WriteReadLockDemo
 * </p>
 * <p>
 * Description: 读写锁，适用于并发读多写少场景，口诀：读读共享，读写互斥，写写互斥
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author linyb
 * @date 2017年4月14日
 */
public class WriteReadLockDemo {
	public static void main(String[] args) {
		final WriteReadTask task = new WriteReadTask();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				task.read();
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				task.read();
			}
		}, "t2");

		Thread t3 = new Thread(new Runnable() {
			public void run() {
				task.write();
			}
		}, "t3");

		Thread t4 = new Thread(new Runnable() {
			public void run() {
				task.write();
			}
		}, "t4");
		// 读读共享
		// t1.start();
		// t2.start();

		// 读写互斥
		// t1.start();
		// t3.start();

		// 写写互斥
		t3.start();
		t4.start();
	}

}
