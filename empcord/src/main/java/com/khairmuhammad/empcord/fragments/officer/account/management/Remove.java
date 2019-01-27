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
import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.transactions.OfficerTransactions;
import com.khairmuhammad.transactions.configuration.DummyData;
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
public class Remove extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //Global variables
    Spinner remove_worker_spinner_name;
    TextView remove_worker_tv_email, remove_worker_tv_zone_number, remove_worker_tv_officer_name;
    Button remove_worker_btn_remove;

    ArrayAdapter<UserModel> userArray;
//    List<UserModel> dummyName;
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

        createDummyData();
//        userArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dummyName);
//        userArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        remove_worker_spinner_name.setAdapter(userArray);

        //Listeners
        remove_worker_btn_remove.setOnClickListener(this);

        remove_worker_spinner_name.setOnItemSelectedListener(this);

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
                remove_worker_spinner_name.setAdapter(userArray);
            }
        }catch (JSONException e){
            Log.d("JSONException", e.getMessage());
        }

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

//                if(OfficerTransactions.removeUser(selectedUser)){
////                    Toast.makeText(getContext(), "User successfully removed", Toast.LENGTH_LONG).show();
////                    userArray.remove(selectedUser);
////                    userArray.notifyDataSetChanged();
////                }

                String workerID = selectedUser.get_id();

                removeWorker(workerID);

                break;
        }
    }

    private void removeWorker(final String workerId){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DummyData.URL_REMOVE_FOREMAN, new Response.Listener<String>() {
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
                remove_worker_spinner_name.setAdapter(userArray);
            }
        }catch (JSONException e){
            Log.d("JSONException", e.getMessage());
        }

    }


}
