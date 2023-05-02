package com.cloudpoc.departments.mapper;

import com.cloudpoc.departments.dto.region.RegionResponse;
import com.cloudpoc.departments.model.Region;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegionMapper {
    public RegionResponse fromEntityToResponse(Region entity) {
        RegionResponse rr = new RegionResponse();
        rr.setId(entity.getRegionId());
        rr.setName(entity.getRegionName());
        return rr;
    }

    public Region fromRequestToEntity(String regionName) {
        return new Region(regionName);
    }
}
