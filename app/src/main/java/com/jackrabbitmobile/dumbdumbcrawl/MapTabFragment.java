package com.jackrabbitmobile.dumbdumbcrawl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by SamMyxer on 6/6/16.
 */

public class MapTabFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;

    GoogleMap googleMap;

    DatabaseReference databaseReference;

    ArrayList<Location> locations;

    boolean addedLocationsToMap = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, rootView);

        locations = new ArrayList<>();
        getMapFragment().getMapAsync(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("locations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                for(DataSnapshot locationSnapshot: dataSnapshots) {
                    Location location = locationSnapshot.getValue(Location.class);
                    locations.add(location);
                }
                if(googleMap != null && !addedLocationsToMap) {
                    addLocationsToMap();
                    addedLocationsToMap = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return rootView;
    }

    private void addLocationsToMap() {

        for(Location location: locations) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(
                    new LatLng(location.getLatitude(), location.getLongitude()));
            markerOptions.title(location.getName());

            googleMap.addMarker(markerOptions);
        }
    }

    private SupportMapFragment getMapFragment() {
        android.support.v4.app.FragmentManager fm = getChildFragmentManager();

        return (SupportMapFragment) fm.findFragmentById(R.id.map);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        if(locations.size() > 0 && !addedLocationsToMap) {
            addLocationsToMap();
            addedLocationsToMap = true;
        }
    }
}
