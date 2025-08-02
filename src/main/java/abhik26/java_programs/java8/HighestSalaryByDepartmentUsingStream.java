package abhik26.java_programs.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HighestSalaryByDepartmentUsingStream {

	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(
				new Employee("Alice", "HR", 60000, Employee.Gender.FEMALE),
				new Employee("Bob", "IT", 75000, Employee.Gender.MALE),
				new Employee("Charlie", "HR", 65000, Employee.Gender.FEMALE),
				new Employee("David", "IT", 80000, Employee.Gender.MALE),
				new Employee("Eve", "Finance", 70000, Employee.Gender.FEMALE),
				new Employee("Evy", "IT", 78000, Employee.Gender.FEMALE));

		// group by department with highest salary
		System.out.println("\n<------------------------->");
		Map<String, Optional<Employee>> highestSalaryByDepartment = employees.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

		highestSalaryByDepartment.forEach((department, employeeOptional) -> {
			employeeOptional.ifPresent(employee -> System.out
					.println("Department: " + department + ", Highest Paid Employee: " + employee));
		});

		// Group By department
		System.out.println("\n<------------------------->");
		employees.stream().collect(Collectors.groupingBy(Employee::getDepartment))
				.forEach((department, optionalEmployees) -> System.out.println(department + ": " + optionalEmployees));

		// Group by department and count of male and female in that department
		System.out.println("\n<------------------------->");
		Map<String, Map<Employee.Gender, Long>> departmentToGenderCountMap = employees.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment, Collectors.groupingBy(Employee::getGender, Collectors.counting())));
		System.out.println(departmentToGenderCountMap);

	}

	private static class Employee {
		private String name;
		private String department;
		private double salary;
		private Gender gender;

		public enum Gender {
			MALE, FEMALE
		}

		public Employee(String name, String department, double salary, Gender gender) {
			this.name = name;
			this.department = department;
			this.salary = salary;
			this.gender = gender;
		}

		public String getDepartment() {
			return department;
		}

		public double getSalary() {
			return salary;
		}

		public Gender getGender() {
			return this.gender;
		}

		@Override
		public String toString() {
			return "Employee [name=" + name + ", department=" + department + ", salary=" + salary + ", gender=" + gender
					+ "]";
		}

	}
}
