package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.dto.EmployeeDepartmentDTO;
import com.cg.dto.EmployeeMobileDTO;
import com.cg.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT new com.cg.dto.EmployeeDepartmentDTO(e.name, d.name, d.managerName) FROM Employee e JOIN e.department d")
    List<EmployeeDepartmentDTO> fetchAllEmployeeWithDept();

    @Query("SELECT e FROM Employee e JOIN FETCH e.department d WHERE d.name = :name")
    List<Employee> findByDeptName(String name);

    @Query("SELECT new com.cg.dto.EmployeeMobileDTO(e.id, e.name, d.name, d.managerName) FROM Employee e JOIN e.department d JOIN e.mobileNumbers m WHERE m = :mobile")
    List<EmployeeMobileDTO> findByMobile(String mobile);
}
