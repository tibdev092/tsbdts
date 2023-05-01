package com.cloudpoc.departments.repository;

import com.cloudpoc.departments.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Region> findByRegionName(String name);
}
