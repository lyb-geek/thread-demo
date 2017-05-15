package com.demo.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExcutorMain {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
				new MyReject());

		Task task = new Task(1, "task1");

		Task task1 = new Task(2, "task2");

		Task task2 = new Task(3, "task3");

		Task task3 = new Task(4, "task4");

		Task task4 = new Task(5, "task5");

		Task task5 = new Task(6, "task6");

		executor.execute(task);
		executor.execute(task1);
		executor.execute(task2);
		executor.execute(task3);
		executor.execute(task4);
		executor.execute(task5);
		executor.shutdown();
	}

}
