package cg.demo.jpahibernate;

import java.util.List;
import java.util.Scanner;

import cg.demo.jpahibernate.dao.EmployeeDAO;
import cg.demo.jpahibernate.entities.Employee;

public class App {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What to do? (1: Insert, 2: Update, 3: Fetch, 4: Delete, 5: Exit)");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter salary:");
                    double salary = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter department:");
                    String department = scanner.nextLine();
                    System.out.println("Enter phone number:");
                    String phoneno = scanner.nextLine();
                    Employee inserted = employeeDAO.insertEmployee(new Employee(name, salary, department, phoneno));
                    System.out.println("Inserted: " + inserted);
                    break;
                }
                case 2: {
                    System.out.println("Enter user ID to update:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new name:");
                    String newName = scanner.nextLine();
                    System.out.println("Enter new salary:");
                    double newSalary = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter new department:");
                    String newDepartment = scanner.nextLine();
                    System.out.println("Enter new phone number:");
                    String newPhoneno = scanner.nextLine();
                    Employee updated = employeeDAO.updateEmployee(id, new Employee(newName, newSalary, newDepartment, newPhoneno));
                    if (updated != null) {
                        System.out.println("Updated: " + updated);
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
                        System.out.println("Deleted: ID=" + deleted.getId() + ", Name=" + deleted.getName() + ", Phone No=" + deleted.getPhoneno() + ", Department=" + deleted.getDepartment());
                    } else {
                        System.out.println("User not found for deletion.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Exiting...");
                    scanner.close();
                    employeeDAO.close();
                    return;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}