package Assignment5_14;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    private Map<Integer, Employee> mp = new HashMap<>();

    @Override
    public Employee fetchById(int id) {
        return mp.get(id);
    }

    @Override
    public void addEmployee(Employee emp) {
        mp.put(emp.getId(), emp);
    }

    @Override
    public void updateEmployee(int id, Employee emp) {
        if (mp.containsKey(id)) {
            mp.put(id, emp);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        mp.remove(id);
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
        return mp;
    }
}