package com.khairmuhammad.empcord.fragments;


import android.content.Context;
import android.content.SharedPreferences;
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
import com.khairmuhammad.transactions.AuthTransactions;
import com.khairmuhammad.transactions.OfficerTransactions;
import com.khairmuhammad.transactions.models.UserModel;
import com.khairmuhammad.transactions.security.HashingAlgorithms;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
//                    if(AuthTransactions.createUser(getContext(), newUser)){
//                        Toast.makeText(getContext(), "User registered", Toast.LENGTH_LONG).show();
//                        manager.popBackStack();
//                    }else{
//                        Toast.makeText(getContext(), "User already registered", Toast.LENGTH_LONG).show();
//                    }

                    //hashed password
                    String hashPassword = HashingAlgorithms.MD5Hashing(newUser.getPassword());
                    newUser.setPassword("");            //clear password field
                    newUser.setConfirmPassword("");     //clear confirm password field
                    newUser.setHashPassword(hashPassword);  //set hashed password

                    createUser(newUser.getName(), newUser.getEmail(), newUser.getHashPassword());


                }else {
                    Toast.makeText(getContext(), "Password are not matched", Toast.LENGTH_LONG).show();
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

    private void createUser(final String name, final String email, final String hashPassword){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, OfficerTransactions.REGISTER_USER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String message = jsonObject.getString("message");
                    if(message.equalsIgnoreCase("success")){
                        JSONObject user = jsonObject.getJSONObject("user");
//                        JSONObject user = users.getJSONObject(0);

                        //store data into sharedPref
                        //Shared Preference
                        SharedPreferences sharedPref;

                        sharedPref = getContext().getSharedPreferences("Auth_User", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        String _id = user.getString("_id");
                        String name = user.getString("name");
                        String password = user.getString("password");
                        String type = user.getString("type");
                        String email = user.getString("email");

                        editor.putString("_id", _id);
                        editor.putString("name", name);
                        editor.putString("password", password);
                        editor.putString("type", type);
                        editor.putString("email", email);

                        editor.apply();

                        //close fragment
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        manager.popBackStack();
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

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("name", name);
                params.put("email", email);
                params.put("password", hashPassword);

                //returning params
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}
