package com.banigeo.job.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class JobResponse extends JobRequest {
    private Integer id;
}
