package com.jackrabbitmobile.dumbdumbcrawl;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabActivity extends AppCompatActivity {

    @BindView(R.id.activity_tab_viewpager)
    ViewPager viewPager;
    @BindView(R.id.activity_tab_tabLayout)
    TabLayout tabLayout;
    MainPagerAdapter mainPagerAdapter;

    int autoLocation = 0;

    public static final String AUTO_LOCATION_EXTRA = "tabactivity.auto_location_extra";

    public static Intent newIntent(Context context, int autoLocation) {
        Intent tabIntent = new Intent(context, TabActivity.class);
        tabIntent.putExtra(AUTO_LOCATION_EXTRA, autoLocation);
        return tabIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);

        autoLocation = getIntent().getIntExtra(AUTO_LOCATION_EXTRA, 0);

        showSelectStartingLocationDialog();

        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private void showSelectStartingLocationDialog() {
        FragmentManager fm = getSupportFragmentManager();
        StartingPointDialogFragment startingPointDialogFragment =
                StartingPointDialogFragment.newInstance(autoLocation);
        startingPointDialogFragment.show(fm, StartingPointDialogFragment.TAG);
    }
}
