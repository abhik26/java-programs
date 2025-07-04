package abhik26.java_programs.java8;

import java.util.stream.Stream;

public class AllMatchExample {
	public static void main(String[] args) {
		Stream<Object> stream = Stream.of(10, 20, "30");
		boolean output = stream.allMatch(in -> in instanceof Number);
		System.out.println(output);
	}
}
