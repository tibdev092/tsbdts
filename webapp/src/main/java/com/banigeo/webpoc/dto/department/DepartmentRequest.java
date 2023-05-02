package com.banigeo.webpoc.dto.department;

import com.banigeo.webpoc.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest
{
    @NotNull(message = "Department Name is not present")
    @NotEmpty(message = "Department Name cannot be empty")
    @Size(max = 100)
    private String departmentName;

    @NotNull(message = "Location is not present")
    @NotEmpty(message = "Location cannot be empty")
    private Location location;
}
