package com.cloudpoc.employees.controller;

import com.cloudpoc.employees.dto.EmployeeRequest;
import com.cloudpoc.employees.dto.EmployeeResponse;
import com.cloudpoc.employees.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
@Validated
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Integer id,
                                                           @Valid @RequestBody EmployeeRequest updateEmployee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, updateEmployee));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employee) {
        EmployeeResponse savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.created(URI.create("/employee/" + savedEmployee.getId())).body(savedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.findEmployee(id));
    }

    @GetMapping
    public List<EmployeeResponse> getEmployee(@RequestParam(required = false) String firstName,
                                              @RequestParam(required = false) String lastName) {
        return employeeService.getEmployees(firstName, lastName);
    }
}

