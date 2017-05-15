package com.demo.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 
 * <p>
 * Title: LogicPolling
 * </p>
 * <p>
 * Description:子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程又循环100，如此循环50次. 利用ArrayBlockingQueue，当ArrayBlockingQueue队列满的话，调用put方法，此时如果再往队列添加元素，会产生堵塞，直到里面的有元素被取出来，才唤醒
 * 调用take方法，此时如果队列已经没有元素，也会产生堵塞，直到有新元素进来，才会唤醒
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author linyb
 * @date 2017年3月10日
 */
public class LogicPolling {

	private ArrayBlockingQueue<Integer> subBlockingQueue = new ArrayBlockingQueue<>(1);

	private ArrayBlockingQueue<Integer> mainBlockingQueue = new ArrayBlockingQueue<>(1);
	// {}表示匿名构造方法，他会比构造方法更早执行，如果用static，一个类只能执行多次，而用匿名构造方法，有几个对象就产生几个
	{
		try {
			mainBlockingQueue.put(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void subPolling(int times) {
		// 如果队列满了，就堵塞
		try {
			subBlockingQueue.put(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 1; i <= 10; i++) {
			System.out.println("i am sub ,run i :" + i + "---- already run " + times + "times");
		}
		// 10次跑完后,轮到主线程跑，把等待的主线程唤醒
		try {
			mainBlockingQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void mainPolling(int times) {
		try {
			mainBlockingQueue.put(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 1; i <= 10; i++) {
			System.out.println("i am main ,run i :" + i + "---- already run " + times + "times");
		}
		// 100次跑完后,轮到子线程跑，把等待的子线程唤醒
		try {
			subBlockingQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
