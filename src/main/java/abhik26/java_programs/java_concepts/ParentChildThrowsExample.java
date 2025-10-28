package abhik26.java_programs.java_concepts;

import java.util.Random;

interface ThrowsInterface {
	void throwsIfNumIsOddFunction(int num) throws RuntimeException;
}

public class ParentChildThrowsExample implements ThrowsInterface {

	@Override
	public void throwsIfNumIsOddFunction(int num) {
		System.out.println("num: " + num);

		if (num % 2 != 0) {
			System.out.println("num is odd");
			throw new RuntimeException("num is odd");
		}
	}

	public static void main(String[] args) {
		ThrowsInterface obj = new ParentChildThrowsExample();
		obj.throwsIfNumIsOddFunction(new Random().nextInt(100));
	}

}
