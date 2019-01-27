package com.khairmuhammad.empcord.fragments.officer.account.management;


import android.content.Context;
import android.content.SharedPreferences;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.transactions.OfficerTransactions;
import com.khairmuhammad.transactions.configuration.DummyData;
import com.khairmuhammad.transactions.models.CompanyModel;
import com.khairmuhammad.transactions.models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//    List<UserModel> dummyName;
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

        createDummyData();
//        userArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dummyName);
//        userArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        update_worker_spinner_name.setAdapter(userArray);

        //Listeners
        update_worker_btn_update.setOnClickListener(this);

        update_worker_spinner_name.setOnItemSelectedListener(this);

        return rootView;
    }

    private void createDummyData() {
        /**
         * Get all user under this current officer from the database
         */
//        return DummyData.dummyUserList();

        //Shared Preference
        SharedPreferences sharedPreferences;

        sharedPreferences = getContext().getSharedPreferences("Auth_User", Context.MODE_PRIVATE);

        final String officerId = sharedPreferences.getString("_id", "");

        StringRequest getRequest = new StringRequest(Request.Method.POST, DummyData.FOREMAN_WORKER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ERROR EN LA RESPUESTA", "From createDummy data: " +response);
                parseJSON(response);

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR EN LA RESPUESTA", error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("id", officerId);

                //returning params
                return params;
            }
        };

        //Adding string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(getRequest);
    }

    private void parseJSON(String response){

        List<UserModel> dummyName = new ArrayList<>();

        String message;

        try{
            JSONObject jsonObject = new JSONObject(response);
            message = jsonObject.getString("message");
            if(message.equalsIgnoreCase("success")){
                JSONArray users = jsonObject.getJSONArray("users");
                int i=0;
                while (i<users.length()) {
                    //extract data
                    JSONObject user = users.getJSONObject(i);

                    UserModel userModel = new UserModel();
                    userModel.set_id(user.getString("_id"));
                    userModel.setName(user.getString("name"));
                    userModel.setPassword(user.getString("password"));
                    userModel.setType(user.getString("type"));
                    userModel.setEmail(user.getString("email"));

                    //company
                    JSONObject company = user.getJSONObject("companyId");
                    userModel.setCompanyId(company.getString("_id"));
                    userModel.setCompanyName(company.getString("company"));

                    //officer
                    JSONObject officer = user.getJSONObject("officerId");
                    userModel.setOfficerId(officer.getString("_id"));
                    userModel.setOfficerName(officer.getString("name"));

                    dummyName.add(userModel);

                    //increment
                    i++;
                }

                //set Adapter
                userArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dummyName);
                userArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                update_worker_spinner_name.setAdapter(userArray);
            }
        }catch (JSONException e){
            Log.d("JSONException", e.getMessage());
        }

    }

    private void displayUserData(UserModel user){
        String email = user.getEmail();
//        String zone = String.valueOf(user.getZone());
        String officerName = user.getOfficerName();

//        String userData = "Name: " + name + "\nEmail: " + email + "\nZone: " + zone;
//        Toast.makeText(getContext(), userData, Toast.LENGTH_LONG).show();

        update_worker_et_email.setText("");
        update_worker_et_email.setHint(email);
//        update_worker_et_zone_number.setText(zone);
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
//                    if(OfficerTransactions.updateWorkerEmail(selectedUser, newEmail)){
//                        Toast.makeText(getContext(), "User successfully updated", Toast.LENGTH_LONG).show();
//                    }else{
//                        Toast.makeText(getContext(), "User unable to update", Toast.LENGTH_LONG).show();
//                    }

                    String workerID = selectedUser.get_id();

                    updateWorkerEmail(workerID, newEmail);
                }else{
                    Toast.makeText(getContext(), "Field is empty, user won't get updated", Toast.LENGTH_LONG).show();
                }


                break;
        }
    }

    private void updateWorkerEmail(final String workerId, final String email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DummyData.URL_UPDATE_FOREMAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ERROR EN LA RESPUESTA", "From createDummy data: " +response);
                parseUpdateJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR EN LA RESPUESTA", error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("id", workerId);
                params.put("email", email);

                //returning params
                return params;
            }
        };

        //Adding string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    private void parseUpdateJSON(String response){

        List<UserModel> dummyName = new ArrayList<>();

        String message;

        try{
            JSONObject jsonObject = new JSONObject(response);
            message = jsonObject.getString("message");
            if(message.equalsIgnoreCase("success")){
//                JSONArray users = jsonObject.getJSONArray("users");
                JSONObject user = jsonObject.getJSONObject("users");
//                int i=0;
//                while (i<users.length()) {
                    //extract data
//                    JSONObject user = users.getJSONObject(i);

                    UserModel userModel = new UserModel();
                    userModel.set_id(user.getString("_id"));
                    userModel.setName(user.getString("name"));
                    userModel.setPassword(user.getString("password"));
                    userModel.setType(user.getString("type"));
                    userModel.setEmail(user.getString("email"));

                    //company
                    JSONObject company = user.getJSONObject("companyId");
                    userModel.setCompanyId(company.getString("_id"));
                    userModel.setCompanyName(company.getString("company"));

                    //officer
                    JSONObject officer = user.getJSONObject("officerId");
                    userModel.setOfficerId(officer.getString("_id"));
                    userModel.setOfficerName(officer.getString("name"));

                    dummyName.add(userModel);

                    //increment
//                    i++;
//                }

                //set Adapter
                userArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dummyName);
                userArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                update_worker_spinner_name.setAdapter(userArray);
            }
        }catch (JSONException e){
            Log.d("JSONException", e.getMessage());
        }

    }

}
