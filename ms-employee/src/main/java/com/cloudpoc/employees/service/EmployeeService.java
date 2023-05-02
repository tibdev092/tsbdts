package com.cloudpoc.employees.service;

import com.cloudpoc.employees.dto.EmployeeRequest;
import com.cloudpoc.employees.dto.EmployeeResponse;
import com.cloudpoc.employees.exception.DuplicateEmailAddressException;
import com.cloudpoc.employees.exception.DuplicatePhoneNumberException;
import com.cloudpoc.employees.exception.EmployeeNotFoundException;
import com.cloudpoc.employees.external.DepartmentProxy;
import com.cloudpoc.employees.external.JobServiceProxy;
import com.cloudpoc.employees.mapper.EmployeeMapper;
import com.cloudpoc.employees.model.Employee;
import com.cloudpoc.employees.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private JobServiceProxy jobServiceProxy;
    private DepartmentProxy departmentProxy;

    private enum Action {CREATE, UPDATE}

    @CircuitBreaker(name="jobProxy", fallbackMethod = "checkSalaryFallback")
    private void checkSalary(EmployeeRequest request) {
        jobServiceProxy.checkSalary(request.getJob(), request.getSalary());
    }

    @CircuitBreaker(name="deptProxy", fallbackMethod = "checkDepartmentFallback")
    private void checkDepartment(EmployeeRequest request) {
        departmentProxy.checkDepartment(request.getDepartment());
    }

    private void checkSalaryFallback(EmployeeRequest employee, Throwable throwable) {
        log.warn("Could not verify salary for {} reason {}",employee.toString(), throwable.getMessage());
    }
    private void checkDepartmentFallback(EmployeeRequest employee, Throwable throwable) {
        log.warn("Could not verify dept for {} reason {}",employee.toString(), throwable.getMessage());
    }

    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = fromRequestToEntity(request);
        checkSalary(request);
        checkDepartment(request);
        checkUniqueness(request, employee, Action.CREATE);
        Employee savedEmployee = employeeRepository.save(employee);
        jobServiceProxy.saveHistoryForEmployee(employee.getEmployeeId(), employee.getCurrentJob(), employee.getSalary());
        return employeeMapper.fromEntityToResponse(savedEmployee);
    }

    public EmployeeResponse createEmployeeWithoutProxies(EmployeeRequest request) {
        Employee employee = fromRequestToEntity(request);
        checkUniqueness(request, employee, Action.CREATE);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.fromEntityToResponse(savedEmployee);
    }

    private Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public EmployeeResponse findEmployee(Integer id) {
        return employeeMapper.fromEntityToResponse(getEmployee(id));
    }

    public List<EmployeeResponse> getEmployees(String firstName, String lastName) {
        Optional<String> firstValue = Optional.ofNullable(firstName);
        Optional<String> lastValue = Optional.ofNullable(lastName);
        return employeeRepository.findAll().stream()
                .filter(t -> (!firstValue.isPresent() || firstValue.get().equals(t.getFirstName())))
                .filter(e -> (!lastValue.isPresent() || lastValue.get().equals(e.getLastName())))
                .map(employeeMapper::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    public Employee fromRequestToEntity(EmployeeRequest request) {

        return Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .currentJob(request.getJob())
                .department(request.getDepartment())
                .salary(request.getSalary())
                .emailAddress(request.getEmail())
                .phoneNumber(request.getPhone())
                .build();
    }

    @Transactional
    public EmployeeResponse updateEmployee(Integer id, EmployeeRequest updateEmployeeRequest) {
        Employee oldEmployee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        Employee newEmployee = fromRequestToEntity(updateEmployeeRequest);
        newEmployee.setEmployeeId(id);
        jobServiceProxy.checkSalary(updateEmployeeRequest.getJob(), updateEmployeeRequest.getSalary());
        departmentProxy.checkDepartment(updateEmployeeRequest.getDepartment());
        checkUniqueness(updateEmployeeRequest, oldEmployee, Action.UPDATE);
        /*
        audit only when job or salary has changed
       */
        if (!newEmployee.getCurrentJob().equals(oldEmployee.getCurrentJob())
                || !newEmployee.getSalary().equals(oldEmployee.getSalary())) {
            jobServiceProxy.saveHistoryForEmployee(newEmployee.getEmployeeId(), newEmployee.getCurrentJob(), newEmployee.getSalary());
        }
        Employee updatedEmployee = employeeRepository.save(newEmployee);
        return employeeMapper.fromEntityToResponse(updatedEmployee);
    }

    private void checkUniqueness(EmployeeRequest newEmployee, Employee oldEmployee, Action action) {
        String newEmailAddress = newEmployee.getEmail();
        String oldEmailAddress = oldEmployee.getEmailAddress();

        if (Objects.nonNull(newEmailAddress)) {
            Optional<Employee> foundEmail = employeeRepository.findByEmailAddress(newEmployee.getEmail());
            switch (action) {
                case CREATE:
                    if (foundEmail.isPresent())
                        throw new DuplicateEmailAddressException();
                    break;
                case UPDATE:
                    if (!newEmailAddress.equals(oldEmailAddress) && foundEmail.isPresent())
                        throw new DuplicateEmailAddressException();
                    break;
                default:
                    throw new RuntimeException("Unknown action " + action);
            }

            String newPhoneNumber = newEmployee.getPhone();
            String oldPhoneNumber = oldEmployee.getPhoneNumber();
            if (Objects.nonNull(newPhoneNumber)) {
                Optional<Employee> foundPhone = employeeRepository.findByPhoneNumber(newEmployee.getPhone());
                switch (action) {
                    case CREATE:
                        if (foundPhone.isPresent())
                            throw new DuplicatePhoneNumberException();
                        break;
                    case UPDATE:
                        if (!newPhoneNumber.equals(oldPhoneNumber) && foundPhone.isPresent())
                            throw new DuplicatePhoneNumberException();
                        break;
                    default:
                        throw new RuntimeException("Unknown action " + action);
                }
            }
        }
    }
}
