package com.khairmuhammad.empcord.fragments.officer.account.management;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.empcord.fragments.dialogs.PasswordResetDialog;
import com.khairmuhammad.transactions.OfficerTransactions;
import com.khairmuhammad.transactions.configuration.DummyData;
import com.khairmuhammad.transactions.models.UserModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Reset extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, PasswordResetDialog.OnInputSelected {

    //User register information variables
    Spinner reset_worker_spinner_name;
    TextView reset_worker_tv_email, reset_worker_et_zone_number, reset_worker_et_officer_name;
    Button reset_worker_btn_unbind, reset_worker_btn_password_reset;

    ArrayAdapter<UserModel> userArray;
    List<UserModel> dummyName;
    UserModel selectedUser;

    //Dialog
    public String newPassword;

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

        dummyName = createDummyData();
        userArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dummyName);
        userArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reset_worker_spinner_name.setAdapter(userArray);

        //Listeners
        reset_worker_btn_unbind.setOnClickListener(this);
        reset_worker_btn_password_reset.setOnClickListener(this);

        reset_worker_spinner_name.setOnItemSelectedListener(this);

        return rootView;
    }

    private List<UserModel> createDummyData() {
        /**
         * Get all user under this current officer from the database
         */
        return DummyData.dummyUserList();
    }

    private void displayUserData(UserModel user){
        String name = user.getName();
        String email = user.getEmail();
        String zone = String.valueOf(user.getZone());
        String officerName = user.getOfficerName();

//        String userData = "Name: " + name + "\nEmail: " + email + "\nZone: " + zone;
//        Toast.makeText(getContext(), userData, Toast.LENGTH_LONG).show();

        reset_worker_tv_email.setText(email);
        reset_worker_et_zone_number.setText(zone);
        reset_worker_et_officer_name.setText(officerName);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        selectedUser = (UserModel) parent.getSelectedItem();
//        remove_worker_tv_email.setText(item);
        displayUserData(selectedUser);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                if(OfficerTransactions.unbindUser(selectedUser)){
                    Toast.makeText(getContext(), "User successfully unbind", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.reset_worker_btn_password_reset:
                Log.d(Tags.TAG_TRANSITION, "(Reset(officer) - fragment) password-reset button has been pressed");

                /**
                 * Open PasswordReset dialog to ask user for new password to be updated
                 */

                PasswordResetDialog passwordResetDialog = new PasswordResetDialog();
                passwordResetDialog.setTargetFragment(Reset.this, 1);
                passwordResetDialog.show(getFragmentManager(), "password reset dialog");


                break;
        }
    }


    @Override
    public void sendInput(String input) {

        /**
         * Invoke method in transition module to reset password(update user new password),
         * once done, direct user back to management activity
         */

        Log.d("Dialog", "Found incoming input: " + input);
        newPassword = input;

        if(!newPassword.isEmpty()){
            if(OfficerTransactions.passwordReset(selectedUser, newPassword)){
                Toast.makeText(getContext(), "User successfully password reset", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getContext(), "User not being reset", Toast.LENGTH_LONG).show();
        }
    }
}
