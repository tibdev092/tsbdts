package com.banigeo.job.controller;

import com.banigeo.job.dto.JobHistoryResponse;
import com.banigeo.job.dto.JobRequest;
import com.banigeo.job.dto.JobResponse;
import com.banigeo.job.service.JobHistoryService;
import com.banigeo.job.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.List;

import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/job")
@AllArgsConstructor
@Validated
public class JobController {

    private JobService jobService;
    private JobHistoryService jobHistoryService;

    @GetMapping(value = "/list", produces = {"application/hal+json"})
    public CollectionModel<JobResponse> getJobs() {
        List<JobResponse> jobs = jobService.getAllJobs();
        for (final JobResponse job : jobs) {
            Link getJobLink = linkTo(methodOn(JobController.class).getJob(job.getTitle())).withRel("getJob");
            job.add(getJobLink);
        }
        Link link = linkTo(methodOn(JobController.class).getJobs()).withSelfRel();
        CollectionModel<JobResponse> result = CollectionModel.of(jobs, link);
        return result;
    }

    @GetMapping(value = "/list2", produces = {"application/json"})
    public List<JobResponse> getJobs2() {
        List<JobResponse> result = jobService.getAllJobs();
        return result;
    }

    @PostMapping(produces = {"application/hal+json"})
    public ResponseEntity<JobResponse> createJob(@Valid @RequestBody JobRequest job) {
        JobResponse savedJob = jobService.createJob(job);
        Link selfLink = linkTo(methodOn(JobController.class).getJob(savedJob.getTitle())).withRel("getJob");
        savedJob.add(selfLink);
        return ResponseEntity.created(URI.create("/job/" + savedJob.getTitle())).body(savedJob);
    }

    @GetMapping(value="/{title}/{salary}")
    public ResponseEntity<?> checkSalary(@PathVariable String title, @PathVariable Double salary) {
        jobService.checkSalaryRange(salary, title);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value="/history/{id}", produces = {"application/hal+json"})
    public List<JobHistoryResponse> getEmploymentHistory(@PathVariable Integer id) {
        return jobHistoryService.findEmploymentHistory(id);
    }

    @PostMapping(value="/{employeeId}/{jobName}/{salary}", produces = {"application/hal+json"})
    public ResponseEntity<?> saveHistoryForEmployee(@PathVariable Integer employeeId, @PathVariable String jobName, @PathVariable Double salary) {
        jobHistoryService.saveHistoryForEmployee(employeeId, jobName, salary);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "get Job by Name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "job found",
            content = {@Content(mediaType = "application/hal+json", schema = @Schema(implementation = JobResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Job not found", content = @Content)})
    @GetMapping(value = "/{name}",  produces = {"application/hal+json"})
    public ResponseEntity<JobResponse> getJob(@PathVariable String name) {
        Link selfLink = linkTo(methodOn(JobController.class).getJob(name)).withSelfRel();
        JobResponse job = jobService.getJob(name);
        job.add(selfLink);
        return ResponseEntity.ok(job);
    }

}
