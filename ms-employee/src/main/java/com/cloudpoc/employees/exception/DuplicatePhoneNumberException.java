package com.cloudpoc.employees.exception;

public class DuplicatePhoneNumberException extends RuntimeException {
    public DuplicatePhoneNumberException() {
        super("Phone number already exists");
    }
}
