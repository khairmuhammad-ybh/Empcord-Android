package com.khairmuhammad.empcord.fragments.officer.account.management;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;

/**
 * A simple {@link Fragment} subclass.
 */
public class Remove extends Fragment implements View.OnClickListener {

    //Global variables
    Spinner remove_worker_spinner_name;
    TextView remove_worker_tv_email, remove_worker_tv_zone_number, remove_worker_tv_officer_name;
    Button remove_worker_btn_remove;

    public Remove() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_remove, container, false);

        remove_worker_spinner_name = rootView.findViewById(R.id.remove_worker_spinner_name);
        remove_worker_tv_email = rootView.findViewById(R.id.remove_worker_tv_email);
        remove_worker_tv_zone_number = rootView.findViewById(R.id.remove_worker_tv_zone_number);
        remove_worker_tv_officer_name = rootView.findViewById(R.id.remove_worker_tv_officer_name);
        remove_worker_btn_remove = rootView.findViewById(R.id.remove_worker_btn_remove);

        //Listeners
        remove_worker_btn_remove.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remove_worker_btn_remove:
                Log.d(Tags.TAG_TRANSITION, "(Remove(officer) - fragment) Remove button has been pressed");

                /**
                 * Invoke method in transition module to remove user (any data associated with the account
                 * are not affected),
                 * once done, direct user back to management activity
                 */

                break;
        }
    }
}
