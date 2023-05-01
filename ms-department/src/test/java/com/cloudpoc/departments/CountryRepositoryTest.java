package com.cloudpoc.departments;

import com.cloudpoc.departments.model.Country;
import com.cloudpoc.departments.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(basePackages = "com.cloudpoc.departments")
public class CountryRepositoryTest {
    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testFindByCountryName() {
        Country expectedCountry = new Country();
        expectedCountry.setCountryId(1);
        expectedCountry.setCountryName("Romania");

        countryRepository.save(expectedCountry);
        Optional<Country> actualCountry = countryRepository.findByCountryName("Romania");

        assertTrue(actualCountry.isPresent());
        assertEquals(expectedCountry.getCountryId(), actualCountry.get().getCountryId());
        assertEquals(expectedCountry.getCountryName(), actualCountry.get().getCountryName());
    }
}
