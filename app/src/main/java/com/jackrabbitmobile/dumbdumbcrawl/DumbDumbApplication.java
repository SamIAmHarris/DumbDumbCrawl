package com.jackrabbitmobile.dumbdumbcrawl;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by SamMyxer on 6/6/16.
 */

public class DumbDumbApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
