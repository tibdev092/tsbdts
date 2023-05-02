package com.banigeo.webpoc.dto.department.location;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class LocationResponse extends LocationRequest {
    private Integer id;
}
