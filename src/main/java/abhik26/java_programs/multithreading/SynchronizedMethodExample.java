package abhik26.java_programs.multithreading;

public class SynchronizedMethodExample {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedMethodClass obj = new SynchronizedMethodClass();        

        Thread t1 = new Thread(() -> {
            try {
                obj.synchronizedMethod();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            obj.unSynchronizedMethod();
        });

        t2.start();
        
    }

    private static class SynchronizedMethodClass {
        private static String preString = "\n\n----------->";

        public synchronized void synchronizedMethod() throws Exception {
            Thread.sleep(5000);
            System.out.println(preString + "Inside synchronizedMethod");
        }

        public void unSynchronizedMethod() {
            System.out.println(preString + "inside unSynchronizedMethod");
        }
    }
}
