package com.demo.threadpool;

public class Task implements Runnable {
	private int id;
	private String taskName;

	public Task(int id, String taskName) {
		super();
		this.id = id;
		this.taskName = taskName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "|" + this.id + " |" + this.taskName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
