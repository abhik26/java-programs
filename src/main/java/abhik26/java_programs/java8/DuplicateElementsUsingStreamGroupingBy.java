package abhik26.java_programs.java8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateElementsUsingStreamGroupingBy {
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<Integer>();
		nums.add(10);
		nums.add(20);
		nums.add(15);
		nums.add(15);
		nums.add(1);
		nums.add(19);

		Map<Integer, Long> numCountMap = nums.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println(numCountMap);

		numCountMap.entrySet().stream().filter(e -> e.getValue() > 1).forEach(e -> System.out.println(e.getKey()));

		// another way
		System.out.println("\n<----------- Another way --------->");
		Set<Integer> duplicates = new HashSet<Integer>();
		nums.stream().filter(e -> !duplicates.add(e)).forEach(System.out::println);
	}
}
