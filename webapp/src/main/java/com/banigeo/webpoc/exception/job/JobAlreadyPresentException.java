package com.banigeo.webpoc.exception.job;

public class JobAlreadyPresentException extends RuntimeException {
    public JobAlreadyPresentException() {
        super("Job title is already present");
    }
}
