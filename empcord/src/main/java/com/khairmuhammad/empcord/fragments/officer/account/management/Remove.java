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
import com.khairmuhammad.transactions.OfficerTransactions;
import com.khairmuhammad.transactions.configuration.DummyData;
import com.khairmuhammad.transactions.models.UserModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Remove extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //Global variables
    Spinner remove_worker_spinner_name;
    TextView remove_worker_tv_email, remove_worker_tv_zone_number, remove_worker_tv_officer_name;
    Button remove_worker_btn_remove;

    ArrayAdapter<UserModel> userArray;
    List<UserModel> dummyName;
    UserModel selectedUser;

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

        dummyName = createDummyData();
        userArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dummyName);
        userArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        remove_worker_spinner_name.setAdapter(userArray);

        //Listeners
        remove_worker_btn_remove.setOnClickListener(this);

        remove_worker_spinner_name.setOnItemSelectedListener(this);

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

        remove_worker_tv_email.setText(email);
        remove_worker_tv_zone_number.setText(zone);
        remove_worker_tv_officer_name.setText(officerName);
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
            case R.id.remove_worker_btn_remove:
                Log.d(Tags.TAG_TRANSITION, "(Remove(officer) - fragment) Remove button has been pressed");

                /**
                 * Invoke method in transition module to remove user (any data associated with the account
                 * are not affected),
                 * once done, direct user back to management activity.
                 *
                 * pass selectedUser to Transition module to remove user from database
                 */

                String name = selectedUser.getName();
                String email = selectedUser.getEmail();
                String zone = String.valueOf(selectedUser.getZone());
                String officerName = selectedUser.getOfficerName();

//                String userData = "Name: " + name + "\nEmail: " + email + "\nZone: " + zone;
//                Toast.makeText(getContext(), userData, Toast.LENGTH_LONG).show();

                if(OfficerTransactions.removeUser(selectedUser)){
                    Toast.makeText(getContext(), "User successfully removed", Toast.LENGTH_LONG).show();
                    userArray.remove(selectedUser);
                    userArray.notifyDataSetChanged();
                }

                break;
        }
    }


}
