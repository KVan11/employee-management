package com.example.employee_management.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.employee_management.dto.EmployeeRequest;
import com.example.employee_management.entity.Department;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {
    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;

    public EmployeeWebController(EmployeeService employeeService, DepartmentRepository departmentRepository) {
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();

        model.addAttribute("employees", employees);

        return "employees/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employeeRequest", new EmployeeRequest());

        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);

        return "employees/add";
    }

    @PostMapping("/add")
    public String addEmployee(
            @Valid @ModelAttribute EmployeeRequest employeeRequest,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            List<Department> departments = departmentRepository.findAll();
            model.addAttribute("departments", departments);
            model.addAttribute("employeeRequest", employeeRequest);
            return "employees/add";
        }

        employeeService.addEmployee(employeeRequest);

        return "redirect:/employees/list";
    }

    @GetMapping("/search")
    public String searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String department,
            Model model) {

        List<Employee> employees;

        if (name != null && !name.isBlank()) {
            employees = employeeService.searchByName(name);
        } else if (department != null && !department.isBlank()) {
            employees = employeeService.searchByDepartment(department);
        } else {
            employees = employeeService.getAllEmployees();
        }

        model.addAttribute("employees", employees);
        model.addAttribute("name", name);
        model.addAttribute("department", department);

        return "employees/search";
    }
}
