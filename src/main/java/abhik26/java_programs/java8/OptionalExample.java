package abhik26.java_programs.java8;

import java.util.Optional;

public class OptionalExample {
	public static void main(String[] args) {
		Optional<Object> optional = Optional.ofNullable(null);

		if (optional.isPresent()) {
			System.out.println(optional.get());
		}
	}
}
