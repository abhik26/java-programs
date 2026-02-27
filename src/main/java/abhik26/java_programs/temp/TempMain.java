package abhik26.java_programs.temp;

import java.util.ArrayList;
import java.util.List;

public class TempMain {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("Abhishek");
		list.add("Anand");

		System.out.println("\n\n--->" + String.join(", ", list));
	}
}
