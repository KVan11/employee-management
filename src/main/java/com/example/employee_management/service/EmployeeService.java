package com.example.employee_management.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.example.employee_management.dto.EmployeeRequest;
import com.example.employee_management.entity.Department;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Cacheable("employeeCount")
    public long getEmployeeCount() {
        logger.info("Querying employee count from database");
        return employeeRepository.count();
    }

    @CacheEvict(value = "employeeCount", allEntries = true)
    public Employee addEmployee(EmployeeRequest request) {
        Department department = findDepartmentById(request.getDepartmentId());

        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        logger.info("Added employee: id={}, name={}, email={}",
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getEmail());

        return savedEmployee;
    }

    @CacheEvict(value = "employeeCount", allEntries = true)
    public Employee updateEmployee(Integer id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        Department department = findDepartmentById(request.getDepartmentId());

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(employee);

        logger.info("Updated employee: id={}, name={}, email={}",
                updatedEmployee.getId(),
                updatedEmployee.getName(),
                updatedEmployee.getEmail());

        return updatedEmployee;
    }

    @CacheEvict(value = "employeeCount", allEntries = true)
    public void delete(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

    employeeRepository.delete(employee);

    logger.info("Deleted employee: id={}, name={}, email={}",
            employee.getId(),
            employee.getName(),
            employee.getEmail());
    }

    public List<Employee> searchByName(String keyword) {
        return employeeRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Employee> searchByDepartment(String keyword) {
        return employeeRepository.findByDepartment_NameContainingIgnoreCase(keyword);
    }

    private Department findDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
    }
}
