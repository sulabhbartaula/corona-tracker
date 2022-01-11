package com.sulabh.coronatracker.models;

import lombok.Data;

@Data
public class LocationData {

    private String state;
    private String country;
    private int latestTotalCases;

}
