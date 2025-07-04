package abhik26.java_programs.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerExample {
	public static void main(String[] args) throws Exception {
		Queue<Integer> dataQueue = new LinkedList<Integer>();
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 1; i <= 3; i++) {
			executorService.execute(new Producer(dataQueue, i * 2, 1000));
		}

		for (int i = 1; i <= 2; i++) {
			executorService.execute(new Consumer(dataQueue));
		}

		executorService.shutdown();

		while (!executorService.isTerminated()) {
		}

		System.out.println("Program ended.");
	}

	private static class Producer implements Runnable {
		private final Queue<Integer> dataQueue;
		private final int datasToProduce;
		private final int waitTime;

		Producer(Queue<Integer> datas, int datasToProduce, int waitTime) {
			this.dataQueue = datas;
			this.datasToProduce = datasToProduce;
			this.waitTime = waitTime;
		}

		public void run() {
			try {
				Random random = new Random();
				for (int i = 1; i <= datasToProduce; i++) {
					int num = random.nextInt(100);
					synchronized (dataQueue) {
						int queueSize = dataQueue.size();
						dataQueue.add(num);
						System.out.println(Thread.currentThread().getName() + " - Produced: " + num);

						if (queueSize == 0) {
							dataQueue.notifyAll();
							System.out.println(Thread.currentThread().getName() + " - Notified all threads...");
						}
					}

					Thread.sleep(waitTime);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static class Consumer implements Runnable {
		private final Queue<Integer> dataQueue;

		Consumer(Queue<Integer> datas) {
			this.dataQueue = datas;
		}

		public void run() {
			try {
				while (true) {
					synchronized (dataQueue) {
						if (dataQueue.size() == 0) {
							System.out.println(Thread.currentThread().getName() + " - waiting for the data...");
							dataQueue.wait();
						}
						Integer num = dataQueue.poll();

						if (num == null) {
							break;
						}

						System.out.println(Thread.currentThread().getName() + " - Consumed: " + num);
					}
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getName() + " - Exception occurred.\n");
				e.printStackTrace();
			}
		}
	}
}
