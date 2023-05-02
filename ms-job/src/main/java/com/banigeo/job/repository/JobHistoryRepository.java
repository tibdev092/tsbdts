package com.banigeo.job.repository;

import com.banigeo.job.model.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Integer> {
    List<JobHistory> findAllByEmployeeId(Integer employeeId);
}