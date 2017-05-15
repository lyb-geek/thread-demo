package com.demo.blockingQueue;

public class SubThread implements Runnable {
	private LogicPolling subLogicPolling;

	public SubThread(LogicPolling subLogicPolling) {
		super();
		this.subLogicPolling = subLogicPolling;
	}

	@Override
	public void run() {
		subPoll();
	}

	private void subPoll() {
		for (int i = 1; i <= 50; i++) {
			subLogicPolling.subPolling(i);
		}
	}

}
