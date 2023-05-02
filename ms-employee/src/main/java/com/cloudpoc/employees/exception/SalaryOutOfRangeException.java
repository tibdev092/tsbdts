package com.cloudpoc.employees.exception;

public class SalaryOutOfRangeException extends RuntimeException {
    public SalaryOutOfRangeException() {
        super("Salary out of bounds");
    }
}

