package abhik26.java_programs.java8;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;

public class IntStreamMethodsExample {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>();
        
        for (int i = 1; i <= 10; i++) {
            Random random = new Random();
            nums.add(random.nextInt(100));
        }

        IntSummaryStatistics intSummaryStatistics = nums.stream().mapToInt(e -> e.intValue()).summaryStatistics();

        System.out.println("Average:\t" + intSummaryStatistics.getAverage());
        System.out.println("Count:\t\t" + intSummaryStatistics.getCount());
        System.out.println("Max:\t\t" + intSummaryStatistics.getMax());
        System.out.println("Min:\t\t" + intSummaryStatistics.getMin());
        System.out.println("Sum:\t\t" + intSummaryStatistics.getSum());
    }
}
