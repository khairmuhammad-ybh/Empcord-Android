package com.khairmuhammad.empcord.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.transactions.AuthTransactions;
import com.khairmuhammad.transactions.models.UserModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment implements View.OnClickListener {

    //Global variables
    TextView link_login;

    //User register information variables
    TextView register_et_name, register_et_email, register_et_password, register_et_confirm_password;
    Button register_btn_register;


    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        //Assignments
        link_login = rootView.findViewById(R.id.link_login);
        register_et_name = rootView.findViewById(R.id.register_et_name);
        register_et_email = rootView.findViewById(R.id.register_et_email);
        register_et_password = rootView.findViewById(R.id.register_et_password);
        register_et_confirm_password = rootView.findViewById(R.id.register_et_confirm_password);
        register_btn_register = rootView.findViewById(R.id.register_btn_register);

        //Listeners
        link_login.setOnClickListener(this);
        register_btn_register.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        switch (v.getId()){
            case R.id.link_login:
                Log.d(Tags.TAG_TRANSITION, "(Register - fragment) login link has been pressed");

                manager.popBackStack();

                break;
            case R.id.register_btn_register:
                Log.d(Tags.TAG_TRANSITION, "(Register - fragment) register button has been pressed");

                /**
                 * Call transition module to do user registration and return a user object.
                 * Once successfully authenticate, direct user to respective user dashboard
                 * (officer / worker)
                 */
                String name = register_et_name.getText().toString().trim();
                String email = register_et_email.getText().toString().trim();
                String password = register_et_password.getText().toString().trim();
                String cPassword = register_et_confirm_password.getText().toString().trim();

                UserModel newUser = new UserModel();
                newUser.setName(name);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setConfirmPassword(cPassword);

                if(comparePassword(newUser)){
                    if(AuthTransactions.createUser(getContext(), newUser)){
                        Toast.makeText(getContext(), "User registered", Toast.LENGTH_LONG).show();
                        manager.popBackStack();
                    }else{
                        Toast.makeText(getContext(), "User already registered", Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
    }

    private boolean comparePassword(UserModel newUser){

        if(newUser.getPassword().equals(newUser.getConfirmPassword())){
            return true;
        }

        return false;
    }
}
