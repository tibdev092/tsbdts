package com.cloudpoc.employees.exception;

public class DuplicateEmailAddressException extends RuntimeException {
    public DuplicateEmailAddressException() {
        super("E-mail address already exists");
    }
}
