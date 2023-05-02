package com.cloudpoc.departments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cloudpoc.departments.model.Country;
import com.cloudpoc.departments.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cloudpoc.departments.dto.DepartmentRequest;
import com.cloudpoc.departments.dto.DepartmentResponse;
import com.cloudpoc.departments.exception.DepartmentNotFoundException;
import com.cloudpoc.departments.mapper.DepartmentMapper;
import com.cloudpoc.departments.model.Department;
import com.cloudpoc.departments.repository.DepartmentRepository;
import com.cloudpoc.departments.service.DepartmentService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan(basePackages = "com.cloudpoc.departments")
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDepartmentByName() {
        String departmentName = "Test Department";
        Department department = new Department(departmentName, null);
        DepartmentResponse expectedResponse = new DepartmentResponse();
        expectedResponse.setDepartmentName(departmentName);
        expectedResponse.setId(1);

        when(departmentRepository.findByDepartmentName(anyString())).thenReturn(Optional.of(department));
        when(departmentMapper.fromEntityToResponse(any(Department.class))).thenReturn(expectedResponse);

        DepartmentResponse actualResponse = departmentService.getDepartment(departmentName);

        assertEquals(expectedResponse.getDepartmentName(), actualResponse.getDepartmentName());
        assertEquals(expectedResponse.getId(), actualResponse.getId());
    }

    @Test
    public void testGetDepartmentByNameThrowsException() {
        String departmentName = "Test Department";

        when(departmentRepository.findByDepartmentName(anyString())).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getDepartment(departmentName));
    }

    @Test
    public void testGetDepartments() {
        Department department1 = new Department("Department 1", null);
        Department department2 = new Department("Department 2", null);
        List<Department> departmentList = Arrays.asList(department1, department2);
        DepartmentResponse expectedResponse1 = new DepartmentResponse();
        expectedResponse1.setDepartmentName("Department 1");
        expectedResponse1.setId(1);
        DepartmentResponse expectedResponse2 = new DepartmentResponse();
        expectedResponse2.setDepartmentName("Department 2");
        expectedResponse2.setId(2);
        List<DepartmentResponse> expectedResponseList = Arrays.asList(expectedResponse1, expectedResponse2);

        when(departmentRepository.findAll()).thenReturn(departmentList);
        when(departmentMapper.fromEntityToResponse(department1)).thenReturn(expectedResponse1);
        when(departmentMapper.fromEntityToResponse(department2)).thenReturn(expectedResponse2);

        List<DepartmentResponse> actualResponseList = departmentService.getDepartments();

        assertEquals(expectedResponseList.size(), actualResponseList.size());
        for (int i = 0; i < expectedResponseList.size(); i++) {
            assertEquals(expectedResponseList.get(i).getDepartmentName(), actualResponseList.get(i).getDepartmentName());
            assertEquals(expectedResponseList.get(i).getId(), actualResponseList.get(i).getId());
        }
    }
}