package abhik26.java_programs.io;

import java.io.*;

class FileExists {
	public static void main(String[] args) {
		File file = new File("D:\\Documents\\Programs\\some_random_file.txt");

		if (file.exists()) {
			System.out.println("file exists");
		} else {
			System.out.println("file doesn't exist");
		}
	}
}
