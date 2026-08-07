package com.example.employee_management.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.employee_management.dto.EmployeeRequest;
import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.repository.EmployeeSequenceRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeSequenceRepository employeeSequenceRepository;
    private final UtilityService utilityService;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeSequenceRepository employeeSequenceRepository, UtilityService utilityService) {

        this.employeeRepository = employeeRepository;
        this.employeeSequenceRepository = employeeSequenceRepository;
        this.utilityService = utilityService;
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByKeyword(keyword);
    }

    public Employee addEmployee(EmployeeRequest request) {

        Integer id = employeeSequenceRepository.nextSequence();

        String code = utilityService.generateEmployeeCode(id);

        Employee employee = new Employee();

        employee.setId(id);
        employee.setCode(code);
        employee.setName(utilityService.formatEmployeeName(request.getName()));
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());

        return employeeRepository.save(employee);
    }
}
