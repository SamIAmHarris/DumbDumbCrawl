package com.jackrabbitmobile.dumbdumbcrawl;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.FacebookSdk;

/**
 * Created by SamMyxer on 6/6/16.
 */

public class DumbDumbApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

