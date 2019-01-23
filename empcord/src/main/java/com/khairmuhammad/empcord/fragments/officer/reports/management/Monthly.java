package com.khairmuhammad.empcord.fragments.officer.reports.management;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;

/**
 * A simple {@link Fragment} subclass.
 */
public class Monthly extends Fragment implements View.OnClickListener {

    //Global variables
    Spinner report_monthly_spinner_name;
    Button report_monthly_btn_report;

    public Monthly() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_monthly, container, false);

        report_monthly_spinner_name = rootView.findViewById(R.id.report_monthly_spinner_name);
        report_monthly_btn_report = rootView.findViewById(R.id.report_monthly_btn_report);

        //Listeners
        report_monthly_btn_report.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.report_monthly_btn_report:
                Log.d(Tags.TAG_TRANSITION, "(Monthly(officer) - fragment) Generate Report button has been pressed");

                /**
                 * Invoke method in transition module to generate monthly report and display
                 * in table-format
                 */

                break;
        }
    }
}
