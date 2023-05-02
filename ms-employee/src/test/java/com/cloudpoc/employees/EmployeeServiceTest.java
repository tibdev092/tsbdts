package com.cloudpoc.employees;

import com.cloudpoc.employees.dto.EmployeeRequest;
import com.cloudpoc.employees.dto.EmployeeResponse;
import com.cloudpoc.employees.external.JobServiceProxy;
import com.cloudpoc.employees.mapper.EmployeeMapper;
import com.cloudpoc.employees.model.Employee;
import com.cloudpoc.employees.repository.EmployeeRepository;
import com.cloudpoc.employees.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeMapper employeeMapper;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void findTest() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(Employee.builder().firstName("TEST").build()));

        List<EmployeeResponse> sliceOfEmployees = employeeService.getEmployees("TEST",null);
        assertEquals(sliceOfEmployees.size(),1);
    }


    @Test
    public void getEmployee() {
        List<Employee> mockedEmployees = List.of(Employee.builder().firstName("alex").lastName("constantin").build(),
                Employee.builder().firstName("george").lastName("banica").build());
        when(employeeRepository.findAll()).thenReturn(mockedEmployees);
        List<EmployeeResponse> returnedEmployees = employeeService.getEmployees("george", "banica");
        assertEquals(1, returnedEmployees.size());
        verify(employeeMapper, times(1)).fromEntityToResponse(any());
    }
}

