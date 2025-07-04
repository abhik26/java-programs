package abhik26.java_programs.java8;

import java.util.Optional;

public class EmptyOptionalExample {
	public static void main(String[] args) {
		String[] str = new String[5];
		str[2] = "This is a sample code.";
		Optional<String> empty = Optional.empty();
		System.out.println(empty);
		Optional<String> value = Optional.of(str[2]);
		System.out.println(value);
	}
}
