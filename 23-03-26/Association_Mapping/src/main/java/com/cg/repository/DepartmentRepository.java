package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.dto.DepartmentCountDTO;
import com.cg.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findByNameIgnoreCase(String name);

    @Query("SELECT new com.cg.dto.DepartmentCountDTO(d.name, COUNT(e)) FROM Department d LEFT JOIN d.employees e GROUP BY d.name")
    List<DepartmentCountDTO> countEmployees();
}
