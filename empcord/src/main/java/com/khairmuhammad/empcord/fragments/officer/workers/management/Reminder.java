package com.khairmuhammad.empcord.fragments.officer.workers.management;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;

/**
 * A simple {@link Fragment} subclass.
 */
public class Reminder extends Fragment implements View.OnClickListener {

    //Global variables
    Spinner reminder_worker_spinner_name, reminder_worker_spinner_urgent;
    EditText reminder_worker_et_reminder;
    Button reminder_worker_btn_reminder;

    public Reminder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reminder, container, false);

        reminder_worker_spinner_name = rootView.findViewById(R.id.reminder_worker_spinner_name);
        reminder_worker_spinner_urgent = rootView.findViewById(R.id.reminder_worker_spinner_urgent);
        reminder_worker_et_reminder = rootView.findViewById(R.id.reminder_worker_et_reminder);
        reminder_worker_btn_reminder = rootView.findViewById(R.id.reminder_worker_btn_reminder);

        //Listeners
        reminder_worker_btn_reminder.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reminder_worker_btn_reminder:
                Log.d(Tags.TAG_TRANSITION, "(Reminder(officer) - fragment) Reminder button has been pressed");

                /**
                 * Invoke method in transition module to send reminder to user
                 * once done, direct user back to management activity
                 */

                break;
        }
    }
}
