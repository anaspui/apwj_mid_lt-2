import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Employee[] employees = new Employee[3];
        int numEmployees = 0;

        while (numEmployees < 3) {
            System.out.println("Enter employee information for Employee " + (numEmployees + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Joining Date (dd/MM/yyyy): ");
            Date joiningDate = Employee.parseDate(scanner.nextLine(), dateFormat);

            System.out.print("Date of Birth (dd/MM/yyyy): ");
            Date dateOfBirth = Employee.parseDate(scanner.nextLine(), dateFormat);

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Employee Type (1 for Officer, 2 for Staff): ");
            int employeeType = Integer.parseInt(scanner.nextLine());

            employees[numEmployees] = new Employee(name, joiningDate, dateOfBirth, email, employeeType);
            numEmployees++;

            if (numEmployees < 3) {
                System.out.print("Do you want to enter information for another employee? (yes/no): ");
                String userInput = scanner.nextLine().toLowerCase();
                if (!userInput.equals("yes")) {
                    break;
                }
            }
        }

        System.out.println("\nEmployee Details:");
        for (int i = 0; i < numEmployees; i++) {
            Employee employee = employees[i];
            System.out.println("Employee " + (i + 1) + ":");
            System.out.println("Name: " + employee.getName());
            System.out.println("Joining Date: " + dateFormat.format(employee.getJoiningDate()));
            System.out.println("Date of Birth: " + dateFormat.format(employee.getDateOfBirth()));
            System.out.println("Email: " + employee.getEmail());
            System.out.println("Vacation Leave: " + employee.getVacationLeave());
            System.out.println("Sick Leave: " + employee.getSickLeave());
            System.out.println();
        }

        scanner.close();
    }
}