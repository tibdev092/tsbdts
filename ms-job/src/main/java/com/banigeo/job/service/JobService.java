package com.banigeo.job.service;

import com.banigeo.job.exception.JobNotFoundException;
import com.banigeo.job.exception.SalaryOutOfRangeException;
import com.banigeo.job.model.Job;
import com.banigeo.job.repository.JobRepository;
import com.banigeo.job.dto.JobRequest;
import com.banigeo.job.dto.JobResponse;
import com.banigeo.job.exception.JobAlreadyPresentException;
import com.banigeo.job.exception.SalaryRangeMissmatchException;
import com.banigeo.job.mapper.JobMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobService {

    private JobRepository jobRepository;
    private JobMapper jobMapper;

    public JobResponse getJob(String jobName) {
        Job job = jobRepository.findByJobTitle(jobName).orElseThrow(JobNotFoundException::new);
        return jobMapper.fromEntityToResponse(job);
    }

    public void checkSalaryRange(Double salary, String jobTitle) {
        //chek if employee has a salary in range with assigned job title
        jobRepository.findJobsBySalaryRange(salary)
                .stream()
                .filter(t -> jobTitle.equals(t.getJobTitle()))
                .findFirst().orElseThrow(SalaryOutOfRangeException::new);
    }

    public JobResponse getJob(Integer jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        return jobMapper.fromEntityToResponse(job);
    }

    public List<Job> getJobsBySalaryRange(Double salary) {
        return jobRepository.findJobsBySalaryRange(salary);
    }

    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll().stream().map(jobMapper::fromEntityToResponse).collect(Collectors.toList());
    }

    public JobResponse createJob(JobRequest request) {
        checkJob(request);

        Job savedJob = jobRepository.save(jobMapper.fromRequestToEntity(request));
        return jobMapper.fromEntityToResponse(savedJob);
    }

    private void checkJob(JobRequest request) {
        if(request.getMax() < request.getMin()) {
            throw new SalaryRangeMissmatchException();
        }
        jobRepository.findByJobTitle(request.getTitle())
                .ifPresent((Job j) -> { throw new JobAlreadyPresentException(); });
    }
}
