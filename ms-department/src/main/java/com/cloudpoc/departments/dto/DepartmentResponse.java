package com.cloudpoc.departments.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentResponse extends DepartmentRequest {
    private Integer id;
}
