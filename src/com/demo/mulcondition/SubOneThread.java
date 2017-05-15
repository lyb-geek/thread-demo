package com.demo.mulcondition;

public class SubOneThread implements Runnable {
	private LogicPolling subLogicPolling;

	public SubOneThread(LogicPolling subLogicPolling) {
		super();
		this.subLogicPolling = subLogicPolling;
	}

	@Override
	public void run() {
		subPoll();
	}

	private void subPoll() {
		for (int i = 1; i <= 50; i++) {
			subLogicPolling.subOnePolling(i);
		}
	}

}
