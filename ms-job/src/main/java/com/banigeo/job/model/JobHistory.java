package com.banigeo.job.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobHistoryId;

    @NotNull
    private Integer employeeId;

    @NotNull
    private String jobTitle;

    private Double salary;
    private LocalDateTime start;
    private LocalDateTime end;
}