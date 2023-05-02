package com.cloudpoc.departments.exception;

public class CountryNotFoundException extends RuntimeException  {
    public CountryNotFoundException() {
        super("Country not found");

    }
}
