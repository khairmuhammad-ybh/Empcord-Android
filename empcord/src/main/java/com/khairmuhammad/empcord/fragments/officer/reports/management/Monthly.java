package com.khairmuhammad.empcord.fragments.officer.reports.management;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class Monthly extends Fragment implements View.OnClickListener {

    //Global variables
    Spinner report_monthly_spinner_name;
    Button report_monthly_btn_report;
    TableLayout report_monthly_table;
    public Monthly() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] nameArray = {"Hamster","Wee","Snake"};
        String[] completionArray = {"Completed","Completed","Completed"};
        String[] dateArray = {"1/4/2019","2/4/2019","3/4/2019"};
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_monthly, container, false);

        report_monthly_spinner_name = rootView.findViewById(R.id.report_monthly_spinner_name);
        report_monthly_btn_report = rootView.findViewById(R.id.report_monthly_btn_report);
        report_monthly_table = rootView.findViewById(R.id.report_monthly_tableLayout);

        if(nameArray.length > 0){
        for(int i = 0; i < nameArray.length;i++){
            TableRow row = new TableRow(rootView.getContext());

            TextView nameTextView = new TextView(rootView.getContext());
            TextView completionTextView = new TextView(rootView.getContext());
            TextView dateTextView = new TextView(rootView.getContext());

            nameTextView.setText(nameArray[i]);
            nameTextView.setGravity(Gravity.CENTER);
            nameTextView.setBackgroundColor(Color.parseColor("#ffffff"));

            completionTextView.setText(completionArray[i]);
            completionTextView.setGravity(Gravity.CENTER);
            completionTextView.setBackgroundColor(Color.parseColor("#ffffff"));

            dateTextView.setText(dateArray[i]);
            dateTextView.setGravity(Gravity.CENTER);
            dateTextView.setBackgroundColor(Color.parseColor("#ffffff"));

            row.setBackgroundColor(Color.parseColor("#000000"));

            row.addView(nameTextView);
            row.addView(completionTextView);
            row.addView(dateTextView);
            report_monthly_table.addView(row);
        }
        }else{
            TableRow row = new TableRow(rootView.getContext());
            TextView noRecordText = new TextView(rootView.getContext());
            noRecordText.setText("No Record Found");
            noRecordText.setGravity(Gravity.CENTER);
            noRecordText.setBackgroundColor(Color.parseColor("#ffffff"));
            row.setBackgroundColor(Color.parseColor("#000000"));
            row.addView(noRecordText);
            report_monthly_table.addView(row);
        }
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
