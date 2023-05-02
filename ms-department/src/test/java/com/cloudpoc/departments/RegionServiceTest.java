package com.cloudpoc.departments;

import com.cloudpoc.departments.dto.region.RegionResponse;
import com.cloudpoc.departments.exception.RegionNotFoundException;
import com.cloudpoc.departments.mapper.RegionMapper;
import com.cloudpoc.departments.model.Region;
import com.cloudpoc.departments.repository.RegionRepository;
import com.cloudpoc.departments.service.RegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RegionServiceTest {
    private RegionService regionService;
    @Mock
    private RegionRepository regionRepository;
    @Mock
    private RegionMapper regionMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        regionService = new RegionService(regionRepository, regionMapper);
    }

    @Test
    public void testGetRegionReturnsCorrectRegion() {
        Region region = new Region();
        region.setRegionName("Region 1");
        when(regionRepository.findByRegionName(anyString())).thenReturn(Optional.of(region));

        RegionResponse expectedResponse = new RegionResponse();
        expectedResponse.setName("Region 1");
        when(regionMapper.fromEntityToResponse(region)).thenReturn(expectedResponse);

        RegionResponse actualResponse = regionService.getRegion("Region 1");

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testGetRegionThrowsExceptionWhenRegionNotFound() {
        when(regionRepository.findByRegionName(anyString())).thenReturn(Optional.empty());
        assertThrows(RegionNotFoundException.class, () -> regionService.getRegion("Nonexistent Region"));
    }

    @Test
    public void testGetRegionsReturnsAllRegions() {
        Region region1 = new Region();
        region1.setRegionName("Region 1");
        Region region2 = new Region();
        region2.setRegionName("Region 2");
        List<Region> regions = Arrays.asList(region1, region2);
        when(regionRepository.findAll()).thenReturn(regions);

        RegionResponse expectedResponse1 = new RegionResponse();
        expectedResponse1.setName("Region 1");
        RegionResponse expectedResponse2 = new RegionResponse();
        expectedResponse2.setName("Region 2");
        when(regionMapper.fromEntityToResponse(region1)).thenReturn(expectedResponse1);
        when(regionMapper.fromEntityToResponse(region2)).thenReturn(expectedResponse2);

        List<RegionResponse> actualResponses = regionService.getRegions();

        assertNotNull(actualResponses);
        assertEquals(2, actualResponses.size());
        assertEquals(expectedResponse1, actualResponses.get(0));
        assertEquals(expectedResponse2, actualResponses.get(1));
    }

    @Test
    public void testCreateRegionReturnsCorrectRegion() {
        Region regionToSave = new Region();
        regionToSave.setRegionName("New Region");
        when(regionMapper.fromRequestToEntity(anyString())).thenReturn(regionToSave);

        Region savedRegion = new Region();
        savedRegion.setRegionName("New Region");
        when(regionRepository.save(regionToSave)).thenReturn(savedRegion);

        RegionResponse expectedResponse = new RegionResponse();
        expectedResponse.setName("New Region");
        when(regionMapper.fromEntityToResponse(savedRegion)).thenReturn(expectedResponse);

        RegionResponse actualResponse = regionService.createRegion("New Region");

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

    }
}