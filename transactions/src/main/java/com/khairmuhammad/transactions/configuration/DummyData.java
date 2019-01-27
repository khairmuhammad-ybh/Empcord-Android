package com.khairmuhammad.transactions.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.khairmuhammad.dataservices.FetchingServices;
import com.khairmuhammad.dataservices.configurations.ConnTags;
import com.khairmuhammad.transactions.models.CompanyModel;
import com.khairmuhammad.transactions.models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DummyData {

    /**
     * Create any object need related to user.
     * Get data object from database and create
     * a user list based on model
     * @return
     */

    public static List<CompanyModel> dummyCompany;

    public static List<UserModel> dummyUserList(){
        List<UserModel> userList = new ArrayList<>();

        UserModel user1 = new UserModel();
        user1.setName("User1");
        user1.setEmail("user1@email.com");
        user1.setType("Worker");
        user1.setOfficerName("ABC");
        user1.setCompanyName("MC");
        user1.setZone(1);

        UserModel user2 = new UserModel();
        user2.setName("User2");
        user2.setEmail("user2@email.com");
        user2.setType("Worker");
        user2.setOfficerName("DEF");
        user2.setCompanyName("CV");
        user2.setZone(2);

        UserModel user3 = new UserModel();
        user3.setName("User3");
        user3.setEmail("user3@email.com");
        user3.setType("Worker");
        user3.setOfficerName("ABC");
        user3.setCompanyName("MC");
        user3.setZone(1);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        return userList;
    }

//    public static List<CompanyModel> populateCompany(Context context){
//        List<CompanyModel> companyModelList = new ArrayList<>();
//
//        getCompanies(context);
//        companyModelList = parseJSON(companies);
//
//        return  companyModelList;
//
//    }

//    public static void populateCompany(final Context context){
//
//        FetchingServices.getString(context, new FetchingServices.VolleyCallback() {
//            @Override
//            public void onSuccess(String response) {
//                Log.d(ConnTags.TAG_VOLLEY, "From DummyData" + response);
//                parseJSON(context, response);
//            }
//        });
//    }

    public static String COMPANY_URL = FetchingServices.GET_COMPANIES_ROUTE;

    private static void parseJSON(String response){

//        List<CompanyModel> companyModelList = new ArrayList<>();

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
            }
        }catch (JSONException e){
            Log.d(ConnTags.TAG_VOLLEY, e.getMessage());
        }

//        //Shared Preference
//        SharedPreferences sharedPreferences;
//
//        sharedPreferences = context.getSharedPreferences("Companies", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(dummyCompany);
//        editor.putString("CompanyList", json);
//        editor.apply();

    }
}
