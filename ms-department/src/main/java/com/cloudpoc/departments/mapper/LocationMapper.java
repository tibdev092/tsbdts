package com.cloudpoc.departments.mapper;

import com.cloudpoc.departments.dto.location.LocationRequest;
import com.cloudpoc.departments.dto.location.LocationResponse;
import com.cloudpoc.departments.model.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationMapper {
    public LocationResponse fromEntityToResponse(Location entity) {
        LocationResponse lr = new LocationResponse();
        lr.setId(entity.getLocationId());
        lr.setCountry(entity.getCountry());
        lr.setDepartment(entity.getDepartment());
        lr.setCity(entity.getCity());
        lr.setPostalCode(entity.getPostalCode());
        lr.setStreetAddress(entity.getStreetAddress());
        return lr;
    }

    public Location fromRequestToEntity(LocationRequest request) {
        return new Location(request.getCountry(), request.getStreetAddress(), request.getPostalCode(), request.getCity());
    }
}
