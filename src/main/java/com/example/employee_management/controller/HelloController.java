package com.example.employee_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.employee_management.service.UtilityService;

@RestController
public class HelloController {

    private final UtilityService utilityService;

    private final String companyName;

    @Autowired
    public HelloController(UtilityService utilityService, String companyName) {

        this.utilityService = utilityService;
        this.companyName = companyName;

    }

    @GetMapping("/hello")
    public String hello() {
        return companyName + " - " + utilityService.generateEmployeeCode();
    }
}
