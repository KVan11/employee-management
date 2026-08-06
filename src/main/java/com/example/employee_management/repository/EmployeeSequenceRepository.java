package com.example.employee_management.repository;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeSequenceRepository {

    private final AtomicInteger sequence = new AtomicInteger(1);

    public int nextSequence() {
        return sequence.getAndIncrement();
    }
}