package com.demo.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <p>
 * Title: LogicPolling
 * </p>
 * <p>
 * Description:子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程又循环100，如此循环50次.
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author linyb
 * @date 2017年3月10日
 */
public class LogicPolling {

	private volatile boolean isMainPolling = false;

	ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public void subPolling(int times) {
		lock.lock();
		try {
			// 这边建议用while，如果用if，可能会出现伪唤醒
			while (isMainPolling) {
				try {
					condition.await();
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int i = 1; i <= 10; i++) {
				System.out.println("i am sub ,run i :" + i + "---- already run " + times + "times");
			}
			// 10次跑完后,轮到主线程跑，把等待的主线程唤醒
			isMainPolling = true;

			condition.signal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void mainPolling(int times) {
		// 这边建议用while，如果用if，可能会出现伪唤醒
		lock.lock();
		try {
			while (!isMainPolling) {
				try {
					condition.await();
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int i = 1; i <= 10; i++) {
				System.out.println("i am main ,run i :" + i + "---- already run " + times + "times");
			}
			// 100次跑完后,轮到子线程跑，把等待的子线程唤醒
			isMainPolling = false;

			condition.signal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

}
