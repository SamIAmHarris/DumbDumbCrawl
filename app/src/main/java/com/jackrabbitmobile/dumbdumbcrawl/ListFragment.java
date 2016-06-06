package com.jackrabbitmobile.dumbdumbcrawl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;

/**
 * Created by SamMyxer on 6/6/16.
 */

public class ListFragment extends Fragment {

    DatabaseReference databaseReference;

    @BindView(R.id.fragment_list_recyclerview)
    RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        databaseReference = FirebaseDatabase.getInstance().getReference();



        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}
