package com.cloudpoc.departments;

import com.cloudpoc.departments.dto.country.CountryRequest;
import com.cloudpoc.departments.dto.country.CountryResponse;
import com.cloudpoc.departments.exception.CountryNotFoundException;
import com.cloudpoc.departments.mapper.CountryMapper;
import com.cloudpoc.departments.model.Country;
import com.cloudpoc.departments.repository.CountryRepository;
import com.cloudpoc.departments.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(basePackages = "com.cloudpoc.departments")
public class CountryServiceTest {

    private CountryService countryService;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        countryService = new CountryService(countryRepository, countryMapper);
    }

    @Test
    public void testGetCountryReturnsCorrectCountry() {
        String countryName = "China";

        Country country = new Country();
        country.setCountryName(countryName);
        CountryResponse expectedResponse = new CountryResponse();
        expectedResponse.setName(countryName);
        when(countryRepository.findByCountryName(countryName)).thenReturn(Optional.of(country));
        when(countryMapper.fromEntityToResponse(country)).thenReturn(expectedResponse);

        CountryResponse actualResponse = countryService.getCountry(countryName);

        verify(countryRepository).findByCountryName(countryName);
        verify(countryMapper).fromEntityToResponse(country);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testGetCountryThrowsExceptionWhenCountryNotFound() {
        String countryName = "Unknown";
        when(countryRepository.findByCountryName(countryName)).thenReturn(Optional.empty());

        assertThrows(CountryNotFoundException.class, () -> {
            countryService.getCountry(countryName);
        });

        verify(countryRepository).findByCountryName(countryName);
    }

    @Test
    public void getCountriesReturnsListOfCountries() {
        Country testCountry = new Country();
        testCountry.setCountryName("China");
        CountryResponse response1 = new CountryResponse();
        response1.setName("China");

        Country country2 = new Country();
        country2.setCountryName("Canada");
        CountryResponse response2 = new CountryResponse();
        response2.setName("Canada");

        List<Country> countries = Arrays.asList(testCountry, country2);
        List<CountryResponse> expectedResponses = Arrays.asList(response1, response2);
        when(countryRepository.findAll()).thenReturn(countries);
        when(countryMapper.fromEntityToResponse(testCountry)).thenReturn(response1);
        when(countryMapper.fromEntityToResponse(country2)).thenReturn(response2);

        List<CountryResponse> actualResponses = countryService.getCountries();

        verify(countryRepository).findAll();
        verify(countryMapper).fromEntityToResponse(testCountry);
        verify(countryMapper).fromEntityToResponse(country2);
        assertEquals(expectedResponses, actualResponses);
    }

    @Test
    public void createCountryReturnsCorrectCountry() {
        CountryRequest request = new CountryRequest();
        request.setName("USA");

        Country country = new Country();
        country.setCountryName("USA");

        CountryResponse expectedResponse = new CountryResponse();
        expectedResponse.setName("USA");

        when(countryMapper.fromRequestToEntity(request)).thenReturn(country);
        when(countryRepository.save(country)).thenReturn(country);
        when(countryMapper.fromEntityToResponse(country)).thenReturn(expectedResponse);

        CountryResponse actualResponse = countryService.createCountry(request);
        assertEquals(expectedResponse, actualResponse);
    }
}


