package com.jackrabbitmobile.dumbdumbcrawl;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by SamMyxer on 6/9/16.
 */

public class StartingPointDialogFragment extends DialogFragment {

    @BindView(R.id.starting_point_dialog_relative_1)
    RelativeLayout startingPointRelativeLayout1;
    @BindView(R.id.starting_point_dialog_relative_2)
    RelativeLayout startingPointRelativeLayout2;
    @BindView(R.id.starting_point_dialog_relative_3)
    RelativeLayout startingPointRelativeLayout3;
    @BindView(R.id.starting_point_dialog_relative_4)
    RelativeLayout startingPointRelativeLayout4;
    @BindView(R.id.starting_point_dialog_relative_5)
    RelativeLayout startingPointRelativeLayout5;
    @BindView(R.id.starting_point_dialog_relative_6)
    RelativeLayout startingPointRelativeLayout6;

    @BindView(R.id.starting_point_dialog_checkbox_1)
    CheckBox startingPointCheckbox1;
    @BindView(R.id.starting_point_dialog_checkbox_2)
    CheckBox startingPointCheckbox2;
    @BindView(R.id.starting_point_dialog_checkbox_3)
    CheckBox startingPointCheckbox3;
    @BindView(R.id.starting_point_dialog_checkbox_4)
    CheckBox startingPointCheckbox4;
    @BindView(R.id.starting_point_dialog_checkbox_5)
    CheckBox startingPointCheckbox5;
    @BindView(R.id.starting_point_dialog_checkbox_6)
    CheckBox startingPointCheckbox6;

    @BindView(R.id.starting_point_text_view_1)
    TextView startingPointTextView1;
    @BindView(R.id.starting_point_dialog_text_view_2)
    TextView startingPointTextView2;
    @BindView(R.id.starting_point_dialog_text_view_3)
    TextView startingPointTextView3;
    @BindView(R.id.starting_point_dialog_text_view_4)
    TextView startingPointTextView4;
    @BindView(R.id.starting_point_dialog_text_view_5)
    TextView startingPointTextView5;
    @BindView(R.id.starting_point_dialog_text_view_6)
    TextView startingPointTextView6;

    @BindView(R.id.starting_point_dialog_done_tv)
    TextView doneTextView;

    private FirebaseRemoteConfig firebaseRemoteConfig;


    private int preselectedLocation;

    private String startingPoint1;
    private String startingPoint2;
    private String startingPoint3;
    private String startingPoint4;
    private String startingPoint5;
    private String startingPoint6;

    public static final String TAG = "startingpointdialog";

    public static final String EXTRA_PRESELECTED_LOCATION = "startingpointdialogfragment.preselected";

    public StartingPointDialogFragment() {
    }

    public static StartingPointDialogFragment newInstance(int preselectedLocation) {
        StartingPointDialogFragment frag = new StartingPointDialogFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_PRESELECTED_LOCATION, preselectedLocation);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preselectedLocation = getArguments().getInt(EXTRA_PRESELECTED_LOCATION);

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        firebaseRemoteConfig.setConfigSettings(configSettings);

        Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put(StartingPointPreferences.STARTING_POINT_1, "Atlassian");
        defaultConfigMap.put(StartingPointPreferences.STARTING_POINT_2, "Capital Factory");
        defaultConfigMap.put(StartingPointPreferences.STARTING_POINT_3, "UShip");
        defaultConfigMap.put(StartingPointPreferences.STARTING_POINT_4, "Maker Square");
        defaultConfigMap.put(StartingPointPreferences.STARTING_POINT_5, "Jackrabbit Mobile");
        defaultConfigMap.put(StartingPointPreferences.STARTING_POINT_6, "My House");

        firebaseRemoteConfig.setDefaults(defaultConfigMap);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialog_starting_location, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    private void initViews() {

        setAutoStartLocation();

        doneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnDone();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                firebaseRemoteConfig.fetch(0)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                firebaseRemoteConfig.activateFetched();
                                setStartingPointTitles();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                setStartingPointTitles();
                            }
                        });
            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    private void setCheckboxTextViews() {
        //get the text from the console and set them
        startingPointTextView1.setText(startingPoint1);
        startingPointTextView2.setText(startingPoint2);
        startingPointTextView3.setText(startingPoint3);
        startingPointTextView4.setText(startingPoint4);
        startingPointTextView5.setText(startingPoint5);
        startingPointTextView6.setText(startingPoint6);
    }


    private void handleOnDone () {
        getDialog().dismiss();
    }

    private void setStartingPointTitles() {
        startingPoint1 = firebaseRemoteConfig.getString(StartingPointPreferences.STARTING_POINT_1);
        startingPoint2 = firebaseRemoteConfig.getString(StartingPointPreferences.STARTING_POINT_2);
        startingPoint3 = firebaseRemoteConfig.getString(StartingPointPreferences.STARTING_POINT_3);
        startingPoint4 = firebaseRemoteConfig.getString(StartingPointPreferences.STARTING_POINT_4);
        startingPoint5 = firebaseRemoteConfig.getString(StartingPointPreferences.STARTING_POINT_5);
        startingPoint6 = firebaseRemoteConfig.getString(StartingPointPreferences.STARTING_POINT_6);

        setCheckboxTextViews();

    }

    public void setAutoStartLocation() {
        if(preselectedLocation != 0) {
            switch (preselectedLocation) {
                case 1:
                    startingPointCheckbox1.setChecked(true);
                    break;
                case 2:
                    startingPointCheckbox2.setChecked(true);
                    break;
                case 3:
                    startingPointCheckbox3.setChecked(true);
                    break;
                case 4:
                    startingPointCheckbox4.setChecked(true);
                    break;
                case 5:
                    startingPointCheckbox5.setChecked(true);
                    break;
                case 6:
                    startingPointCheckbox6.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }

    private class StartingPointPreferences {
        private static final String STARTING_POINT_1 = "starting_point_1";
        private static final String STARTING_POINT_2 = "starting_point_2";
        private static final String STARTING_POINT_3 = "starting_point_3";
        private static final String STARTING_POINT_4 = "starting_point_4";
        private static final String STARTING_POINT_5 = "starting_point_5";
        private static final String STARTING_POINT_6 = "starting_point_6";
    }

}
