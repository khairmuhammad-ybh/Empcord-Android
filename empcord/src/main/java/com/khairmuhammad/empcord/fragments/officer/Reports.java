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
public class Reports extends Fragment implements View.OnClickListener {

    //Global variables
    Button officer_btn_worker, officer_btn_monthly;

    public Reports() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reports, container, false);

        officer_btn_worker = rootView.findViewById(R.id.officer_btn_worker);
        officer_btn_monthly = rootView.findViewById(R.id.officer_btn_monthly);

        officer_btn_worker.setOnClickListener(this);
        officer_btn_monthly.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();

        bundle.putString("TYPE", "Report");
        bundle.putString("SELECTED", ((Button)v).getText().toString());
        Intent intent = new Intent(getContext(), BlankCanvas.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
