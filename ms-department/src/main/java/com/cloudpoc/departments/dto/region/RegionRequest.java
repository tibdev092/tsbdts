package com.cloudpoc.departments.dto.region;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionRequest {
    @NotNull(message = "Name is not present")
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 100)
    private String name;
}
