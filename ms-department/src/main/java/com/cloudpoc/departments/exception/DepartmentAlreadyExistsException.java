package com.cloudpoc.departments.exception;

public class DepartmentAlreadyExistsException extends RuntimeException {
    public DepartmentAlreadyExistsException() {
        super("Department already exists");

    }
}
