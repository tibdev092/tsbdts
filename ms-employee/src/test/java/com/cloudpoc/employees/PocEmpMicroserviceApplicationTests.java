package com.cloudpoc.employees;

import com.cloudpoc.employees.dto.EmployeeRequest;
import com.cloudpoc.employees.dto.EmployeeResponse;
import com.cloudpoc.employees.external.DepartmentProxy;
import com.cloudpoc.employees.external.JobServiceProxy;
import com.cloudpoc.employees.mapper.EmployeeMapper;
import com.cloudpoc.employees.repository.EmployeeRepository;
import com.cloudpoc.employees.service.EmployeeService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class PocEmpMicroserviceApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;
    @MockBean
    private JobServiceProxy jobServiceProxy;
    @MockBean
    private DepartmentProxy departmentProxy;

    private EmployeeService employeeService;

    @BeforeEach
    public  void init() {
        employeeService = new EmployeeService(employeeRepository, employeeMapper, jobServiceProxy, departmentProxy);
        //Mockito.when(employeeMapper.fromEntityToResponse(Mockito.any())).thenReturn(request);

    }

    @Test
	void test_create_employee() {

        EmployeeRequest request = EmployeeRequest.builder().salary(200d).department("TEST").build();
        EmployeeResponse employee = employeeService.createEmployee(request);

        Assert.assertEquals(200d, employee.getSalary().doubleValue());
        Assert.assertEquals("TEST", employee.getDepartment());
        //System.out.println(employee);
    }

}
