package com.demo.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * <p>
 * Title:SemaphoreDemo
 * </p>
 * <p>
 * Description: 用信号灯，可以指定数量线程进行并发访问
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author linyb
 * @date 2017年3月12日
 */
public class SemaphoreDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(3);

		for (int i = 1; i <= 10; i++) {
			final int task = i;
			service.execute(new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for (int j = 1; j <= 10; j++) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + "execute j :" + j + "; runTask:" + task
								+ "当前已经支持：" + (3 - semaphore.availablePermits()) + "并发");
					}

					semaphore.release();
					System.out.println(Thread.currentThread().getName() + "调度结束; runTask:" + task + "当前已经支持："
							+ (3 - semaphore.availablePermits()) + "并发");
				}
			});
		}
	}
}
