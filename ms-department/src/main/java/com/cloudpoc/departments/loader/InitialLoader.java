package com.cloudpoc.departments.loader;

import com.cloudpoc.departments.model.Country;
import com.cloudpoc.departments.model.Department;
import com.cloudpoc.departments.model.Location;
import com.cloudpoc.departments.model.Region;
import com.cloudpoc.departments.repository.CountryRepository;
import com.cloudpoc.departments.repository.DepartmentRepository;
import com.cloudpoc.departments.repository.LocationRepository;
import com.cloudpoc.departments.repository.RegionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Log
public class InitialLoader implements CommandLineRunner {

    RegionRepository regionRepository;
    DepartmentRepository departmentRepository;
    LocationRepository locationRepository;
    CountryRepository countryRepository;

    @Override
    public void run(String... args) throws Exception {
        // initial setup
        List<Region> regions = Arrays.asList(new Region("Europe"),
                new Region("Americas"),
                new Region("Asia"),
                new Region("Middle East and Africa"));

        log.info("Setup regions ");

        Set<String> existingRegions = regionRepository.findAll().stream().map(Region::getRegionName).collect(Collectors.toSet());
        Set<String> insertingRegions = regions.stream().map(Region::getRegionName).collect(Collectors.toSet());
        insertingRegions.removeAll(existingRegions);

        regionRepository.saveAll(regions.stream()
                .filter(t -> insertingRegions.contains(t.getRegionName()))
                .collect(Collectors.toList()));

        log.info("Setup countries ");

        List<Country> countries = Arrays.asList(new Country(regions.get(0), "Italy")
                , new Country(regions.get(2), "Japan")
                , new Country(regions.get(1), "USA")
                , new Country(regions.get(3), "Egypt"));

        Set<String> existingCountries = countryRepository.findAll().stream().map(Country::getCountryName).collect(Collectors.toSet());
        Set<String> insertingCountries = countries.stream().map(Country::getCountryName).collect(Collectors.toSet());
        insertingCountries.removeAll(existingCountries);

        countryRepository.saveAll(countries.stream()
                .filter(t-> insertingCountries.contains(t.getCountryName()))
                .collect(Collectors.toList()));

        log.info("Setup locations ");

        List<Location> locations = Arrays.asList(
                new Location(countries.get(0), "1297 Via Cola di Rie", "00989", "Roma")
                , new Location(countries.get(1), "2017 Shinjuku-ku", "1689", "Tokyo")
                , new Location(countries.get(2), "2014 Jabberwocky Rd", "26192", "San Francisco")
                , new Location(countries.get(3), "46 Mohammed El Kouly", "54534", "Cairo")
        );

        Set<String> existingLocations = locationRepository.findAll().stream().map(Location::getPostalCode).collect(Collectors.toSet());
        Set<String> insertingLocations = locations.stream().map(Location::getPostalCode).collect(Collectors.toSet());
        insertingLocations.removeAll(existingLocations);

        locationRepository.saveAll(locations.stream()
                .filter(t -> insertingLocations.contains(t.getPostalCode()))
                .collect(Collectors.toList()));

        log.info("Setup departments ");

        List<Department> departments = Arrays.asList(
                new Department("Administration", locations.get(0))
                , new Department("Marketing", locations.get(1))
                , new Department("Purchasing", locations.get(2))
                , new Department("Human Resources", locations.get(3)));

        Set<String> existingDepartments = departmentRepository.findAll().stream().map(Department::getDepartmentName).collect(Collectors.toSet());
        Set<String> insertingDepartments = departments.stream().map(Department::getDepartmentName).collect(Collectors.toSet());
        insertingDepartments.removeAll(existingDepartments);

        departmentRepository.saveAll(departments.stream()
                .filter(t -> insertingDepartments.contains(t.getDepartmentName()))
                .collect(Collectors.toList()));
    }
}
