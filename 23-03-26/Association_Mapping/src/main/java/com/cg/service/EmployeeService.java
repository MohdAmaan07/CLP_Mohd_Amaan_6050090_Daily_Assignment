package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.DepartmentCountDTO;
import com.cg.dto.EmployeeDepartmentDTO;
import com.cg.dto.EmployeeMobileDTO;
import com.cg.entity.Department;
import com.cg.entity.Employee;
import com.cg.exception.DepartmentNameNotFoundException;
import com.cg.exception.MobileNumberDoesNotExistsForEmployeeException;
import com.cg.repository.DepartmentRepository;
import com.cg.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private DepartmentRepository deptRepo;

    public Employee addEmployee(Employee emp, String deptName) {

        Department dept = deptRepo.findByNameIgnoreCase(deptName);

        if (dept == null) {
            throw new DepartmentNameNotFoundException("Department with name '" + deptName + "' not found");
        }

        emp.setDepartment(dept);
        return empRepo.save(emp);
    }

    public List<EmployeeDepartmentDTO> getAllEmployees() {
        return empRepo.fetchAllEmployeeWithDept();
    }

    public List<DepartmentCountDTO> getCount() {
        return deptRepo.countEmployees();
    }

    public List<Employee> getByDept(String name) {
        List<Employee> list = empRepo.findByDeptName(name);
        if (list.isEmpty()) {
            throw new DepartmentNameNotFoundException("Department not found");
        }
        return list;
    }

    public List<EmployeeMobileDTO> getByMobile(String mobile) {

        List<EmployeeMobileDTO> list = empRepo.findByMobile(mobile);

        if (list.isEmpty()) {
            throw new MobileNumberDoesNotExistsForEmployeeException("Mobile not found");
        }

        return list;
    }
}
