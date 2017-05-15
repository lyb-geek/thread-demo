package com.demo.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * <p>
 * Title:ExchangerDemo
 * </p>
 * <p>
 * Description: 用于两个线程间数据的交换
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author linyb
 * @date 2017年3月13日
 */
public class ExchangerDemo {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		Exchanger<String> exchanger = new Exchanger<>();

		service.execute(new Runnable() {

			@Override
			public void run() {
				String name = "zhangsan";

				try {
					System.out.println(Thread.currentThread().getName() + ":交换前的name：" + name);
					String exchangeName = exchanger.exchange(name);
					System.out.println(Thread.currentThread().getName() + ":交换后的name：" + exchangeName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		service.execute(new Runnable() {

			@Override
			public void run() {
				String name = "lisi";

				try {
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() + ":交换前的name：" + name);
					String exchangeName = exchanger.exchange(name);
					System.out.println(Thread.currentThread().getName() + ":交换后的name：" + exchangeName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		service.shutdown();
	}

}
