import java.util.HashMap;
import java.util.Map;

public class Database {

    //Používám mapu s rodným číslem jako klíč, abych zajistil unikátnost rodného čísla
    private Map<String, Person> personMap = new HashMap<>();

    // Odstraním lomítko z rodného čísla, abychom mohli vyhledávat podle 2 formátů stejně jako při vytvoření záznamu
    private String normalizeBirthNumber(String birthNumber) {
        return birthNumber.replace("/", "");
    }

    public void addPerson(String firstName, String lastName, String birthNumber) {

        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("First name and last name cannot be empty.");
        }

        //Doufám, že regex není zbytečně složitý, pro jistotu píšu doc
        /**
         * \\d - libovolné číslo od 0 do 9
         * {6} | {4} - počet čísel
         * První část logického výrazu kontroluje formát s lomítkem, a druhý bez
         */
        if (!birthNumber.matches("\\d{6}/\\d{4}") && !birthNumber.matches("\\d{6}\\d{4}")) {
            throw new IllegalArgumentException("Invalid birth number format.");
        }

        String normalizedBirthNumber = normalizeBirthNumber(birthNumber);

        if (personMap.containsKey(normalizedBirthNumber)) {
            throw new IllegalArgumentException("Person with this birth number already exists.");
        }

        Person person = new Person(firstName, lastName, normalizedBirthNumber);
        personMap.put(normalizedBirthNumber, person);
    }

    public void removePerson(String birthNumber) {
        String normalizedBirthNumber = normalizeBirthNumber(birthNumber);

        if (!personMap.containsKey(normalizedBirthNumber)) {
            throw new IllegalArgumentException("Person not found.");
        }

        personMap.remove(normalizedBirthNumber);
    }

    public Person findPerson(String birthNumber) {
        String normalizedBirthNumber = normalizeBirthNumber(birthNumber);

        if (!personMap.containsKey(normalizedBirthNumber)) {
            throw new IllegalArgumentException("Person not found.");
        }

        return personMap.get(normalizedBirthNumber);
    }
}
