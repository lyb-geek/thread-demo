package com.demo.share;

public class DecThread implements Runnable {
	private Count count;

	public DecThread(Count count) {
		super();
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			count.dec();
		}

	}

}
