package abhik26.java_programs.multithreading;

public class PrintMultiplesUsingDifferentThreads {

	public static void main(String[] args) {
		int[] nums = { 2, 3, 5 };
		Printer printer = new Printer();

		for (int num : nums) {
			Thread t = new Thread(new MyTask(num, 10, printer), "Thread-" + num + "x");
			t.start();
		}
	}

	private static class Printer {

		private int currentNum = 2;

		public synchronized void print(int num, int multipleCount) throws InterruptedException {
			if (currentNum != num) {
				wait();
			}
			
			System.out.println(String.format("Thread: %s, print: %d", Thread.currentThread().getName(), (num * multipleCount)));

			switch (currentNum) {
				case 2:
					currentNum = 3;
					break;
				case 3:
					currentNum = 5;
					break;
				case 5:
					currentNum = 2;
					break;
			}

			notify();
		}
	}

	private static class MyTask implements Runnable {

		int num;
		int multiplesCount;
		Printer printer;

		MyTask(int num, int multiplesCount, Printer printer) {
			this.num = num;
			this.multiplesCount = multiplesCount;
			this.printer = printer;
		}

		@Override
		public void run() {
			for (int i = 1; i <= multiplesCount; i++) {
				try {
					printer.print(num, i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
