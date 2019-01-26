package com.khairmuhammad.dataservices;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khairmuhammad.dataservices.configurations.ConnTags;
import com.khairmuhammad.dataservices.connections.ConnectUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handling all database related operations, the center piece of component to interact
 * with remote server, being used by statistic & transitions module
 *
 * Module contributions: Muhammad Khairi & Oh Shan Ge
 */

public class DataServices {


    public static void registerUser(final Context context, final String name, final String email, final String hashPassword){

        Log.d(ConnTags.TAG_VOLLEY, "in dataserves: registerUser");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectUrl.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Data successfully returned
                        Log.d(ConnTags.TAG_VOLLEY, response);
                        parseJSONObject(context, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(ConnTags.TAG_VOLLEY, String.valueOf(error));
                //nothing
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

        //Adding string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    private static boolean parseJSONObject(Context context, String response){

        String message;

        String _id;
        String name;
        String password;
        String type;
        String email;
//        JSONArray worker;

        try{

            //Shared Preference
            SharedPreferences sharedPreferences;

            sharedPreferences = context.getSharedPreferences("Volley_SharedPref", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            JSONObject jsonObject = new JSONObject(response);
            message = jsonObject.getString("message");
            if(message.equalsIgnoreCase("success")){
//                JSONObject user = jsonObject.getJSONObject("user");
                JSONArray users = jsonObject.getJSONArray("user");
                JSONObject user = users.getJSONObject(0);
                //shared preference
                _id = user.getString("_id");
                name = user.getString("name");
                password = user.getString("password");
                type = user.getString("type");
                email = user.getString("email");
//                worker = user.getJSONArray("worker");


                editor.putString("_id", _id);
                editor.putString("name", name);
                editor.putString("password", password);
                editor.putString("type", type);
                editor.putString("email", email);
                editor.putString("message", message);
                editor.apply();

                return true;
            }else{
                editor.putString("message", message);
                editor.apply();
            }

        }catch (JSONException e){
            Log.d(ConnTags.TAG_VOLLEY, e.getMessage());
        }

        return false;
    }

    public static void loggingUser(final Context context, final String email, final String hashPassword){

        Log.d(ConnTags.TAG_VOLLEY, "in dataserves: loggingUser");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectUrl.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Data successfully returned
                        Log.d(ConnTags.TAG_VOLLEY, response);
                        parseJSONObject(context, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(ConnTags.TAG_VOLLEY, String.valueOf(error));
                //nothing
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

        //Adding string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

}
