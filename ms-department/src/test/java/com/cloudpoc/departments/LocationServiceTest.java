package com.cloudpoc.departments;

import com.cloudpoc.departments.dto.location.LocationRequest;
import com.cloudpoc.departments.dto.location.LocationResponse;
import com.cloudpoc.departments.mapper.LocationMapper;
import com.cloudpoc.departments.model.Location;
import com.cloudpoc.departments.repository.LocationRepository;
import com.cloudpoc.departments.service.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class LocationServiceTest {

    private LocationService locationService;

    @Mock
    private LocationRepository locationRepositoryMock;

    @Mock
    private LocationMapper locationMapperMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        locationService = new LocationService(locationRepositoryMock, locationMapperMock);
    }

    @Test
    public void testGetLocationReturnsLocationResponse() {
        int locationId = 1;
        Location location = new Location();
        LocationResponse locationResponse = new LocationResponse();
        when(locationRepositoryMock.getById(locationId)).thenReturn(location);
        when(locationMapperMock.fromEntityToResponse(location)).thenReturn(locationResponse);

        LocationResponse result = locationService.getLocation(locationId);

        Assertions.assertSame(result, locationResponse);
        verify(locationRepositoryMock, times(1)).getById(locationId);
        verify(locationMapperMock, times(1)).fromEntityToResponse(location);
    }

    @Test
    public void testGetLocationsReturnsListLocationResponse() {
        Location location1 = new Location();
        Location location2 = new Location();
        LocationResponse locationResponse1 = new LocationResponse();
        LocationResponse locationResponse2 = new LocationResponse();
        List<Location> locations = Arrays.asList(location1, location2);
        List<LocationResponse> locationResponses = Arrays.asList(locationResponse1, locationResponse2);
        when(locationRepositoryMock.findAll()).thenReturn(locations);
        when(locationMapperMock.fromEntityToResponse(location1)).thenReturn(locationResponse1);
        when(locationMapperMock.fromEntityToResponse(location2)).thenReturn(locationResponse2);

        List<LocationResponse> results = locationService.getLocations();

        Assertions.assertSame(results.size(), locationResponses.size());
    }

    @Test
    public void testCreateLocationReturnsLocationResponse() {
        LocationRequest locationRequest = new LocationRequest();
        Location location = new Location();
        LocationResponse locationResponse = new LocationResponse();
        when(locationMapperMock.fromRequestToEntity(locationRequest)).thenReturn(location);
        when(locationRepositoryMock.save(location)).thenReturn(location);
        when(locationMapperMock.fromEntityToResponse(location)).thenReturn(locationResponse);

        LocationResponse result = locationService.createLocation(locationRequest);

        Assertions.assertSame(result, locationResponse);
        verify(locationMapperMock, times(1)).fromRequestToEntity(locationRequest);
        verify(locationRepositoryMock, times(1)).save(location);
        verify(locationMapperMock, times(1)).fromEntityToResponse(location);
    }
}
