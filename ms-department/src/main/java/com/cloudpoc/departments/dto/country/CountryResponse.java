package com.cloudpoc.departments.dto.country;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountryResponse extends CountryRequest {
    private Integer id;
}
