package com.banigeo.job.exception;

public class SalaryOutOfRangeException extends RuntimeException {
    public SalaryOutOfRangeException() {
        super("Salary out of bounds");
    }
}
