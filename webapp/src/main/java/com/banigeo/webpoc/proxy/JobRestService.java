package com.banigeo.webpoc.proxy;

import com.banigeo.webpoc.dto.history.JobHistoryResponse;
import com.banigeo.webpoc.dto.job.JobRequest;
import com.banigeo.webpoc.dto.job.JobResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "JOB")
public interface JobRestService {
    @PostMapping(value="/job/{employeeId}/{jobName}/{salary}")
    ResponseEntity<?> saveHistoryForEmployee(@PathVariable Integer employeeId, @PathVariable String jobName, @PathVariable Double salary);

    @GetMapping(value="/job/history/{id}")
    List<JobHistoryResponse> getEmploymentHistory(@PathVariable Integer id);

    @GetMapping(value = "/job/list2")
    List<JobResponse> getJobs2();

    @PostMapping(value = "/job")
    ResponseEntity<JobResponse> createJob(@RequestBody JobRequest job);

    @GetMapping(value = "/job/{name}")
    ResponseEntity<JobResponse> getJob(@PathVariable String name);
}
