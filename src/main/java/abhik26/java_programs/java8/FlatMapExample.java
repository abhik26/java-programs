package abhik26.java_programs.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {
	public static void main(String[] args) {
		List<Employee> employees = init();

		List<String> employeeCities = employees.stream().flatMap(e -> e.cities.stream()).collect(Collectors.toList());
		System.out.println(employeeCities);
	}

	private static List<Employee> init() {
		List<Employee> employees = new ArrayList<Employee>();

		for (int i = 0; i < 5; i++) {
			List<String> cities = new ArrayList<String>();
			int multiplier = 10 * i;

			for (int j = 1; j <= 3; j++) {
				cities.add("City" + (multiplier + j));
			}

			Employee employee = new Employee(1, "Abhishek" + i, cities);
			employees.add(employee);
		}

		return employees;
	}

	private static class Employee {
		int id;
		String name;
		List<String> cities;

		public Employee(int id, String name, List<String> cities) {
			this.id = id;
			this.name = name;
			this.cities = cities;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", name=" + name + ", cities=" + cities + "]";
		}
	}
}
