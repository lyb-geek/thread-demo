package com.demo.mulcondition;

public class MainThread {
	public static void main(String[] args) {
		final LogicPolling logicPolling = new LogicPolling();
		SubOneThread subThread = new SubOneThread(logicPolling);
		Thread thread = new Thread(subThread);
		thread.start();

		SubTwoThread subTwoThread = new SubTwoThread(logicPolling);
		Thread threadTwo = new Thread(subTwoThread);
		threadTwo.start();

		for (int i = 1; i <= 50; i++) {
			logicPolling.mainPolling(i);
		}
	}

}
