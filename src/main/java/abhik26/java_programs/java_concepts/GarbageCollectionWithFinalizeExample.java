package abhik26.java_programs.java_concepts;

public class GarbageCollectionWithFinalizeExample {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyGarbageCollection mgc1 = new MyGarbageCollection();
		mgc1 = null;
		System.gc();
	}

	private static class MyGarbageCollection {
		@Override
		public void finalize() {
			System.out.println("Performing final task befor GC...");
		}
	}
}
