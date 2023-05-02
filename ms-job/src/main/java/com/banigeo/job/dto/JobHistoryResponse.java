package com.banigeo.job.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryResponse {
    @NotNull
    private String job;
    @NotNull
    private Double salary;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime startJob;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endJob;
}
