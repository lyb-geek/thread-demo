package com.demo.share;

/**
 * 
 * <p>
 * Title: Count
 * </p>
 * <p>
 * Description: 设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1。写出程序。
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author linyb
 * @date 2017年3月10日
 */
public class Count {
	private int j;

	public synchronized void inc() {
		j++;
		System.out.println(Thread.currentThread().getName() + "inc : j=" + j);
	}

	public synchronized void dec() {
		j--;
		System.out.println(Thread.currentThread().getName() + "dec : j=" + j);
	}

}
