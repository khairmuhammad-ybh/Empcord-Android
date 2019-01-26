package com.khairmuhammad.empcord.fragments.officer.account.management;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.transactions.OfficerTransactions;
import com.khairmuhammad.transactions.models.UserModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class Creation extends Fragment implements View.OnClickListener {

    //User register information variables
    TextView register_worker_et_name, register_worker_et_email, register_worker_et_password, register_worker_et_confirm_password;
    Button register_worker_btn_register;

    public Creation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_creation, container, false);

        register_worker_et_name = rootView.findViewById(R.id.register_worker_et_name);
        register_worker_et_email = rootView.findViewById(R.id.register_worker_et_email);
        register_worker_et_password = rootView.findViewById(R.id.register_worker_et_password);
        register_worker_et_confirm_password = rootView.findViewById(R.id.register_worker_et_confirm_password);
        register_worker_btn_register = rootView.findViewById(R.id.register_worker_btn_register);

        //Listeners
        register_worker_btn_register.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.register_worker_btn_register:
                Log.d(Tags.TAG_TRANSITION, "(Creation(officer) - fragment) register button has been pressed");

                /**
                 * Call transition module to do user registration and return a user object.
                 * Once successfully authenticate, direct user back to management activity
                 */
                UserModel userModel = new UserModel();

                if(checkFields()){
                    //all fields has been filled
                    userModel.setName(register_worker_et_name.getText().toString().trim());
                    userModel.setEmail(register_worker_et_name.getText().toString().trim());
                    userModel.setPassword(register_worker_et_name.getText().toString().trim());
                    userModel.setConfirmPassword(register_worker_et_name.getText().toString().trim());

                    if(comparePassword(userModel)) {
                        if(OfficerTransactions.createWorker(userModel)){
                            clearFields();
                            Toast.makeText(getContext(), "User has successfully registered", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(), "User unable to register", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getContext(), "Password are not matched", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Please complete the registration form", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    private void clearFields() {
        register_worker_et_name.setText("");
        register_worker_et_email.setText("");
        register_worker_et_password.setText("");
        register_worker_et_confirm_password.setText("");
    }

    private boolean checkFields(){

        String[] etFields = {register_worker_et_name.getText().toString().trim(),
                            register_worker_et_email.getText().toString().trim(),
                            register_worker_et_password.getText().toString().trim(),
                            register_worker_et_confirm_password.getText().toString().trim()};

        for(String field : etFields){
            if(field.isEmpty()){
                return false;
            }
        }

        return true;
    }

    private boolean comparePassword(UserModel userModel){

        if(userModel.getPassword().equals(userModel.getConfirmPassword())){
            return true;
        }

        return false;
    }
}
