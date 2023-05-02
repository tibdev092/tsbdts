package com.cloudpoc.departments.dto;

import com.cloudpoc.departments.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    @NotNull(message = "Department Name is not present")
    @NotEmpty(message = "Department Name cannot be empty")
    @Size(max = 100)
    private String departmentName;

    @NotNull(message = "Location is not present")
    @NotEmpty(message = "Location cannot be empty")
    private Location location;
}
