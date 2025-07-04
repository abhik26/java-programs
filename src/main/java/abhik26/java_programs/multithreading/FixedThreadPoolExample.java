package abhik26.java_programs.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FixedThreadPoolExample {

	public static void main(String[] args) {
		int poolSize = 3;

		ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
		ThreadPoolExecutor tpExecutor = (ThreadPoolExecutor) executorService;

		System.out.println("before active count: " + tpExecutor.getActiveCount());

		for (int i = 1; i < 10; i++) {
			final int temp = i;

			executorService.execute(() -> {
				System.out.println("Executing task number: " + temp);
			});
		}

		System.out.println("active count: " + tpExecutor.getActiveCount());
		System.out.println("largest pool size: " + tpExecutor.getLargestPoolSize());
		System.out.println("task count: " + tpExecutor.getTaskCount());
	}

}
