package com.cloudpoc.employees.mapper;


import com.cloudpoc.employees.dto.EmployeeResponse;
import com.cloudpoc.employees.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {

    public EmployeeResponse fromEntityToResponse(Employee employee) {
        if(employee == null) {
            return new EmployeeResponse();
        }

        return EmployeeResponse.builder()
                .id(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .job(employee.getCurrentJob())
                .department(employee.getDepartment())
                //.job(Objects.nonNull(employee.getCurrentJob()) ? employee.getCurrentJob().getJobTitle().toString() : null)
                //.department(Objects.nonNull(employee.getDepartment()) ? employee.getDepartment().getDepartmentName() : null)
                .salary(employee.getSalary())
                .email(employee.getEmailAddress())
                .phone(employee.getPhoneNumber())
                .build();
    }
}