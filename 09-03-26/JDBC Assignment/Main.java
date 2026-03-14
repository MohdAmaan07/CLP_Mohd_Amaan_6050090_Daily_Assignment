import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Scanner scanner = new Scanner(System.in);

        try {
            employeeDAO.createTable();

            while (true) {
                System.out.println("What to do? (1: Insert, 2: Update, 3: Fetch, 4: Delete, 5: Exit)");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: {
                        System.out.println("Enter name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter email:");
                        String email = scanner.nextLine();
                        Employee inserted = employeeDAO.insertEmployee(new Employee(name, email));
                        System.out.println("Inserted: ID=" + inserted.getId() + ", Name=" + inserted.getName() + ", Email=" + inserted.getEmail());
                        break;
                    }
                    case 2: {
                        System.out.println("Enter user ID to update:");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter new name:");
                        String newName = scanner.nextLine();
                        System.out.println("Enter new email:");
                        String newEmail = scanner.nextLine();
                        Employee updated = employeeDAO.updateEmployee(id, new Employee(newName, newEmail));
                        if (updated != null) {
                            System.out.println("Updated: ID=" + updated.getId() + ", Name=" + updated.getName() + ", Email=" + updated.getEmail());
                        } else {
                            System.out.println("User not found for update.");
                        }
                        break;
                    }
                    case 3: {
                        List<Employee> users = employeeDAO.getAllEmployees();
                        System.out.println("\n--- All Users ---");
                        for (Employee user : users) {
                            System.out.println(user);
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("Enter user ID to delete:");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();
                        Employee deleted = employeeDAO.deleteEmployee(deleteId);
                        if (deleted != null) {
                            System.out.println("Deleted: ID=" + deleted.getId() + ", Name=" + deleted.getName() + ", Email=" + deleted.getEmail());
                        } else {
                            System.out.println("User not found for deletion.");
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    }
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        scanner.close();
    }
}
