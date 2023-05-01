package com.cloudpoc.departments.repository;

import com.cloudpoc.departments.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findByCountryName(String name);
}