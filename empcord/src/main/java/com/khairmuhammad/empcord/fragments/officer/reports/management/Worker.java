package com.khairmuhammad.empcord.fragments.officer.reports.management;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.statistics.Statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Worker extends Fragment implements View.OnClickListener {

    //Global variables
    Spinner report_worker_spinner_name;
    Button report_worker_btn_report;
    TableLayout report_worker_tableLayout;
    public Worker() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] nameArray = {"Hamster","Wee","Snake"};
        String[] completionArray = {"Completed","Completed","Completed"};
        String[] dateArray = {"1/4/2019","2/4/2019","3/4/2019"};

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_worker, container, false);

        report_worker_spinner_name = rootView.findViewById(R.id.report_worker_spinner_name);
        report_worker_btn_report = rootView.findViewById(R.id.report_worker_btn_report);
        report_worker_tableLayout = rootView.findViewById(R.id.report_worker_tableLayout);
        //---------------------fill spinner-------------------------------------------
        List<String> nameList = new ArrayList<String>();
        nameList.addAll(Arrays.asList(nameArray));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_spinner_item,nameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        report_worker_spinner_name.setAdapter(dataAdapter);
        //---------------------fill table---------------------------------------------
        if(completionArray.length > 0){
            for(int i = 0; i < completionArray.length;i++){
                TableRow row = new TableRow(rootView.getContext());

                TextView completionTextView = new TextView(rootView.getContext());
                TextView dateTextView = new TextView(rootView.getContext());


                completionTextView.setText(completionArray[i]);
                completionTextView.setGravity(Gravity.CENTER);
                completionTextView.setBackgroundColor(Color.parseColor("#ffffff"));

                dateTextView.setText(dateArray[i]);
                dateTextView.setGravity(Gravity.CENTER);
                dateTextView.setBackgroundColor(Color.parseColor("#ffffff"));

                row.setBackgroundColor(Color.parseColor("#000000"));

                row.addView(completionTextView);
                row.addView(dateTextView);
                report_worker_tableLayout.addView(row);
            }
        }else{
            TableRow row = new TableRow(rootView.getContext());
            TextView noRecordText = new TextView(rootView.getContext());
            noRecordText.setText("No Record Found");
            noRecordText.setGravity(Gravity.CENTER);
            noRecordText.setBackgroundColor(Color.parseColor("#ffffff"));
            row.setBackgroundColor(Color.parseColor("#000000"));
            row.addView(noRecordText);
            report_worker_tableLayout.addView(row);
        }
        //Listeners
        report_worker_btn_report.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.report_worker_btn_report:
                Log.d(Tags.TAG_TRANSITION, "(Worker(officer) - fragment) Generate Report button has been pressed");
                Statistics.generateExcel();
                /**
                 * Invoke method in transition module to generate worker's report and display
                 * in table-format
                 */

                break;
        }
    }
}
