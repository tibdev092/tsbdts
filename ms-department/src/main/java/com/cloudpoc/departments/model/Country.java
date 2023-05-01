package com.cloudpoc.departments.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "countries")
@Data
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;

    //relations
    @OneToOne
    @JoinColumn(name = "region_id")
    private Region region;

    String countryName;

    public Country(Region region, String name) {
        this.region = region;
        this.countryName = name;
    }
}
