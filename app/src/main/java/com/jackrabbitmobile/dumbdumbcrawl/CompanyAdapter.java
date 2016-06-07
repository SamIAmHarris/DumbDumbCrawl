package com.jackrabbitmobile.dumbdumbcrawl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by SamMyxer on 6/6/16.
 */

public class CompanyAdapter extends RecyclerView.Adapter<CompanyHolder> {

    ArrayList<Company> companies;

    public CompanyAdapter(ArrayList<Company> companies) {
        this.companies = companies;
    }

    @Override
    public CompanyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View holderView = layoutInflater.inflate(R.layout.row_main_recyclerview, parent, false);
        return new CompanyHolder(holderView);
    }

    @Override
    public void onBindViewHolder(CompanyHolder holder, int position) {
        holder.bindView(companies.get(position));
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

}
