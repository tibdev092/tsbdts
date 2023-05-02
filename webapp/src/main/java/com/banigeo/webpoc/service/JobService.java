package com.banigeo.webpoc.service;

import com.banigeo.webpoc.dto.job.JobRequest;
import com.banigeo.webpoc.dto.job.JobResponse;
import com.banigeo.webpoc.exception.job.JobAlreadyPresentException;
import com.banigeo.webpoc.exception.job.JobNotFoundException;
import com.banigeo.webpoc.exception.job.SalaryRangeMissmatchException;
import com.banigeo.webpoc.proxy.JobRestService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobService {

    private JobRestService jobRestService;

    public JobResponse getJob(String jobName) {
        return Optional.ofNullable(jobRestService.getJob(jobName).getBody())
                .orElseThrow(JobNotFoundException::new);
    }

    public List<JobResponse> getAllJobs() {
        return jobRestService.getJobs2();
    }

    public JobResponse createJob(JobRequest request) {
        checkJob(request);
        return jobRestService.createJob(request).getBody();
    }

    private boolean checkJob(JobRequest request) {
        if(request.getMax() < request.getMin()) {
            throw new SalaryRangeMissmatchException();
        }

        try {
            jobRestService.getJob(request.getTitle()).getBody();
        }
        catch(FeignException.NotFound fne) {
            return true;
        }

        throw new JobAlreadyPresentException();
    }
}
