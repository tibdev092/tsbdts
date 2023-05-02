package com.banigeo.webpoc.dto.job;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class JobResponse extends JobRequest {
    private Integer id;
}
