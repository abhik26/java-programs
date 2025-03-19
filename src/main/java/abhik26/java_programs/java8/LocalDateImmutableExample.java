package abhik26.java_programs.java8;

import java.time.LocalDate;

public class LocalDateImmutableExample {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2012, 2, 29);
		date.plusYears(2);
		System.out.println(date);
	}
}
