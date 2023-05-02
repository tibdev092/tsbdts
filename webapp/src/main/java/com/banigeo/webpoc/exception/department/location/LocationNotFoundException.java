package com.banigeo.webpoc.exception.department.location;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException() {
        super("Location not found");

    }
}