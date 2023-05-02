package com.cloudpoc.employees.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    @NotNull(message = "First Name is not present")
    @NotEmpty(message = "First Name cannot be empty")
    @Size(max = 100)
    private String firstName;

    @NotNull(message = "Last Name is not present")
    @NotEmpty(message = "Last Name cannot be empty")
    @Size(max = 100)
    private String lastName;

    @NotNull(message = "Salary is not present")
    @Min(value = 0, message = "Salary has to be greater than 0")
    private Double salary;

    @Email(message = "Email doesn't respect the pattern")
    private String email;

    private String phone;
    private String department;
    private String job;
}


