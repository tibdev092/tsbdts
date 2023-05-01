package com.cloudpoc.departments;

import com.cloudpoc.departments.model.Location;
import com.cloudpoc.departments.repository.LocationRepository;
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
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;
    @Test
    public void testFindLocationById() {
        Optional<Location> foundLocation = locationRepository.findById(1);
        assertTrue(foundLocation.isPresent());
        assertEquals(foundLocation.get().getLocationId(), 1);
        assertEquals(foundLocation.get().getPostalCode(), "00989");
        assertEquals(foundLocation.get().getStreetAddress(), "1297 Via Cola di Rie");
        assertEquals(foundLocation.get().getCity(), "Roma");
    }
}
