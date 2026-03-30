package Assignment5_14;

import java.util.Map;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        EmployeeService service = ac.getBean(EmployeeService.class);

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {

            System.out.println("\n------ Employee CRUD Menu ------");
            System.out.println("1. Add Employee");
            System.out.println("2. Fetch Employee By ID");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. View All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Name: ");
                    String name = sc.next();

                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();

                    Employee e = new Employee(id, name, salary);
                    service.addEmployee(e);

                    System.out.println("Employee Added Successfully");
                    break;

                case 2:
                    System.out.print("Enter Employee ID: ");
                    int fetchId = sc.nextInt();

                    Employee emp = service.fetchById(fetchId);

                    if (emp != null)
                        System.out.println(emp);
                    else
                        System.out.println("Employee not found");

                    break;

                case 3:
                    System.out.print("Enter Employee ID to Update: ");
                    int updateId = sc.nextInt();

                    System.out.print("Enter New Name: ");
                    String newName = sc.next();

                    System.out.print("Enter New Salary: ");
                    double newSalary = sc.nextDouble();

                    Employee updatedEmp = new Employee(updateId, newName, newSalary);
                    service.updateEmployee(updateId, updatedEmp);

                    System.out.println("Employee Updated Successfully");
                    break;

                case 4:
                    System.out.print("Enter Employee ID to Delete: ");
                    int deleteId = sc.nextInt();

                    service.deleteEmployee(deleteId);

                    System.out.println("Employee Deleted Successfully");
                    break;

                case 5:
                    Map<Integer, Employee> employees = service.getAllEmployees();

                    for (Employee employee : employees.values()) {
                        System.out.println(employee);
                    }

                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }
        sc.close();
    }
}