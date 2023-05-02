package com.cloudpoc.departments.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException() {
        super("Department not found");

    }
}
