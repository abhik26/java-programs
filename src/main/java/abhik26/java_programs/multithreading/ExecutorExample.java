package abhik26.java_programs.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            executorService.execute(new MyTask(i));
        }

        executorService.shutdown();
        
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // while(!executorService.isTerminated()) {}

        if (executorService.isTerminated()) {
            System.out.println("All taks completed by executor service and is terminated!");
        }
    }

    private static class MyTask implements Runnable {

        private int num;

        public MyTask(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            for (int i=1; i<=num; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}