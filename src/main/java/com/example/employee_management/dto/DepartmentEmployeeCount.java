package com.example.employee_management.dto;

public class DepartmentEmployeeCount {
    private String departmentName;
    private Long employeeCount;

    public DepartmentEmployeeCount(String departmentName, Long employeeCount) {
        this.departmentName = departmentName;
        this.employeeCount = employeeCount;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }
}
