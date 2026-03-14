package cg.demo.orm.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String name;
    private int salary;

    @ElementCollection
    @CollectionTable(name = "employee_mobile_numbers", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "mobile_number")
    private Set<Long> mobileNumber;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Long> getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Set<Long> mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }



}
