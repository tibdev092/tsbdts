package com.cloudpoc.departments.service;

import com.cloudpoc.departments.dto.DepartmentRequest;
import com.cloudpoc.departments.dto.DepartmentResponse;
import com.cloudpoc.departments.exception.DepartmentAlreadyExistsException;
import com.cloudpoc.departments.exception.DepartmentNotFoundException;
import com.cloudpoc.departments.mapper.DepartmentMapper;
import com.cloudpoc.departments.model.Department;
import com.cloudpoc.departments.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;

    public DepartmentResponse getDepartment(String name) {
        Department dept = departmentRepository.findByDepartmentName(name).orElseThrow(DepartmentNotFoundException::new);
        return departmentMapper.fromEntityToResponse(dept);
    }

    public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll().stream().map(departmentMapper::fromEntityToResponse).collect(Collectors.toList());
    }

    public DepartmentResponse saveDepartment(DepartmentRequest request) {
        checkDepartment(request);
        Department dept = departmentRepository.save(departmentMapper.fromRequestToEntity(request));
        return departmentMapper.fromEntityToResponse(dept);
    }

    private void checkDepartment(DepartmentRequest request) {
        departmentRepository.findByDepartmentName(request.getDepartmentName())
                .ifPresent((Department d) -> { throw new DepartmentAlreadyExistsException(); });
    }
}
