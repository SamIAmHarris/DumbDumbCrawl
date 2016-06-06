package com.jackrabbitmobile.dumbdumbcrawl;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamMyxer on 6/6/16.
 */

public class MainHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    @BindView(R.id.row_main_iconImageView)
    ImageView iconImageView;
    @BindView(R.id.row_main_titleTextView)
    TextView titleTextView;

    private Company company;
    private Context context;

    public MainHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    public void bindView(Company company) {
        this.company = company;
        if(company.type == 0) {
            iconImageView.setImageDrawable(
                    ContextCompat.getDrawable(context, android.R.drawable.star_on));
        } else {
            iconImageView.setImageDrawable(
                    ContextCompat.getDrawable(context, android.R.drawable.star_off));
        }
        titleTextView.setText(company.title);
    }

    @Override
    public void onClick(View v) {

    }
}
