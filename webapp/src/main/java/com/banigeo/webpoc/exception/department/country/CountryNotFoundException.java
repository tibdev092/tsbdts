package com.banigeo.webpoc.exception.department.country;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException() {
        super("Country not found");

    }
}
