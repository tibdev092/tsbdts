package com.cloudpoc.departments;

import com.cloudpoc.departments.model.Country;
import com.cloudpoc.departments.model.Department;
import com.cloudpoc.departments.model.Location;
import com.cloudpoc.departments.model.Region;
import com.cloudpoc.departments.repository.CountryRepository;
import com.cloudpoc.departments.repository.DepartmentRepository;
import com.cloudpoc.departments.repository.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(basePackages = "com.cloudpoc.departments")
@RunWith(SpringRunner.class)
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Test
    public void testFindByDepartmentName() {

        Location location = locationRepository.getById(1);

        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("Department1");
        department.setLocation(location);

        Optional<Department> foundDepartment = departmentRepository.findByDepartmentName("Department1");
        assertTrue(foundDepartment.isPresent());
        assertEquals(foundDepartment.get().getDepartmentName(), "Department1");
        assertEquals(foundDepartment.get().getLocation().getPostalCode(), "00989");
    }
}