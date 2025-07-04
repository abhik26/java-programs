package abhik26.java_programs.java8;

import java.util.Optional;

/*
 * OrElse is called everytime irrespective of the fact that
 * the value is present or not.
 * 
 * OrElseGet is called only when the value is not present.
 */
public class OptionalOrElseAndOrElseGetExample {
	public static void main(String[] args) {
		Optional<Integer> intOptional = Optional.ofNullable(null);
		System.out.println("Or else for null: " + intOptional.orElse(optionalCheckMethod(false)));
		System.out.println("Or else get for null: " + intOptional.orElseGet(() -> optionalCheckMethod(true)));

		System.out.println("\n<-------------------------------------------->");

		Optional<Integer> intNotNullOptional = Optional.ofNullable(1);
		System.out.println("Or else for not null: " + intNotNullOptional.orElse(optionalCheckMethod(false)));
		System.out
				.println("Or else get for not null: " + intNotNullOptional.orElseGet(() -> optionalCheckMethod(true)));
	}

	private static Integer optionalCheckMethod(boolean get) {
		if (get) {
			System.out.println("check method called for or else get...");
		} else {
			System.out.println("check method called for or else...");
		}
		return null;
	}
}
