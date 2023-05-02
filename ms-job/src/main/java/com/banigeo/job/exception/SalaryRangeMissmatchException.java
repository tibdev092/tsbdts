package com.banigeo.job.exception;

public class SalaryRangeMissmatchException extends RuntimeException {
    public SalaryRangeMissmatchException() {
        super("Minimum salary has to be lower than Maximum salary");
    }
}
