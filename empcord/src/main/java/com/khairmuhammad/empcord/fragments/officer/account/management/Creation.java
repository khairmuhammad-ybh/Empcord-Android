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
import com.google.gson.Gson;
import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.transactions.OfficerTransactions;
import com.khairmuhammad.transactions.configuration.DummyData;
import com.khairmuhammad.transactions.models.CompanyModel;
import com.khairmuhammad.transactions.models.UserModel;
import com.khairmuhammad.transactions.security.HashingAlgorithms;

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
public class Creation extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //User register information variables
    TextView register_worker_et_name, register_worker_et_email, register_worker_et_password, register_worker_et_confirm_password;
    Button register_worker_btn_register;
    Spinner register_worker_spinner_company;

    ArrayAdapter<CompanyModel> userArray;
//    List<CompanyModel> dummyCompany = new ArrayList<>();
    CompanyModel selectedUser;

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
        register_worker_spinner_company = rootView.findViewById(R.id.register_worker_spinner_company);


        createDummyData();
//        Log.d("VolleyResp","Volley Reponse: " + storeResponse);

//        userArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dummyCompany);
//        userArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        register_worker_spinner_company.setAdapter(userArray);

        //Listeners
        register_worker_btn_register.setOnClickListener(this);

        register_worker_spinner_company.setOnItemSelectedListener(this);

        return rootView;
    }

    private void createDummyData() {
        /**
         * Get all user under this current officer from the database
         */
        StringRequest getRequest = new StringRequest(Request.Method.GET, DummyData.COMPANY_URL, new Response.Listener<String>() {
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
        });

        //Adding string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(getRequest);

    }

    private void parseJSON(String response){

        List<CompanyModel> dummyCompany = new ArrayList<>();

        String message;

        try{
            JSONObject jsonObject = new JSONObject(response);
            message = jsonObject.getString("message");
            if(message.equalsIgnoreCase("success")){
                JSONArray companies = jsonObject.getJSONArray("companies");
                int i=0;
                while (i<companies.length()) {
                    //extract data
                    JSONObject company = companies.getJSONObject(i);
                    CompanyModel companyModel = new CompanyModel();
                    companyModel.set_id(company.getString("_id"));
                    companyModel.setCompany(company.getString("company"));

                    dummyCompany.add(companyModel);

                    //increment
                    i++;
                }

                //set Adapter
                userArray = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dummyCompany);
                userArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                register_worker_spinner_company.setAdapter(userArray);
            }
        }catch (JSONException e){
            Log.d("Volley", e.getMessage());
        }

    }

    private void displayUserData(CompanyModel company){
        String _id = company.get_id();
        String companyName = company.getCompany();

//        String userData = "Name: " + name + "\nEmail: " + email + "\nZone: " + zone;
        Toast.makeText(getContext(), "_id: " + _id + "\ncompany: " + companyName, Toast.LENGTH_LONG).show();

//        remove_worker_tv_email.setText(email);
//        remove_worker_tv_zone_number.setText(zone);
//        remove_worker_tv_officer_name.setText(officerName);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        selectedUser = (CompanyModel) parent.getSelectedItem();
//        remove_worker_tv_email.setText(item);
        displayUserData(selectedUser);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                    userModel.setEmail(register_worker_et_email.getText().toString().trim());
                    userModel.setPassword(register_worker_et_password.getText().toString().trim());
                    userModel.setConfirmPassword(register_worker_et_confirm_password.getText().toString().trim());

                    if(comparePassword(userModel)) {
//                        if(OfficerTransactions.createWorker(getContext(), userModel, selectedUser.get_id())){
//                            clearFields();
//                            Toast.makeText(getContext(), "User has successfully registered", Toast.LENGTH_LONG).show();
//                        }else{
//                            Toast.makeText(getContext(), "User unable to register", Toast.LENGTH_LONG).show();
//                        }

                        //hashed password
                        String hashPassword = HashingAlgorithms.MD5Hashing(userModel.getPassword());
                        userModel.setPassword("");            //clear password field
                        userModel.setConfirmPassword("");     //clear confirm password field
                        userModel.setHashPassword(hashPassword);  //set hashed password

                        //Shared Preference
                        SharedPreferences sharedPreferences;

                        sharedPreferences = getContext().getSharedPreferences("Auth_User", Context.MODE_PRIVATE);

                        String officerId = sharedPreferences.getString("_id", "");

                        String companyId = selectedUser.get_id();
                        String name = userModel.getName();
                        String password = userModel.getHashPassword();
                        String email = userModel.getEmail();

                        createWorker(name, password, email, companyId, officerId);
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

    private void createWorker(final String name, final String hashPassword, final String email, final String companyId, final String officerId){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, OfficerTransactions.REGISTER_WORKER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String message = jsonObject.getString("message");
                    if(message.equalsIgnoreCase("success")){
                        clearFields();
                        Toast.makeText(getContext(), "User has successfully registered", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(), "User unable to register", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG_VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("name", name);
                params.put("email", email);
                params.put("password", hashPassword);
                params.put("companyId", companyId);
                params.put("officerId", officerId);

                //returning params
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


}
