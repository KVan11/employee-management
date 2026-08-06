package com.example.employee_management.component;

import org.springframework.stereotype.Component;

@Component
public class EmployeeCodeFormatter {

    public String format(int sequence) {
        return String.format("EMP%03d", sequence);
    }
}