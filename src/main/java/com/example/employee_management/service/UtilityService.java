package com.example.employee_management.service;

import org.springframework.stereotype.Service;

import com.example.employee_management.component.EmployeeCodeFormatter;
import com.example.employee_management.repository.EmployeeSequenceRepository;

@Service
public class UtilityService {

    private final EmployeeSequenceRepository employeeSequenceRepository;

    private final EmployeeCodeFormatter employeeCodeFormatter;

    public UtilityService(EmployeeSequenceRepository employeeSequenceRepository, EmployeeCodeFormatter employeeCodeFormatter) {
        this.employeeSequenceRepository = employeeSequenceRepository;
        this.employeeCodeFormatter = employeeCodeFormatter;
    }

    public String generateEmployeeCode() {
        return generateEmployeeCode(employeeSequenceRepository.nextSequence());
    }

    public String generateEmployeeCode(int sequence) {
        return employeeCodeFormatter.format(sequence);
    }

    public String formatEmployeeName(String name) {
        return name.trim().toUpperCase();
    }
}
