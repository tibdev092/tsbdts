package com.cloudpoc.departments.dto.location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationResponse extends LocationRequest {
    private Integer id;
}
