package com.example.employee_management.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.example.employee_management.model.Employee;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> findAll() {
        return employees;
    }

    public Optional<Employee> findById(Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    public List<Employee> findByKeyword(String keyword) {
        String normalizedKeyword = keyword == null ? "" : keyword.trim().toLowerCase();

        return employees.stream()
                .filter(employee -> employee.getName().toLowerCase().contains(normalizedKeyword)
                || employee.getCode().toLowerCase().contains(normalizedKeyword)
                || employee.getEmail().toLowerCase().contains(normalizedKeyword)
                || employee.getDepartment().toLowerCase().contains(normalizedKeyword))
                .toList();
    }

    public Employee save(Employee employee) {
        employees.add(employee);
        return employee;
    }
    
}
