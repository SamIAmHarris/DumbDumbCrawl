package com.jackrabbitmobile.dumbdumbcrawl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamMyxer on 6/6/16.
 */

public class ListFragment extends Fragment {

    private static final String TAG = "ListFragment";

    DatabaseReference databaseReference;

    @BindView(R.id.fragment_list_recyclerview)
    RecyclerView recyclerView;

    ArrayList<Company> companies;
    CompanyAdapter companyAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rootView);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        companies = new ArrayList<>();
        companyAdapter =  new CompanyAdapter(companies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(companyAdapter);


        databaseReference.child("companies").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, dataSnapshot.toString());
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                for(DataSnapshot companySnapShot: dataSnapshots) {
                    Company company = companySnapShot.getValue(Company.class);
                    companies.add(company);
                }
                companyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("locations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, dataSnapshot.toString());
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                for(DataSnapshot locationSnapshot: dataSnapshots) {
                    Company company = locationSnapshot.getValue(Company.class);
                    companies.add(company);
                }
                companyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }
}
