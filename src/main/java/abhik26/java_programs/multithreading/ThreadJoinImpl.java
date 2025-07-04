package abhik26.java_programs.multithreading;

public class ThreadJoinImpl {
	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(() -> {
			for (int i = 1; i <= 9; i++) {
				System.out.println("Thread t1: " + i);
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 100; i <= 109; i++) {
				System.out.println("Thread t2: " + i);
			}
		});

		Thread t3 = new Thread(() -> {
			for (int i = 1000; i <= 1009; i++) {
				System.out.println("Thread t2: " + i);
			}
		});

		t1.start();
		t1.join();
		t2.start();
		// t2.join();
		t3.start();
	}
}