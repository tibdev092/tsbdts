package com.banigeo.webpoc.exception.department.region;

public class RegionNotFoundException extends RuntimeException{
    public RegionNotFoundException() {
        super("Region not found");

    }
}
