package com.cloudpoc.departments.service;

import com.cloudpoc.departments.dto.location.LocationRequest;
import com.cloudpoc.departments.dto.location.LocationResponse;
import com.cloudpoc.departments.mapper.LocationMapper;
import com.cloudpoc.departments.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;
    private LocationMapper locationMapper;

    public LocationResponse getLocation(int id) {
        return locationMapper.fromEntityToResponse(locationRepository.getById(id));
    }
    public List<LocationResponse> getLocations() {
        return locationRepository.findAll().stream().map(locationMapper::fromEntityToResponse).collect(Collectors.toList());
    }
    public LocationResponse createLocation(LocationRequest newLocation) {
        return locationMapper.fromEntityToResponse(locationRepository.save(locationMapper.fromRequestToEntity(newLocation)));
    }
}
