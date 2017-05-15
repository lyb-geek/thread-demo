package com.demo.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * <p>
 * Title:MyReject
 * </p>
 * <p>
 * Description: 自定义线程拒绝测量
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author linyb
 * @date 2017年4月14日
 */
public class MyReject implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("当前线程池已经占满了，无法再接受新线程ID：" + new Thread(r).getId());

	}

}
