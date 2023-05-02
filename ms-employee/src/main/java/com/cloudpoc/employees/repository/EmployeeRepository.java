package com.cloudpoc.employees.repository;

import com.cloudpoc.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmailAddress(String email);
    Optional<Employee> findByPhoneNumber(String phone);
}
