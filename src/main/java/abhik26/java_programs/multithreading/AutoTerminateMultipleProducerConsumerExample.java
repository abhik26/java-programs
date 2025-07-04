package abhik26.java_programs.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AutoTerminateMultipleProducerConsumerExample {

	public static void main(String[] args) throws InterruptedException {
		Data data = new Data();
		ExecutorService executorService = Executors.newFixedThreadPool(6);

		for (int i = 1; i <= 3; i++) {
			executorService.execute(new Producer("Producer" + i, data, 2 * i, 1000));
			executorService.execute(new Consumer("Consumer" + i, data));

		}

		long startTime = System.currentTimeMillis();
		
		executorService.shutdown();
		executorService.awaitTermination(1000L, TimeUnit.SECONDS);
		
		long endTime = System.currentTimeMillis();

		System.out.println("Program Ended... after waiting for " + (endTime - startTime) + " milliseconds");

	}

	private static class Data {
		int producerCount;
		Queue<Integer> queue = new LinkedList<Integer>();
	}

	private static class Producer implements Runnable {
		private final String name;
		private final Data data;
		private final int dataToProduce;
		private final long waitTime;

		Producer(String name, Data data, int dataToProduce, long waitTime) {
			this.name = name;
			this.data = data;
			this.dataToProduce = dataToProduce;
			this.waitTime = waitTime;
			++this.data.producerCount;
		}

		@Override
		public void run() {
			try {
				Random random = new Random();
				for (int i = 1; i <= dataToProduce; i++) {
					int num = random.nextInt(100);
					synchronized (data) {
						int queueSize = data.queue.size();
						data.queue.add(num);
						System.out.println(String.format("'%s' produced num: %d", this.name, num));

						if (queueSize == 0) {
							data.notifyAll();
							System.out.println(String.format("'%s' notified all thread...", this.name));
						}
					}

					if (i < dataToProduce && random.nextBoolean()) {
						System.out.println(String.format("'%s' going to sleep...", this.name));
						Thread.sleep(waitTime);
					}
				}

				--this.data.producerCount;
				System.out.println(String.format("'%s' stopped producing. Now the producer count is: %d", this.name,
						this.data.producerCount));

			} catch (Exception e) {
				System.out
						.println(String.format("Exception occurred in '%s'. Exception message: %s", this.name,
								e.getMessage()));
			}
		}

	}

	private static class Consumer implements Runnable {
		private String name;
		private Data data;

		Consumer(String name, Data data) {
			this.name = name;
			this.data = data;
		}

		@Override
		public void run() {
			try {
				while (true) {
					synchronized (data) {
						int queueSize = data.queue.size();

						if (queueSize > 0) {
							int num = data.queue.poll();
							System.out.println(String.format("'%s' consumed num: %d", this.name, num));
						} else if (data.producerCount <= 0) {
							System.out.println(String.format("'%s' is stopping...", this.name));
							// data.notifyAll();
							break;
						} else {
							System.out.println(String.format("'%s' is waiting for data...", this.name));
							data.wait();
						}
					}

					System.out.println(String.format("'%s' going to sleep...", this.name));
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				System.out.println(String.format("Exception occurred in '%s'. Exception message: %s",
						this.name, e.getMessage()));
			}
		}

	}
}
