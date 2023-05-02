package com.banigeo.webpoc.dto.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {
    @NotNull(message = "First Name is not present")
    @NotEmpty(message = "First Name cannot be empty")
    @Size(max = 50)
    private String title;

    @NotNull(message = "Minimum salary is not present")
    @Min(value = 0, message = "Minimum salary has to be greater than 0")
    private Double min;

    @NotNull(message = "Maximum salary is not present")
    @Min(value = 0, message = "Maximum salary has to be greater than 0")
    private Double max;
}
