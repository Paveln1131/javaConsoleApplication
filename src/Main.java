import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Database database = new Database();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Find person by birth number");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter last name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter birth number (YYMMDDXXXX or YYMMDD/XXXX): ");
                        String birthNumber = scanner.nextLine();
                        database.addPerson(firstName, lastName, birthNumber);
                        System.out.println("Person added successfully.");
                        break;
                    case 2:
                        System.out.print("Enter birth number: ");
                        String birthNumberToRemove = scanner.nextLine();
                        database.removePerson(birthNumberToRemove);
                        System.out.println("Person removed successfully.");
                        break;
                    case 3:
                        System.out.print("Enter birth number: ");
                        String birthNumberToFind = scanner.nextLine();
                        Person person = database.findPerson(birthNumberToFind);
                        System.out.println("Person found: " + person);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}