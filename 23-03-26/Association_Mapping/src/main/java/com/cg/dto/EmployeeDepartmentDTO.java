package com.cg.dto;

public class EmployeeDepartmentDTO {

    private String empName;
    private String deptName;
    private String managerName;

    public EmployeeDepartmentDTO(String empName, String deptName, String managerName) {
        this.empName = empName;
        this.deptName = deptName;
        this.managerName = managerName;
    }

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
    
}
