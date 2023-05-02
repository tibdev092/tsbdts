package com.cloudpoc.departments.controller;


import com.cloudpoc.departments.dto.DepartmentRequest;
import com.cloudpoc.departments.dto.DepartmentResponse;
import com.cloudpoc.departments.dto.country.CountryRequest;
import com.cloudpoc.departments.dto.country.CountryResponse;
import com.cloudpoc.departments.dto.location.LocationRequest;
import com.cloudpoc.departments.dto.location.LocationResponse;
import com.cloudpoc.departments.dto.region.RegionResponse;
import com.cloudpoc.departments.service.CountryService;
import com.cloudpoc.departments.service.DepartmentService;
import com.cloudpoc.departments.service.LocationService;
import com.cloudpoc.departments.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/department")
@Validated
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;
    private CountryService countryService;
    private RegionService regionService;
    private LocationService locationService;

    @GetMapping("/{name}")
    public ResponseEntity<DepartmentResponse> getDepartment(@PathVariable String name) {
        return ResponseEntity.ok(departmentService.getDepartment(name));
    }

    @GetMapping("/list")
    public List<DepartmentResponse> getDepartments() {
        return departmentService.getDepartments();
    }
    /*
        {
        "departmentId": 1,
        "departmentName": "Administration",
        "location": {
            "locationId": 1,
            "streetAddress": "1297 Via Cola di Rie",
            "postalCode": "00989",
            "city": "Roma",
            "country": {
                "countryId": 1,
                "region": {
                    "regionId": 1,
                    "name": "Europe"
                },
                "name": "Italy"
            },
            "department": null
        },
        "locations": []
    },
     */

    @PostMapping("/create")
    public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest department) {
        DepartmentResponse savedDepartment = departmentService.saveDepartment(department);
        return ResponseEntity.created(URI.create("/department/" + savedDepartment.getDepartmentName())).body(savedDepartment);
    }

    @GetMapping("/country/list")
    public List<CountryResponse> getCountries() {
        return countryService.getCountries();
    }

    @GetMapping("/country/{name}")
    public CountryResponse getCountry(@PathVariable String name) {
        return countryService.getCountry(name);
    }

    @PostMapping("/country/create")
    public ResponseEntity<CountryResponse> createCountry(@Valid @RequestBody CountryRequest country) {
        CountryResponse savedCountry = countryService.createCountry(country);
        return ResponseEntity.created(URI.create("/department/country" + savedCountry.getName())).body(savedCountry);
    }

    @GetMapping("/location/list")
    public List<LocationResponse> getLocations() {
        return locationService.getLocations();
    }

    @GetMapping("/location/{id}")
    public LocationResponse getLocation(@PathVariable int id) {
        return locationService.getLocation(id);
    }

    @PostMapping("/location/create")
    public ResponseEntity<LocationResponse> createLocation(@Valid @RequestBody LocationRequest location) {
        LocationResponse saveLocation = locationService.createLocation(location);
        return ResponseEntity.created(URI.create("/department/location/" + saveLocation.getId())).body(saveLocation);
    }

    @GetMapping("/region/list")
    public List<RegionResponse> getRegions() {
        return regionService.getRegion();
    }

    @GetMapping("/region/{name}")
    public RegionResponse getRegion(@PathVariable String name) {
        return regionService.getRegion(name);
    }

    @PostMapping("/region/create/{name}")
    public ResponseEntity<RegionResponse> createRegion(@PathVariable String name) {
        RegionResponse savedRegion = regionService.createRegion(name);
        return ResponseEntity.created(URI.create("/department/region/" + savedRegion.getName())).body(savedRegion);
    }
}