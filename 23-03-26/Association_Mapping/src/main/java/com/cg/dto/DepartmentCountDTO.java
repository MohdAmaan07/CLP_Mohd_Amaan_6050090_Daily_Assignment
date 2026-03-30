package com.cg.dto;

public class DepartmentCountDTO {

    private String deptName;
    private long count;

    public DepartmentCountDTO(String deptName, long count) {
        this.deptName = deptName;
        this.count = count;
    }

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
    
}
