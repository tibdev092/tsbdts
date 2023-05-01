package com.cloudpoc.departments.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "locations")
@Data
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    private String streetAddress;
    private String postalCode;
    private String city;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Location(Country country, String streetAddress, String postalCode, String city) {
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }
}
