package com.example.employee_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {
    @NotBlank(message = "Name không được để trống")
    @Size(min = 2, max = 50, message = "Name phải từ 2 đến 50 ký tự")
    private String name;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email phải hợp lệ")
    @Size(max = 30, message = "Email không được vượt quá 30 ký tự")
    private String email;

    @NotNull(message = "DepartmentId không được trống")
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
