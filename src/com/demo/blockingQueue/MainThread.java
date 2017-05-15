package com.demo.blockingQueue;

public class MainThread {
	public static void main(String[] args) {
		final LogicPolling logicPolling = new LogicPolling();
		SubThread subThread = new SubThread(logicPolling);
		Thread thread = new Thread(subThread);
		thread.start();

		for (int i = 1; i <= 50; i++) {
			logicPolling.mainPolling(i);
		}
	}

}
