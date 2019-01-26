package com.khairmuhammad.empcord.fragments.officer.workers.management;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import com.khairmuhammad.empcord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Location extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{

    //Global variables
    Spinner location_worker_spinner_name;
    TextView location_worker_tv_address;
    MapView google_map_view;


    public Location() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        location_worker_spinner_name = rootView.findViewById(R.id.location_worker_spinner_name);
        location_worker_tv_address = rootView.findViewById(R.id.location_worker_tv_address);
        google_map_view = rootView.findViewById(R.id.google_map_view);


        //Listeners
        location_worker_spinner_name.setOnItemSelectedListener(this);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        String item = parent.getItemAtPosition(pos).toString();

        /**
         * Invoke method in transition to get location on selected user.
         * Once done, display location on MapView
         */
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
