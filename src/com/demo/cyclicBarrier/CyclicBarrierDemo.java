package com.demo.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * <p>
 * Title:CyclicBarrierDemo
 * </p>
 * <p>
 * Description: 栅栏的作用是让所有线程一起到到达同一个地点，再接下去执行下一步动作，先到的线程，先等待
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author linyb
 * @date 2017年3月13日
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		CyclicBarrier barrier = new CyclicBarrier(3);

		for (int i = 1; i <= 3; i++) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(new Random().nextInt(2000));
						System.out.println(Thread.currentThread().getName() + "已经到达集合地点1"
								+ (barrier.getNumberWaiting() == 2 ? "所有人都已经到达，继续向前走" : "正在等候"));

						barrier.await();

						Thread.sleep(new Random().nextInt(2000));
						System.out.println(Thread.currentThread().getName() + "已经到达集合地点2"
								+ (barrier.getNumberWaiting() == 2 ? "所有人都已经到达，继续向前走" : "正在等候"));

						barrier.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		}

		service.shutdown();
	}

}
