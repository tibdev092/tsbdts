package com.cloudpoc.departments.exception;

public class RegionNotFoundException extends RuntimeException{
    public RegionNotFoundException() {
        super("Region not found");

    }
}
