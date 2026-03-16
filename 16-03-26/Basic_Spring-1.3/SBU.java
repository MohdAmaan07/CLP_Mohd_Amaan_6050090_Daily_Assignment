package com.demo.springcore.beans;

import java.util.List;

public class SBU {
    private String sbuCode;
    private String sbuName;
    private String sbuHead;
    private List<Employee> employeeList;

    public SBU() {
    }

    public SBU(String sbuCode, String sbuName, String sbuHead, List<Employee> employeeList) {
        this.sbuCode = sbuCode;
        this.sbuName = sbuName;
        this.sbuHead = sbuHead;
        this.employeeList = employeeList;
    }

    public String getSbuCode() {
        return sbuCode;
    }

    public void setSbuCode(String sbuCode) {
        this.sbuCode = sbuCode;
    }

    public String getSbuName() {
        return sbuName;
    }

    public void setSbuName(String sbuName) {
        this.sbuName = sbuName;
    }

    public String getSbuHead() {
        return sbuHead;
    }

    public void setSbuHead(String sbuHead) {
        this.sbuHead = sbuHead;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "SBU Details" + "\n"
                + "---------------------------------------" + "\n"
                + "SBU [sbuCode=" + sbuCode + ", sbuName=" + sbuName + ", sbuHead=" + sbuHead
                + "\n\nEmployee Details\n" +
                "---------------------------------------\n"
                + "employeeList=" + employeeList + "]";
    }
}

