package com.demo.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();

		Future<String> future = service.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(100);
				return "hello";
			}

		});

		System.out.println("等待结果");

		try {
			System.out.println("结果出来：" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExecutorService executor = Executors.newFixedThreadPool(3);
		CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

		for (int i = 0; i < 5; i++) {
			final int seq = i;
			completionService.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return Thread.currentThread().getPriority() + "-" + Thread.currentThread().getName() + "-" + seq;
				}
			});
		}

		for (int i = 0; i < 5; i++) {
			try {
				Future<String> future2 = completionService.take();

				System.out.println("结果已经出来：" + future2.get());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
