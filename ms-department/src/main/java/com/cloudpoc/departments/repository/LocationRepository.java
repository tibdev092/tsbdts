package com.cloudpoc.departments.repository;

import com.cloudpoc.departments.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}

