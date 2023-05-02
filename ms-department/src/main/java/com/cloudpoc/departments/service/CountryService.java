package com.cloudpoc.departments.service;

import com.cloudpoc.departments.dto.country.CountryRequest;
import com.cloudpoc.departments.dto.country.CountryResponse;
import com.cloudpoc.departments.exception.CountryNotFoundException;
import com.cloudpoc.departments.mapper.CountryMapper;
import com.cloudpoc.departments.model.Country;
import com.cloudpoc.departments.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;
    private CountryMapper countryMapper;

    public CountryResponse getCountry(String name) {
        Country country = countryRepository.findByCountryName(name).orElseThrow(CountryNotFoundException::new);
        return countryMapper.fromEntityToResponse(country);
    }
    public List<CountryResponse> getCountries() {
        return countryRepository.findAll().stream().map(countryMapper::fromEntityToResponse).collect(Collectors.toList());
    }
    public CountryResponse createCountry(CountryRequest newCountry) {
        return countryMapper.fromEntityToResponse(countryRepository.save(countryMapper.fromRequestToEntity(newCountry)));
    }
}
