package abhik26.java_programs;

public class FinalExample {

    private static class Employee {
        Integer id;
        String name;
        String dept;

        public Employee(Integer id, String name, String dept) {
            this.id = id;
            this.name = name;
            this.dept = dept;
        }

        @Override
        public String toString() {
            return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + "]";
        }
    }

    public static void main(String[] args) {
        Employee employee = new Employee(1, "Abhishek", "IT");
        System.out.println(changeInstanceContent(employee));
    }

    private static Employee changeInstanceContent(final Employee employee) {
        employee.name = "Abhishek Anand";
        return employee;
    }
}
