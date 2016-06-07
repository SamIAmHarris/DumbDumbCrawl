package com.jackrabbitmobile.dumbdumbcrawl;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by SamMyxer on 6/6/16.
 */
@IgnoreExtraProperties
public class Company {

    public static final int COMPANY_TYPE = 0;
    public static final int LOCATION_TYPE = 1;

    String name;
    int type;

    public Company() {
        // Default constructor required for calls to DataSnapshot.getValue(Company.class)
    }

    public Company(String name, int type) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
