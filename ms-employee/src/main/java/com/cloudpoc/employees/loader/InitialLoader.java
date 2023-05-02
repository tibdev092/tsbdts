package com.cloudpoc.employees.loader;

import com.cloudpoc.employees.model.Employee;
import com.cloudpoc.employees.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Log
public class InitialLoader implements CommandLineRunner {

    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Employee> employees =
                List.of(Employee.builder().firstName("Ismael")
                        .lastName("Sciarra")
                        .phoneNumber("515.124.4369")
                        .emailAddress("ISCIARRA@EMP.COM")
                        .salary(7700d)
                        .department("Administration")
                        .currentJob("Programmer").build(),
                Employee.builder().firstName("John")
                        .lastName("Chen")
                        .phoneNumber("515.124.4269")
                        .emailAddress("JCHEN@EMP.COM")
                        .salary(8000d)
                        .department("Marketing")
                        .currentJob("Marketing_Manager").build(),
                Employee.builder().firstName("Nancy")
                        .lastName("Greenberg")
                        .phoneNumber("515.124.4569")
                        .emailAddress("NGREENBE@EMP.COM")
                        .salary(8300d)
                        .department("Purchasing")
                        .currentJob("Public_Relations_Representative").build(),
                Employee.builder().firstName("Daniel")
                        .lastName("Faviet")
                        .phoneNumber("515.124.4169")
                        .emailAddress("DFAVIET@EMP.COM")
                        .salary(8100d)
                        .department("Human Resources")
                        .currentJob("Human_Resources_Representative").build(),
                Employee.builder().firstName("Neena")
                        .lastName("Kochhar")
                        .phoneNumber("515.123.4568")
                        .emailAddress("NKOCHHAR@EMP.COM")
                        .salary(10400d)
                        .department("Human Resources")
                        .currentJob("Public_Relations_Representative").build(),
                Employee.builder().firstName("Lex")
                        .lastName("De Haan")
                        .phoneNumber("515.123.4568")
                        .emailAddress("LDEHAAN@EMP.COM")
                        .salary(8000d)
                        .department("Human Resources")
                        .currentJob("Human_Resources_Representative").build(),
                Employee.builder().firstName("Alexander")
                        .lastName("Hunold")
                        .phoneNumber("590.423.4567")
                        .emailAddress("AHUNOLD@EMP.COM")
                        .salary(7500d)
                        .department("Human Resources")
                        .currentJob("Programmer").build(),
                Employee.builder().firstName("Bruce")
                        .lastName("Ernst")
                        .phoneNumber("590.423.4568")
                        .emailAddress("BERNST@EMP.COM")
                        .salary(6500d)
                        .department("Human Resources")
                        .currentJob("Human_Resources_Representative").build(),
                Employee.builder().firstName("David")
                        .lastName("Austin")
                        .phoneNumber("590.423.4569")
                        .emailAddress("DAUSTIN@EMP.COM")
                        .salary(14000d)
                        .department("Human Resources")
                        .currentJob("Marketing_Manager").build(),
                Employee.builder().firstName("Valli")
                        .lastName("Pataballa")
                        .phoneNumber("590.423.4560")
                        .emailAddress("VPATABAL@EMP.COM")
                        .salary(10000d)
                        .department("Human Resources")
                        .currentJob("Public_Relations_Representative").build(),
                Employee.builder().firstName("Diana")
                        .lastName("Lorentz")
                        .phoneNumber("590.423.5567")
                        .emailAddress("DLORENTZ@EMP.COM")
                        .salary(10000d)
                        .department("Human Resources")
                        .currentJob("Programmer").build());

        Set<String> existingEmployees = employeeRepository.findAll().stream().map(Employee::getEmailAddress).collect(Collectors.toSet());
        Set<String> insertingEmployees = employees.stream().map(Employee::getEmailAddress).collect(Collectors.toSet());
        insertingEmployees.removeAll(existingEmployees);

        log.info("Setup employees");

        employeeRepository.saveAll(employees.stream()
                .filter(t -> insertingEmployees.contains(t.getEmailAddress()))
                .collect(Collectors.toList()));
    }
}