package com.banigeo.job.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest extends RepresentationModel<JobRequest> {
    @NotNull(message = "First Name is not present")
    @NotEmpty(message = "First Name cannot be empty")
    @Size(max = 50)
    private String title;

    @NotNull(message = "Minimum salary is not present")
    @Min(value = 0, message = "Minimum salary has to be greater than 0")
    private Double min;

    @NotNull(message = "Minimum salary is not present")
    @Min(value = 0, message = "Maximum salary has to be greater than 0")
    private Double max;
}