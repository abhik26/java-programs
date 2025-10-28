package abhik26.java_programs.java_concepts;

public class NullArgumentForMethodOverloadingExample {
	public static void main(String[] args) {
		nullArgumentCheck(null);
	}

	private static void nullArgumentCheck(String str) {
		System.out.println("String method called!!");
	}

	@SuppressWarnings("unused")
	private static void nullArgumentCheck(Object str) {
		System.out.println("Object method called!!");
	}
}
