package com.cloudpoc.departments.mapper;

import com.cloudpoc.departments.dto.country.CountryRequest;
import com.cloudpoc.departments.dto.country.CountryResponse;
import com.cloudpoc.departments.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CountryMapper {

    public CountryResponse fromEntityToResponse(Country entity) {
        CountryResponse cr = new CountryResponse();
        cr.setId(entity.getCountryId());
        cr.setName(entity.getCountryName());
        cr.setRegion(entity.getRegion());
        return cr;
    }

    public Country fromRequestToEntity(CountryRequest request) {
        return new Country(request.getRegion(), request.getName());
    }
}
