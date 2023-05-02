package com.banigeo.webpoc.dto.department.region;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class RegionResponse extends RegionRequest {
    private Integer id;
}
