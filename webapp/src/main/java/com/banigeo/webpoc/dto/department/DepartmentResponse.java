package com.banigeo.webpoc.dto.department;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class DepartmentResponse extends DepartmentRequest {
    private Integer id;
}
