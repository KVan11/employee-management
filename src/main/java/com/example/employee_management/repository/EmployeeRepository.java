package com.example.employee_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.employee_management.dto.DepartmentEmployeeCount;
import com.example.employee_management.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByDepartment_NameContainingIgnoreCase(String departmentName);

    @Query("""
        SELECT new com.example.employee_management.dto.DepartmentEmployeeCount(
            d.name,
            COUNT(e.id)
        )
        FROM Department d
        LEFT JOIN d.employees e
        GROUP BY d.id, d.name
        ORDER BY d.name
    """)
    List<DepartmentEmployeeCount> countEmployeesByDepartment();
}