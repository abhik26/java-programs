package abhik26.java_programs.design_patterns;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SingletonDesignPatternExample {

	public static void main(String[] args) {
		System.out.println();
		try {
            // Serialize the singleton instance
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
			System.out.println("Getting instance to be written...");
            oos.writeObject(Singleton.getInstance());
            oos.close();

            // Deserialize the singleton instance
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"));
			System.out.println("Reading object from input stream...");
            Singleton deserializedSingleton = (Singleton) ois.readObject();
            ois.close();

			System.out.println("Object deserialized with toString: " + deserializedSingleton);

			Singleton SingletonObj = Singleton.getInstance();

            // Verify that both instances are the same
            System.out.println("Are instances same? " + (SingletonObj.equals(deserializedSingleton)));

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private static class Singleton implements Serializable {

		private static volatile Singleton obj = null;

		private Singleton() {
			System.out.println("Singleton private constructor invoked...");
		}

		public static Singleton getInstance() {
			boolean objectPresent = true;
			
			if (obj == null) {
				synchronized (Singleton.class) {
					if (obj == null) {
						objectPresent = false;
						obj = new Singleton();
					}
				}
			}
			
			System.out.println("Singleton.getInstance method invoked with object present: " + objectPresent);
			return obj;
		}

		private Object readResolve() {
			System.out.println("Singleton.readResolve method invoked");
			return getInstance();
			// return obj;
		}
	}
}
