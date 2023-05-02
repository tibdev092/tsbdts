package com.banigeo.job.exception;

public class JobAlreadyPresentException extends RuntimeException {
    public JobAlreadyPresentException() {
        super("Job title is already present");
    }
}

