package com.khairmuhammad.transactions;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.khairmuhammad.dataservices.DataServices;
import com.khairmuhammad.transactions.configuration.AuthTags;
import com.khairmuhammad.transactions.configuration.Config;
import com.khairmuhammad.transactions.models.UserModel;
import com.khairmuhammad.transactions.security.HashingAlgorithms;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AuthTransactions {

    public static boolean createUser(Context context, UserModel newUser){

        //hashed password
        String hashPassword = HashingAlgorithms.MD5Hashing(newUser.getPassword());
        newUser.setPassword("");            //clear password field
        newUser.setConfirmPassword("");     //clear confirm password field
        newUser.setHashPassword(hashPassword);  //set hashed password

        //call dataservice
        DataServices.registerUser(context, newUser.getName(), newUser.getEmail(), newUser.getHashPassword());

        SharedPreferences pref = context.getSharedPreferences(Config.TRANSACTION_PREFERENCE_VOLLEY_NAME, Context.MODE_PRIVATE);

        String message = pref.getString("message", "");
        if(!message.isEmpty()){
            if(message.equalsIgnoreCase("success")){
                return true;
            }else{
                return false;
            }
        }

        return false;

    }

    public static UserModel authenticateUser(Context context, UserModel loginUser){

        UserModel loggedUser = new UserModel();

        //hashed password
        String hashPassword = HashingAlgorithms.MD5Hashing(loginUser.getPassword());
        loginUser.setPassword("");            //clear password field
        loginUser.setConfirmPassword("");     //clear confirm password field
        loginUser.setHashPassword(hashPassword);  //set hashed password

        //call dataservice
        DataServices.loggingUser(context, loginUser.getEmail(), loginUser.getHashPassword());

        SharedPreferences pref = context.getSharedPreferences(Config.TRANSACTION_PREFERENCE_VOLLEY_NAME, Context.MODE_PRIVATE);

        String message = pref.getString("message", "");
        if(!message.isEmpty()){
            if(message.equalsIgnoreCase("success")){

                loggedUser.set_id(pref.getString("_id", ""));
                loggedUser.setName(pref.getString("name", ""));
                loggedUser.setType(pref.getString("type", ""));
                loggedUser.setEmail(pref.getString("email", ""));
                //worker field current unavailable
                return loggedUser;

            }else{
                //nothing happen, return empty object
            }
        }

        return null;
    }
}
