package com.cloudpoc.employees.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class EmployeeResponse extends EmployeeRequest {
    private Integer id;

}