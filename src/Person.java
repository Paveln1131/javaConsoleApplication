import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person {

    private String firstName;
    private String lastName;
    private String birthNumber;

    public Person(String firstName, String lastName, String birthNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthNumber = birthNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public int calculateAge() {
        String[] parts = birthNumber.split("[/]");
        String birthDateString = parts[0];
        try {

            int year = Integer.parseInt(birthDateString.substring(0, 2));
            int month = Integer.parseInt(birthDateString.substring(2, 4));
            int day = Integer.parseInt(birthDateString.substring(4, 6));

            // Získám aktuální rok a vezmu poslední dvě číslice
            int currentYearLastTwoDigits = LocalDate.now().getYear() % 100;

            // Rozhodnu o jaké století se jedná - pokud je rok z rodného číla větší, než auktuální rok (98 > (20)24), nastavím 20. století
            int century = (year > currentYearLastTwoDigits) ? 1900 : 2000;

            LocalDate birthDate = LocalDate.of(century + year, month, day);
            LocalDate currentDate = LocalDate.now();

            return Period.between(birthDate, currentDate).getYears();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid birth number format.");
        }
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + ", Birth Number: " + birthNumber + ", Age: " + calculateAge();
    }

}
