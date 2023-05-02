package com.banigeo.webpoc.dto.department.location;

import com.banigeo.webpoc.model.Country;
import com.banigeo.webpoc.model.Department;
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
public class LocationRequest {

    @NotNull(message = "Street address is not present")
    @NotEmpty(message = "Street address cannot be empty")
    @Size(max = 100)
    private String streetAddress;

    @NotNull(message = "Postal code is not present")
    @NotEmpty(message = "Postal code cannot be empty")
    @Size(max = 100)
    private String postalCode;

    @NotNull(message = "City is not present")
    @NotEmpty(message = "City cannot be empty")
    @Size(max = 100)
    private String city;

    @NotNull(message = "Country is not present")
    @NotEmpty(message = "Country cannot be empty")
    private Country country;

    @NotNull(message = "Department is not present")
    @NotEmpty(message = "Department cannot be empty")
    private Department department;

}
