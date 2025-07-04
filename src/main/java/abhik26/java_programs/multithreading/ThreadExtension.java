package abhik26.java_programs.multithreading;

public class ThreadExtension {
	public static void main(String[] args) {
		new NewThread();
	}
}

class NewThread extends Thread {
	Thread t1;
	Thread t2;

	NewThread() {
		t1 = new Thread(this, "Thread_1");
		t2 = new Thread(this, "Thread_1");
		t1.start();
		t2.start();
	}

	public void run() {
		t2.setPriority(Thread.MAX_PRIORITY);
		System.out.println(t1.equals(t2));
	}
}