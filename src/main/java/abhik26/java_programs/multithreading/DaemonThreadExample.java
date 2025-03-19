package abhik26.java_programs.multithreading;

public class DaemonThreadExample {
	
	public static void main(String[] args) {
		Runnable r = () -> {
			System.out.println("Putting the thread to sleep having name: " + Thread.currentThread().getName());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("The thread came out of sleep having name: " + Thread.currentThread().getName());
		};

		Thread t = new Thread(r, "Daemon Thread");
		
		t.setDaemon(true); // cannot set a thread daemon after start method is invokes, will get IllegalThreadStateException
		t.start();
		
		System.out.println("Finishing task of main thread.");
	}
	
}
