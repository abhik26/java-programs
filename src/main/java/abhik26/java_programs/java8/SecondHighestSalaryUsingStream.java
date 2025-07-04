package abhik26.java_programs.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SecondHighestSalaryUsingStream {

	private static class Employee {
		public String name;
		public double salary;

		public Employee(String name, double salary) {
			this.name = name;
			this.salary = salary;
		}

		@Override
		public String toString() {
			return "Employee [name=" + name + ", salary=" + salary + "]";
		}

	}

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<Employee>();

		employees.add(new Employee("Abhishek", 20.5));
		employees.add(new Employee("Abhinesh", 25.5));
		employees.add(new Employee("Shima", 30.3));
		employees.add(new Employee("Anita", 30.4));

		Optional<Employee> theEmployee = getEmployee(employees);
		System.out.println(theEmployee.get());
	}

	public static Optional<Employee> getEmployee(List<Employee> employees) {
		return employees.stream().sorted((e1, e2) -> {
			if (e1.salary > e2.salary) {
				return -1;
			} else if (e1.salary < e2.salary) {
				return 1;
			} else {
				return e1.name.compareToIgnoreCase(e2.name);
			}
		}).skip(1).findFirst();
	}

}