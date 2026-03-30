package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.EmployeeDepartmentDTO;
import com.cg.entity.Department;
import com.cg.entity.Employee;
import com.cg.service.DepartmentService;
import com.cg.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    
    @Autowired
    private DepartmentService depservice;
    
 // Insert Department
    @PostMapping("/department")
    public ResponseEntity<?> addDepartment(@RequestBody @Valid Department dept) {

        Department saved = depservice.addDepartment(dept);

        return new ResponseEntity<>(
                "Department added successfully with ID: " + saved.getId(),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/employee/{name}")
    public ResponseEntity<?> add(@RequestBody @Valid Employee emp,@PathVariable String name) {

        Employee saved = service.addEmployee(emp, name);

        return new ResponseEntity<>("Employee added successfully with ID: " + saved.getId(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAll() {
        List<EmployeeDepartmentDTO> list = service.getAllEmployees();

        if (list.isEmpty()) {
            return new ResponseEntity<>("No employees found", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity<>(service.getCount(), HttpStatus.OK);
    }

    @GetMapping("/department/{name}")
    public ResponseEntity<?> getByDept(@PathVariable String name) {
        return new ResponseEntity<>(service.getByDept(name), HttpStatus.OK);
    }

    @GetMapping("/mobile/{number}")
    public ResponseEntity<?> getByMobile(@PathVariable String number) {
        return new ResponseEntity<>(service.getByMobile(number), HttpStatus.OK);
    }
}