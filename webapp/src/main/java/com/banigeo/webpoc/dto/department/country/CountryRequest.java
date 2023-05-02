package com.banigeo.webpoc.dto.department.country;

import com.banigeo.webpoc.model.Region;
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
public class CountryRequest {
    @NotNull(message = "Name is not present")
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 100)
    private String name;

    @NotNull(message = "Region is not present")
    @NotEmpty(message = "Region cannot be empty")
    private Region region;
}
