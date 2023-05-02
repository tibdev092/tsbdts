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

import java.util.List;

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
        Mockito.when(employeeMapper.fromEntityToResponse(Mockito.any())).thenCallRealMethod();;
    }

    @Test
	void test_create_employee() {
        EmployeeRequest request = EmployeeRequest.builder().salary(200d).department("TEST-1").build();
        EmployeeResponse employee = employeeService.createEmployee(request);

        Assert.assertEquals(200, employee.getSalary().intValue());
        Assert.assertEquals("TEST-1", employee.getDepartment());
    }

    @Test
    void test_update_employee() {
        EmployeeRequest request = EmployeeRequest.builder().salary(500d).job("TEST").department("TEST").build();
        EmployeeResponse employee = employeeService.createEmployee(request);
        employee.setDepartment("TEST-2");
        EmployeeResponse employeeResponse = employeeService.updateEmployee(employee.getId(), employee);

        Assert.assertEquals("TEST-2", employeeResponse.getDepartment());
    }

    @Test
    public void test_find_employee() {
        EmployeeRequest request = EmployeeRequest.builder().salary(500d).job("TEST-3").department("TEST-3").build();
        EmployeeResponse employee = employeeService.createEmployee(request);
        EmployeeResponse foundEmployee = employeeService.findEmployee(employee.getId());

        Assert.assertEquals("TEST-3", foundEmployee.getDepartment());
        Assert.assertEquals("TEST-3", foundEmployee.getJob());
    }


    @Test
    void test_get_employees() {
        employeeService.createEmployee(EmployeeRequest.builder().salary(200d).firstName("Alex").lastName("Oana").build());
        employeeService.createEmployee(EmployeeRequest.builder().salary(300d).firstName("Costel").lastName("Iulian").build());
        employeeService.createEmployee(EmployeeRequest.builder().salary(400d).firstName("Ion").lastName("Oana").build());

        List<EmployeeResponse> employees = employeeService.getEmployees(null, "Oana");

        Assert.assertEquals(new String[]{"Alex", "Ion"}, employees.stream().map(EmployeeResponse::getFirstName).toArray(String[]::new));
    }

}
