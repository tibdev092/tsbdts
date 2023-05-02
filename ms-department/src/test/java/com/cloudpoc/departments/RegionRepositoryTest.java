package com.cloudpoc.departments;


import com.cloudpoc.departments.model.Region;
import com.cloudpoc.departments.repository.RegionRepository;
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
public class RegionRepositoryTest {
    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void testFindRegionById() {
        Optional<Region> expectedRegion = regionRepository.findByRegionName("Europe");
        assertTrue(expectedRegion.isPresent());
        assertEquals(expectedRegion.get().getRegionId(), 1);
    }
}
