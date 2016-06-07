package com.jackrabbitmobile.dumbdumbcrawl;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by SamMyxer on 6/7/16.
 */
@IgnoreExtraProperties
public class Location {

    String name;
    int type;
    double latitude;
    double longitude;

    public Location() {
    }

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
