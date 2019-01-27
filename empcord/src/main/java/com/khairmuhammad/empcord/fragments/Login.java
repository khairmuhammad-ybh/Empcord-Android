package com.khairmuhammad.empcord.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khairmuhammad.empcord.OfficerTabbedActivity;
import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.WorkerTabbedActivity;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.transactions.AuthTransactions;
import com.khairmuhammad.transactions.OfficerTransactions;
import com.khairmuhammad.transactions.models.UserModel;
import com.khairmuhammad.transactions.security.HashingAlgorithms;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements View.OnClickListener {

    //Global variables
    TextView link_register;

    //User login credential variables
    EditText login_et_email, login_et_password;
    Button login_btn_login;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        //Shared Preference
        SharedPreferences sharedPreferences;

        sharedPreferences = getContext().getSharedPreferences("Auth_User", Context.MODE_PRIVATE);

        if(sharedPreferences.contains("_id")){
            String type = sharedPreferences.getString("type", "");
            if(type.equals("Officer")){
                startActivity(new Intent(getContext(), OfficerTabbedActivity.class));
            }else{
                startActivity(new Intent(getContext(), WorkerTabbedActivity.class));
            }
            getActivity().finish();
        }

        //Assignments
        link_register = rootView.findViewById(R.id.link_register);
        login_et_email = rootView.findViewById(R.id.login_et_email);
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
                String email = login_et_email.getText().toString().trim();
                String password = login_et_password.getText().toString().trim();

                UserModel loginUser = new UserModel();
                loginUser.setEmail(email);
                loginUser.setPassword(password);

//                UserModel authUser = AuthTransactions.authenticateUser(getContext(), loginUser);
//                Log.d(Tags.TAG_TRANSITION, "(Login - fragment) " + authUser);
//                if(authUser != null){
//                    if(authUser.getType().equals("Officer")){
//                        startActivity(new Intent(getContext(), OfficerTabbedActivity.class));
//                    }else if(authUser.getType().equals("Worker")){
//                        startActivity(new Intent(getContext(), WorkerTabbedActivity.class));
//                    }
//                }else{
//                    Log.d(Tags.TAG_TRANSITION, "(Login - fragment) No user returned");
//                    Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();
//                }

                //hashed password
                String hashPassword = HashingAlgorithms.MD5Hashing(loginUser.getPassword());
                loginUser.setPassword("");            //clear password field
                loginUser.setConfirmPassword("");     //clear confirm password field
                loginUser.setHashPassword(hashPassword);  //set hashed password

                loggingUser(loginUser.getEmail(), loginUser.getHashPassword());


//                startActivity(new Intent(getContext(), WorkerTabbedActivity.class));
//                startActivity(new Intent(getContext(), OfficerTabbedActivity.class));
//                getActivity().finish();
                break;
        }
    }

    private void loggingUser(final String email, final String hashPassword){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, OfficerTransactions.LOGIN_USER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String message = jsonObject.getString("message");
                    if(message.equalsIgnoreCase("success")){
                        JSONObject user = jsonObject.getJSONObject("user");
                        String accType = user.getString("type");

                        //store data into sharedPref
                        //Shared Preference
                        SharedPreferences sharedPref;

                        sharedPref = getContext().getSharedPreferences("Auth_User", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        if(accType.equals("Officer")){
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

                            startActivity(new Intent(getContext(), OfficerTabbedActivity.class));
                            getActivity().finish();
                        }else{
                            String _id = user.getString("_id");
                            String name = user.getString("name");
                            String password = user.getString("password");
                            String type = user.getString("type");
                            String email = user.getString("email");
                            String companyId = user.getString("companyId");
                            String officerId = user.getString("officerId");

                            editor.putString("_id", _id);
                            editor.putString("name", name);
                            editor.putString("password", password);
                            editor.putString("type", type);
                            editor.putString("email", email);
                            editor.putString("companyId", companyId);
                            editor.putString("officerId", officerId);

                            editor.apply();

                            startActivity(new Intent(getContext(), WorkerTabbedActivity.class));
                            getActivity().finish();
                        }
                    }else{
                        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
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
