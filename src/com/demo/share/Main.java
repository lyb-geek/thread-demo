package com.demo.share;

public class Main {
	public static void main(String[] args) {
		final Count count = new Count();
		for (int i = 0; i < 2; i++) {
			new Thread(new IncThread(count)).start();
		}

		for (int i = 0; i < 2; i++) {
			new Thread(new DecThread(count)).start();
		}

	}
}
