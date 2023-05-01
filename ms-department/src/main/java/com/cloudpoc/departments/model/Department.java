package com.cloudpoc.departments.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "departments")
@Data
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;

    private String departmentName;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "department")  //, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations;

    public Department(String departmentName, Location location) {
        this.departmentName = departmentName;
        this.location = location;
    }
}

