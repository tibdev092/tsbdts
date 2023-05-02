package com.cloudpoc.employees.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "DEPARTMENT")
public interface DepartmentProxy {
    @GetMapping(value = "/department/{name}", consumes = "application/json")
    void checkDepartment(@PathVariable String name);
}
