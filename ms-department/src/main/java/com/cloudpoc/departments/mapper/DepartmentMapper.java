package com.cloudpoc.departments.mapper;

import com.cloudpoc.departments.dto.DepartmentRequest;
import com.cloudpoc.departments.dto.DepartmentResponse;
import com.cloudpoc.departments.model.Department;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartmentMapper {

    public DepartmentResponse fromEntityToResponse(Department entity) {
        DepartmentResponse dr = new DepartmentResponse();
        dr.setId(entity.getDepartmentId());
        dr.setDepartmentName(entity.getDepartmentName());
        dr.setLocation(entity.getLocation());
        return dr;
    }

    public Department fromRequestToEntity(DepartmentRequest request) {
        return new Department(request.getDepartmentName(), request.getLocation());
    }
}
