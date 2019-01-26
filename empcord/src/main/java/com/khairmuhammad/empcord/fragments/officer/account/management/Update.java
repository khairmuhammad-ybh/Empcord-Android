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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.transactions.OfficerTransactions;
import com.khairmuhammad.transactions.configuration.DummyData;
import com.khairmuhammad.transactions.models.UserModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Update extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //User register information variables
    Spinner update_worker_spinner_name;
    EditText update_worker_et_email;
    TextView update_worker_et_zone_number, update_worker_tv_officer_name;
    Button update_worker_btn_update;

    ArrayAdapter<UserModel> userArray;
    List<UserModel> dummyName;
    UserModel selectedUser;

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

        dummyName = createDummyData();
        userArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dummyName);
        userArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        update_worker_spinner_name.setAdapter(userArray);

        //Listeners
        update_worker_btn_update.setOnClickListener(this);

        update_worker_spinner_name.setOnItemSelectedListener(this);

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

        update_worker_et_email.setText("");
        update_worker_et_email.setHint(email);
        update_worker_et_zone_number.setText(zone);
        update_worker_tv_officer_name.setText(officerName);
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
            case R.id.update_worker_btn_update:
                Log.d(Tags.TAG_TRANSITION, "(Update(officer) - fragment) update button has been pressed");


                /**
                 * Invoke method in transition module to update user information,
                 * once done, direct user back to management activity
                 */
                String newEmail = update_worker_et_email.getText().toString().trim();
                if(!newEmail.isEmpty()){
                    if(OfficerTransactions.updateWorkerEmail(selectedUser, newEmail)){
                        Toast.makeText(getContext(), "User successfully updated", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(), "User unable to update", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Field is empty, user won't get updated", Toast.LENGTH_LONG).show();
                }


                break;
        }
    }

}
