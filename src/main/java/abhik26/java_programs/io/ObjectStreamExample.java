package abhik26.java_programs.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectStreamExample {

	public static void main(String[] args) {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			Student student = new Student("Abhishek", 20, 80.3f);

			oos = new ObjectOutputStream(new FileOutputStream("student.txt"));
			oos.writeObject(student);

			ois = new ObjectInputStream(new FileInputStream("student.txt"));
			Object deserializedObject = ois.readObject();
			Student deserializedStudent = null;

			if (deserializedObject instanceof Student) {
				deserializedStudent = (Student) deserializedObject;
			}

			oos.close();
			ois.close();

			if (deserializedStudent != null) {
				System.out.println(deserializedStudent);

				oos = new ObjectOutputStream(new FileOutputStream("student.txt"));
				oos.writeObject(deserializedStudent);

				ois = new ObjectInputStream(new FileInputStream("student.txt"));
				Object deserializedObject1 = ois.readObject();
				Student deserializedStudent1 = null;

				if (deserializedObject1 instanceof Student) {
					deserializedStudent1 = (Student) deserializedObject1;
				}

				if (deserializedStudent1 != null) {
					System.out.println(deserializedStudent1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class Student implements Serializable {
		String name;
		Integer age;
		Float marks;
		Integer deserializedCount;

		public Student(String name, int age, float marks) {
			this.name = name;
			this.age = age;
			this.marks = marks;
		}

		private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
			ois.defaultReadObject();
			this.deserializedCount = deserializedCount == null ? 1 : ++deserializedCount;

		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + ", marks=" + marks + ", deserializedCount="
					+ deserializedCount + "]";
		}

	}
}