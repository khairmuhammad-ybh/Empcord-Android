package com.khairmuhammad.empcord.fragments.worker;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.khairmuhammad.empcord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Agenda extends Fragment {
    TableLayout worker_agenda_table;

    public Agenda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] blkArray = {"180","33","72"};
        String[] streetArray = {"Ang Mo Kio Ave 8","Sengkang West Ave","Woodlands Ave 3"};
        String[] completionArray = {"Incomplete","Incomplete","Incomplete"};
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_agenda, container, false);

        /**
         * Display current user's today's schedule (agenda) in a table format
         */
        worker_agenda_table = rootView.findViewById(R.id.worker_agenda_tableLayout);
        if(blkArray.length > 0){
            for(int i = 0; i < blkArray.length;i++){
                TableRow row = new TableRow(rootView.getContext());

                TextView blkTextView = new TextView(rootView.getContext());
                TextView streetTextView = new TextView(rootView.getContext());
                TextView completionTextView = new TextView(rootView.getContext());

                blkTextView.setText(blkArray[i]);
                blkTextView.setGravity(Gravity.CENTER);
                blkTextView.setBackgroundColor(Color.parseColor("#ffffff"));

                streetTextView.setText(streetArray[i]);
                streetTextView.setGravity(Gravity.CENTER);
                streetTextView.setBackgroundColor(Color.parseColor("#ffffff"));

                completionTextView.setText(completionArray[i]);
                completionTextView.setGravity(Gravity.CENTER);
                completionTextView.setBackgroundColor(Color.parseColor("#ffffff"));

                row.setBackgroundColor(Color.parseColor("#000000"));

                row.addView(blkTextView);
                row.addView(streetTextView);
                row.addView(completionTextView);
                worker_agenda_table.addView(row);
            }
        }else{
            TableRow row = new TableRow(rootView.getContext());
            TextView noRecordText = new TextView(rootView.getContext());
            noRecordText.setText("No Record Found");
            noRecordText.setGravity(Gravity.CENTER);
            noRecordText.setBackgroundColor(Color.parseColor("#ffffff"));
            row.setBackgroundColor(Color.parseColor("#000000"));
            row.addView(noRecordText);
            worker_agenda_table.addView(row);
        }

        return rootView;
    }

}
