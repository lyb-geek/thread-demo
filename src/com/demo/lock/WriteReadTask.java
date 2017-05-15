package com.demo.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class WriteReadTask {
	private ReentrantReadWriteLock rWriteLock = new ReentrantReadWriteLock();

	private ReadLock readLock = rWriteLock.readLock();

	private WriteLock writeLock = rWriteLock.writeLock();

	public void read() {
		try {
			readLock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "开始进行读操作");
			Thread.sleep(1000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "结束读操作");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
	}

	public void write() {
		try {
			writeLock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "开始进行写操作");
			Thread.sleep(1000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "结束写操作");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}

}
