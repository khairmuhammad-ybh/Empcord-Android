package com.khairmuhammad.empcord.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.khairmuhammad.empcord.OfficerTabbedActivity;
import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.WorkerTabbedActivity;
import com.khairmuhammad.empcord.configurations.Tags;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements View.OnClickListener {

    //Global variables
    TextView link_register;

    //User login credential variables
    EditText login_et_username, login_et_password;
    Button login_btn_login;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        //Assignments
        link_register = rootView.findViewById(R.id.link_register);
        login_et_username = rootView.findViewById(R.id.login_et_username);
        login_et_password = rootView.findViewById(R.id.login_et_password);
        login_btn_login = rootView.findViewById(R.id.login_btn_login);

        //Listeners
        link_register.setOnClickListener(this);
        login_btn_login.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        switch (v.getId()){
            case R.id.link_register:
                Log.d(Tags.TAG_TRANSITION, "(Login - fragment) register link has been pressed");

                //transact to register fragment
                Register registerFragment = new Register();

                manager.beginTransaction()
                        .replace(R.id.fragment_container, registerFragment)
                        .addToBackStack("tag")
                        .commit();

                break;
            case R.id.login_btn_login:
                Log.d(Tags.TAG_TRANSITION, "(Login - fragment) login button has been pressed");

                /**
                 * Call transition module to do user authentication and return an use object.
                 * Once successfully authenticate, direct user to respective user dashboard
                 * (officer / worker)
                 */

                startActivity(new Intent(getContext(), WorkerTabbedActivity.class));
//                startActivity(new Intent(getContext(), OfficerTabbedActivity.class));
                getActivity().finish();
                break;
        }
    }
}
