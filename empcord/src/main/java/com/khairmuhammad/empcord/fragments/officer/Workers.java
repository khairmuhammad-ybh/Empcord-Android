package com.khairmuhammad.empcord.fragments.officer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.khairmuhammad.empcord.BlankCanvas;
import com.khairmuhammad.empcord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Workers extends Fragment implements View.OnClickListener {

    //Global variables
    Button officer_btn_location, officer_btn_reminder;

    public Workers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_workers, container, false);

        officer_btn_location = rootView.findViewById(R.id.officer_btn_location);
        officer_btn_reminder = rootView.findViewById(R.id.officer_btn_reminder);

        officer_btn_location.setOnClickListener(this);
        officer_btn_reminder.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();

        bundle.putString("TYPE", "Worker");
        bundle.putString("SELECTED", ((Button)v).getText().toString());
        Intent intent = new Intent(getContext(), BlankCanvas.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
