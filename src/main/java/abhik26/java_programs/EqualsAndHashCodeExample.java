package abhik26.java_programs;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings("unused")
public class EqualsAndHashCodeExample {
    public static void main(String[] args) {
        Set<Employee> employeeSet = new HashSet<Employee>();

        Employee e1 = new Employee(1, "Abhishek");
        Employee e2 = new Employee(1, "Abhinesh");
        
        employeeSet.add(e1);
        employeeSet.add(e2);

        System.out.println("equals: " + e1.equals(e2));
        System.out.println("size: " + employeeSet.size());
    }

    private static class Employee {
        Integer id;
        String name;

        public Employee(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.id);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            else if (obj == this)
                return true;
            else if (obj.getClass() != getClass())
                return false;
            else if (obj.hashCode() != hashCode())
                return false;
            
            return true;
        }
    }
}
