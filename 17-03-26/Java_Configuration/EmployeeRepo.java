package Assignment5_14;

import java.util.Map;

public interface EmployeeRepo {

    Employee fetchById(int id);

    void addEmployee(Employee emp);

    void updateEmployee(int id, Employee emp);

    void deleteEmployee(int id);

    Map<Integer, Employee> getAllEmployees();
}