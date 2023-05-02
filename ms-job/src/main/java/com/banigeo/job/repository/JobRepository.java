package com.banigeo.job.repository;

import com.banigeo.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Optional<Job> findByJobTitle(String jobName);


    @Query(value = "select j.* from jobs j where :salary between j.min_salary and j.max_salary", nativeQuery = true)
    List<Job> findJobsBySalaryRange(Double salary);
}