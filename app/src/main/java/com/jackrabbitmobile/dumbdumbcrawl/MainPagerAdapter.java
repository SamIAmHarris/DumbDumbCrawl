package com.jackrabbitmobile.dumbdumbcrawl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by SamMyxer on 6/6/16.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    public static int PAGER_SIZE = 3;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListFragment();
            case 1:
                return new TwitterFragment();
            case 2:
                return new MapTabFragment();
            default:
                return new ListFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "List Lyfe";
            case 1:
                return "Tweet da Beat";
            case 2:
                return "Map Attack";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return PAGER_SIZE;
    }

}
