package com.cloudpoc.employees.model;

import lombok.*;
import javax.persistence.*;

@Entity(name = "employees")
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private Double salary;

    //@OneToOne
    //@JoinColumn(name = "department_id")
    private String department;

    //@OneToOne
    //@JoinColumn(name = "job_id")
    private String currentJob;
}

