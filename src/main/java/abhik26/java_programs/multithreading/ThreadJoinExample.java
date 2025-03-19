package abhik26.java_programs.multithreading;

public class ThreadJoinExample {

	public static void main(String[] args) throws InterruptedException {
		Runnable r = () -> {
			long millis = 5000;
			System.out.println(
					String.format("Putting the current thread with name: %s to sleep for %d milliseconds.", 
							Thread.currentThread().getName(), millis));
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(String.format("Thread with name: %s has come out of sleep.", Thread.currentThread().getName()));
		};
		
		Thread t = new Thread(r, "Child Thread");
		
		t.start();
		// always do thread join after the start method is invoked, other-wise it will not work.
		// t.join(); 
		t.join(1000);
		
		System.out.println(String.format("Executing in thread: %s", Thread.currentThread().getName()));
		
	}

}
