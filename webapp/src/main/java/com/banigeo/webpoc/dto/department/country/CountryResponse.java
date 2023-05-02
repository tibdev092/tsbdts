package com.banigeo.webpoc.dto.department.country;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class CountryResponse extends CountryRequest {
    private Integer id;
}
