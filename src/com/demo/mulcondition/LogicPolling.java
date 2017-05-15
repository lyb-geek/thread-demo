package com.demo.mulcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <p>
 * Title: LogicPolling
 * </p>
 * <p>
 * Description:子线程一循环10次，接着子线程二循环20，随后主线程又循环100，接着又子线程一循环10次，接着子线程二循环20，随后主线程又循环100，如此循环50次.
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author linyb
 * @date 2017年3月10日
 */
public class LogicPolling {

	private volatile int isMainPolling = 1;

	ReentrantLock lock = new ReentrantLock();
	Condition conditionSubOne = lock.newCondition();
	Condition conditionSubTwo = lock.newCondition();
	Condition conditionMain = lock.newCondition();

	public void subOnePolling(int times) {
		lock.lock();
		try {
			// 这边建议用while，如果用if，可能会出现伪唤醒
			while (isMainPolling != 1) {
				try {
					conditionSubOne.await();
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int i = 1; i <= 10; i++) {
				System.out.println("i am subOne ,run i :" + i + "---- already run " + times + "times");
			}

			isMainPolling = 2;

			conditionSubTwo.signal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void subTwoPolling(int times) {
		lock.lock();
		try {
			// 这边建议用while，如果用if，可能会出现伪唤醒
			while (isMainPolling != 2) {
				try {
					conditionSubTwo.await();
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int i = 1; i <= 20; i++) {
				System.out.println("i am subTwo ,run i :" + i + "---- already run " + times + "times");
			}
			// 10次跑完后,轮到主线程跑，把等待的主线程唤醒
			isMainPolling = 0;

			conditionMain.signal();
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
			while (isMainPolling != 0) {
				try {
					conditionMain.await();
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
			isMainPolling = 1;

			conditionSubOne.signal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

}
