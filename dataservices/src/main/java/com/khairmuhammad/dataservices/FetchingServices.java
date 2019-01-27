package com.khairmuhammad.dataservices;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khairmuhammad.dataservices.connections.ConnectUrl;

public class FetchingServices {

    public static String GET_COMPANIES_ROUTE = ConnectUrl.URL_GET_COMPANIES;
    public static String GET_ALL_FOREMAN = ConnectUrl.URL_GET_FOREMAN;
    public static String URL_UPDATE_FOREMAN = ConnectUrl.URL_UPDATE_FOREMAN;
    public static String URL_REMOVE_FOREMAN = ConnectUrl.URL_REMOVE_FOREMAN;

//    public interface VolleyCallback{
//        void onSuccess(String response);
//    }

//    public static void getString(Context context, final VolleyCallback callback){
//        StringRequest getRequest = new StringRequest(Request.Method.GET, ConnectUrl.URL_GET_COMPANIES, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                callback.onSuccess(response);
//
//            }
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("ERROR EN LA RESPUESTA", error.toString());
//            }
//        });
//
//        //Adding string request to the queue
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        requestQueue.add(getRequest);
//    }
}
