package com.khairmuhammad.empcord.fragments.officer.account.management;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;

/**
 * A simple {@link Fragment} subclass.
 */
public class Update extends Fragment implements View.OnClickListener {

    //User register information variables
    Spinner update_worker_spinner_name;
    EditText update_worker_et_email;
    TextView update_worker_et_zone_number, update_worker_tv_officer_name;
    Button update_worker_btn_update;

    public Update() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);

        update_worker_spinner_name = rootView.findViewById(R.id.update_worker_spinner_name);
        update_worker_et_email = rootView.findViewById(R.id.update_worker_et_email);
        update_worker_et_zone_number = rootView.findViewById(R.id.update_worker_et_zone_number);
        update_worker_tv_officer_name = rootView.findViewById(R.id.update_worker_tv_officer_name);
        update_worker_btn_update = rootView.findViewById(R.id.update_worker_btn_update);

        //Listeners
        update_worker_btn_update.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_worker_btn_update:
                Log.d(Tags.TAG_TRANSITION, "(Update(officer) - fragment) update button has been pressed");


                /**
                 * Invoke method in transition module to update user information,
                 * once done, direct user back to management activity
                 */

                break;
        }
    }
}
