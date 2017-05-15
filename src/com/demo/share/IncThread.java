package com.demo.share;

public class IncThread implements Runnable {
	private Count count;

	public IncThread(Count count) {
		super();
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			count.inc();
		}

	}

}
