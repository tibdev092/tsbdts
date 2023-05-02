package com.banigeo.job.mapper;

import com.banigeo.job.dto.JobRequest;
import com.banigeo.job.dto.JobResponse;
import com.banigeo.job.model.Job;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JobMapper {

    public JobResponse fromEntityToResponse(Job entity) {
        JobResponse jr = new JobResponse();//.builder()
        jr.setId(entity.getJobId());
        jr.setTitle(entity.getJobTitle().toString());
        jr.setMin(entity.getMinSalary());
        jr.setMax(entity.getMaxSalary());

        return jr;
    }


    public Job fromRequestToEntity(JobRequest request) {
        return Job.builder()
                .jobTitle(request.getTitle())
                .maxSalary(request.getMax())
                .minSalary(request.getMin())
                .build();
    }

}

