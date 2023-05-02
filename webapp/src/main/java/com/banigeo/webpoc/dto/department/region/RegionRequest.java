package com.banigeo.webpoc.dto.department.region;

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
public class RegionRequest {
    @NotNull(message = "Name is not present")
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 100)
    private String name;
}
