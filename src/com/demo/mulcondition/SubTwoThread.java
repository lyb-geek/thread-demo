package com.demo.mulcondition;

public class SubTwoThread implements Runnable {
	private LogicPolling subLogicPolling;

	public SubTwoThread(LogicPolling subLogicPolling) {
		super();
		this.subLogicPolling = subLogicPolling;
	}

	@Override
	public void run() {
		subPoll();
	}

	private void subPoll() {
		for (int i = 1; i <= 50; i++) {
			subLogicPolling.subTwoPolling(i);
		}
	}

}
