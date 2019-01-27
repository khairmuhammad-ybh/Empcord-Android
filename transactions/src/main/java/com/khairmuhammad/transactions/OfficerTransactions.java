package com.khairmuhammad.transactions;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.khairmuhammad.dataservices.DataServices;
import com.khairmuhammad.dataservices.configurations.ConnTags;
import com.khairmuhammad.dataservices.connections.ConnectUrl;
import com.khairmuhammad.transactions.configuration.Config;
import com.khairmuhammad.transactions.models.UserModel;
import com.khairmuhammad.transactions.security.HashingAlgorithms;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OfficerTransactions {

    public static String REGISTER_WORKER_URL = ConnectUrl.URL_REGISTER_WORKER;
    public static String REGISTER_USER_URL = ConnectUrl.URL_REGISTER;
    public static String LOGIN_USER_URL = ConnectUrl.URL_LOGIN;

//    public static boolean createWorker(final Context context, UserModel newWorker, String compID){
//
//        /**
//         * Hash password
//         */
//        //hashed password
//        String hashPassword = HashingAlgorithms.MD5Hashing(newWorker.getPassword());
//        newWorker.setPassword("");            //clear password field
//        newWorker.setConfirmPassword("");     //clear confirm password field
//        newWorker.setHashPassword(hashPassword);  //set hashed password
//
//        /**
//         * Invoke dataservices to register into database
//         * return true if success, else false
//         */
//        DataServices.registerWorker(context, compID, newWorker.getName(), newWorker.getEmail(), newWorker.getHashPassword(), new DataServices.VolleyCallback() {
//            @Override
//            public void onSuccess(JSONObject jsonObject) {
//                try {
//                    String message = jsonObject.getString("message");
//                    if(message.equalsIgnoreCase("success")){
//
//                        //Shared Preference
//                        SharedPreferences sharedPreferences;
//
//                        sharedPreferences = context.getSharedPreferences(Config.TRANSACTION_PREFERENCE_VOLLEY_NAME_WORKER, Context.MODE_PRIVATE);
//
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//
//                        JSONArray users = jsonObject.getJSONArray("user");
////                        JSONObject user = users.getJSONObject(0);
//                        JSONObject user = jsonObject.getJSONObject("user");
//
//                        //shared preference setup
//                        String _id = user.getString("_id");
//                        String name = user.getString("name");
//                        String password = user.getString("password");
//                        String type = user.getString("type");
//                        String email = user.getString("email");
//                        String companyId = user.getString("companyId");
//                        String officerId = user.getString("officerId");
//
//                        //storing data into sharedPref
//                        editor.putString("_id", _id);
//                        editor.putString("name", name);
//                        editor.putString("password", password);
//                        editor.putString("type", type);
//                        editor.putString("email", email);
//                        editor.putString("companyId", companyId);
//                        editor.putString("officerId", officerId);
//
//                        editor.putString("message", message);
//                        editor.apply();
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        SharedPreferences pref = context.getSharedPreferences(Config.TRANSACTION_PREFERENCE_VOLLEY_NAME_WORKER, Context.MODE_PRIVATE);
//
//        String message = pref.getString("message", "");
//        Log.d("TAG", "Officertransac: " + message);
//
//        if(!message.isEmpty()){
//            if(message.equalsIgnoreCase("success")){
//
//                //worker field current unavailable
//
//                //Worker successfully registered
//                return true;
//
//            }else{
//                //nothing happen, return empty object
//            }
//        }
//
//        return false;
//    }

    public static boolean removeUser(UserModel removedUser){

        /**
         * Invoke dataservices to remove user from database
         * return true if success, else false
         */

        return true;
    }

    public static boolean unbindUser(UserModel unbindUser){

        /**
         * Invoke dataservces to unbind user
         * return true if success, else false
         */

        return true;
    }

    public static boolean passwordReset(UserModel userReset, String newPassword){

        /**
         * Hash new password
         */
        String hashPassword = HashingAlgorithms.MD5Hashing(newPassword);
        userReset.setPassword("");            //clear password field
        userReset.setConfirmPassword("");     //clear confirm password field
        userReset.setHashPassword(hashPassword);  //set hashed password

        /**
         * Invoke dataservices to reset user account into database
         * return true if success, else false
         */

        return true;
    }

    public static boolean updateWorkerEmail(UserModel userUpdate, String newEmail){
        /**
         * Invoke dataservices to update user account into database
         * return true if success, else false
         */

        return true;
    }
}
