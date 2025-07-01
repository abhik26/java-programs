package abhik26.java_programs.java8;

import java.time.LocalDate;

public class LocalDateExample {

    public static void main(String[] args) {
        LocalDate bikePurchaseDate = LocalDate.of(2024, 11, 30);
        System.out.println("Bike second service date: " + bikePurchaseDate.plusDays(240));
    }
}
