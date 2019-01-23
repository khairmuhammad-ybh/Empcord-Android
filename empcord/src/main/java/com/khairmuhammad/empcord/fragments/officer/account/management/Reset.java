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
public class Reset extends Fragment implements View.OnClickListener {

    //User register information variables
    Spinner reset_worker_spinner_name;
    TextView reset_worker_tv_email, reset_worker_et_zone_number, reset_worker_et_officer_name;
    Button reset_worker_btn_unbind, reset_worker_btn_password_reset;

    public Reset() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reset, container, false);

        reset_worker_spinner_name = rootView.findViewById(R.id.reset_worker_spinner_name);
        reset_worker_tv_email = rootView.findViewById(R.id.reset_worker_tv_email);
        reset_worker_et_zone_number = rootView.findViewById(R.id.reset_worker_et_zone_number);
        reset_worker_et_officer_name = rootView.findViewById(R.id.reset_worker_et_officer_name);
        reset_worker_btn_unbind = rootView.findViewById(R.id.reset_worker_btn_unbind);
        reset_worker_btn_password_reset = rootView.findViewById(R.id.reset_worker_btn_password_reset);

        //Listeners
        reset_worker_btn_unbind.setOnClickListener(this);
        reset_worker_btn_password_reset.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reset_worker_btn_unbind:
                Log.d(Tags.TAG_TRANSITION, "(Reset(officer) - fragment) unbind button has been pressed");

                /**
                 * Invoke method in transition module to unbind user (remove mac address from user account),
                 * once done, direct user back to management activity
                 */

                break;
            case R.id.reset_worker_btn_password_reset:
                Log.d(Tags.TAG_TRANSITION, "(Reset(officer) - fragment) password-reset button has been pressed");

                /**
                 * Invoke method in transition module to reset password(update user new password),
                 * once done, direct user back to management activity
                 */

                break;
        }
    }
}
