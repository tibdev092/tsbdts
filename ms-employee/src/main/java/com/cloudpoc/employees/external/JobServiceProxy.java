package com.cloudpoc.employees.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "JOB")
public interface JobServiceProxy {
    @GetMapping(value="/job/{title}/{salary}", consumes = "application/json")
    void checkSalary(@PathVariable String title, @PathVariable Double salary);

    @PostMapping(value="/job/{employeeId}/{jobName}/{salary}", consumes = "application/json")
    ResponseEntity<?> saveHistoryForEmployee(@PathVariable Integer employeeId, @PathVariable String jobName, @PathVariable Double salary);
}
