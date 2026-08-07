package com.example.employee_management.dto;

public class EmployeeRequest {
    private String name;
    private String email;
    private Integer departmentId;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String name, String email, Integer departmentId) {
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
