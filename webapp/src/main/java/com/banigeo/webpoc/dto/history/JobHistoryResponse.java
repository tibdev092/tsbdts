package com.banigeo.webpoc.dto.history;

import com.banigeo.webpoc.dto.job.JobResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryResponse {
    private String job;
    private Double salary;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime startJob;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endJob;
}
