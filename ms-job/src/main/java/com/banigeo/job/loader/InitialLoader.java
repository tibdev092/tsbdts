package com.banigeo.job.loader;

import com.banigeo.job.model.Job;
import com.banigeo.job.repository.JobHistoryRepository;
import com.banigeo.job.repository.JobRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Log
public class InitialLoader implements CommandLineRunner {
    JobRepository jobRepository;
    JobHistoryRepository jobHistoryRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Setup jobs");
        List<Job> jobs = Arrays.asList(
                new Job("Programmer", 4000d, 10000d),
                new Job("Marketing_Manager", 9000d, 15000d),
                new Job("Human_Resources_Representative", 4000d, 9000d),
                new Job("JobTitle.Public_Relations_Representative", 4500d, 10500d));

        Set<String> existingJobs = jobRepository.findAll().stream()
                .map(Job::getJobTitle)
                .collect(Collectors.toSet());
        Set<String> insertingJobs = jobs.stream().map(Job::getJobTitle)
                .collect(Collectors.toSet());
        insertingJobs.removeAll(existingJobs);

        jobRepository.saveAll(jobs.stream()
                .filter(t -> insertingJobs.contains(t.getJobTitle().toString()))
                .collect(Collectors.toList()));
    }
}