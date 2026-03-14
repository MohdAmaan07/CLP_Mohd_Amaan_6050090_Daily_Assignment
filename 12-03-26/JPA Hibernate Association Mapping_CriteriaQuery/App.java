package cg.demo;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import cg.demo.orm.entities.*;

import cg.demo.orm.dao.LogicDAO;


public class App {
    public static void main(String[] args) {
        LogicDAO logicDAO = new LogicDAO();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What to do? (1: Add Employee, 2: Fetch Employees along with Department and Manager " +
                    "3: Fetch No of Employees working in Each Department " + "\n" +
                    "4: Fetch Details of Employees from a Given Department " +
                    "5: Fetch Department Details based on Mobile Number " +
                    "6: Fetch Employee Whose Salary is Greater than Provided Salary " +
                    "7: Exit)");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter Employee name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Employee Salary:");
                    int salary = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Department Name:");
                    String departmentName = scanner.nextLine();
                    System.out.println("Enter Mobile Number:");
                    long mobileNumber = scanner.nextLong();
                    scanner.nextLine();

                    Employee emp = new Employee();
                    emp.setName(name);
                    emp.setSalary(salary);
                    emp.setMobileNumber(Set.of(mobileNumber));
                    emp.setDepartment(logicDAO.findDepartmentByName(departmentName));
                    Employee res = logicDAO.insertEmployee(emp);
                    System.out.println("Employee added with ID: " + res.getEmployeeId());
                    break;
                case 2:
                    List<Employee> result = logicDAO.getEmployeesWithDepartmentAndManager();
                    for (Employee e : result) {
                        System.out.println("Employee Name: " + e.getName() + ", Department: " + e.getDepartment().getName() + ", Manager: " + e.getDepartment().getManagerName());
                    }
                    break;
                case 3:
                    List<Object[]> empCount = logicDAO.getNumberOfEmployeesInEachDepartment();
                    for (Object[] row : empCount) {
                        System.out.println("Department: " + row[0] + ", Employee Count: " + row[1]);
                    }
                    break;
                case 4:
                    System.out.println("Enter Department name:");
                    String deptName = scanner.nextLine();
                    List<Employee> employeesInDept = logicDAO.getEmployeeByDepartment(deptName);
                    for (Employee e : employeesInDept) {
                        System.out.println("Employee Name: " + e.getName() + ", Salary: " + e.getSalary());
                    }
                    break;
                case 5:
                    System.out.println("Enter Mobile Number:");
                    long mobileNum = scanner.nextLong();
                    scanner.nextLine();
                    List<Object[]> details = logicDAO.getDetailsByMobileNumber(mobileNum);
                    for (Object[] row : details) {
                        System.out.println("Employee ID: " + row[0] + ", Employee Name: " + row[1] + ", Department: " + ((Department) row[2]).getName());
                    }
                    break;
                case 6:
                    System.out.println("Enter Salary:");
                    int sal = scanner.nextInt();
                    scanner.nextLine();
                    List<Object[]> highEarners = logicDAO.getEmployeeAboveSalary(sal);
                    for (Object[] row : highEarners) {
                        System.out.println("Employee Name: " + row[0] + ", Salary: " + row[1] + ", Department: " + ((Department) row[2]).getName());
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    logicDAO.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
}
