package com.cloudpoc.departments.service;

import com.cloudpoc.departments.dto.region.RegionResponse;
import com.cloudpoc.departments.exception.RegionNotFoundException;
import com.cloudpoc.departments.mapper.RegionMapper;
import com.cloudpoc.departments.model.Region;
import com.cloudpoc.departments.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegionService {
    private RegionRepository regionRepository;
    private RegionMapper regionMapper;

    public RegionResponse getRegion(String name) {
        Region region = regionRepository.findByRegionName(name).orElseThrow(RegionNotFoundException::new);
        return regionMapper.fromEntityToResponse(region);
    }
    public List<RegionResponse> getRegion() {
        return regionRepository.findAll().stream().map(regionMapper::fromEntityToResponse).collect(Collectors.toList());
    }
    public RegionResponse createRegion(String regionName) {
        return regionMapper.fromEntityToResponse(regionRepository.save(regionMapper.fromRequestToEntity(regionName)));
    }
}
