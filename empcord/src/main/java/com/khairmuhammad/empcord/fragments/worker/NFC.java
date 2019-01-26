package com.khairmuhammad.empcord.fragments.worker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khairmuhammad.empcord.R;
import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.transactions.WorkerTransactions;


/**
 * A simple {@link Fragment} subclass.
 */
public class NFC extends Fragment implements NFCInterface {

    //Global variables
    TextView nfc_tv_tag_location, nfc_tv_tag_status, nfc_tv_tag_message;
    private Bundle bundle;

    public static final String MIME_TEXT_PLAIN = "text/plain";
    public static final String TAG = "NfcDemo";


    public NFC() {
        // Required empty public constructor
    }

    public static NFC newInstance(){
        return new NFC();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nfc, container, false);

        nfc_tv_tag_location = rootView.findViewById(R.id.nfc_tv_tag_location);
        nfc_tv_tag_status = rootView.findViewById(R.id.nfc_tv_tag_status);
        nfc_tv_tag_message = rootView.findViewById(R.id.nfc_tv_tag_message);

        /**
         * NFC technology implemented in WorkerTabbedActivity, get data from sharedPreference
         * to be use in here.
         */

        String[] prefNFC = WorkerTransactions.getNFCPref(); // 0: prefName, 1: location, 2: status, 3: message

        SharedPreferences pref = getContext().getSharedPreferences(prefNFC[0], Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String status = pref.getString(prefNFC[2], null);

        if(status!= null && nfc_tv_tag_status.getText() != status){
            Log.d(Tags.TAG_TRANSITION, "In NFC fragment, NFC is " + status);
            nfc_tv_tag_status.setText(status);
        }else{
            Log.d(Tags.TAG_TRANSITION, "In NFC fragment, NFC is " + status);
            nfc_tv_tag_status.setText("Error occured");
        }

        return rootView;
    }

    @Override
    public void setDisplay(String nfcResult) {
//        nfc_tv_tag_message.setText(nfcResult);

        String[] prefNFC = WorkerTransactions.getNFCPref(); // 0: prefName, 1: location, 2: status, 3: message

        SharedPreferences pref = getContext().getSharedPreferences(prefNFC[0], Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String message = pref.getString(prefNFC[3], null);

        if(message!= null){
            Log.d(Tags.TAG_TRANSITION, "In NFC fragment, retrieving message from sharedPref : " + message);
            nfc_tv_tag_message.setText(message);
        }else{
            Log.d(Tags.TAG_TRANSITION, "In NFC fragment, Not updating");
            nfc_tv_tag_message.setText("Not updated");
        }
    }

    @Override
    public void setStatus() {
        String[] prefNFC = WorkerTransactions.getNFCPref(); // 0: prefName, 1: location, 2: status, 3: message

        SharedPreferences pref = getContext().getSharedPreferences(prefNFC[0], Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String status = pref.getString(prefNFC[2], null);

        if(status!= null && nfc_tv_tag_status.getText() != status){
            Log.d(Tags.TAG_TRANSITION, "In NFC fragment, NFC is " + status);
            nfc_tv_tag_status.setText(status);
        }else{
            Log.d(Tags.TAG_TRANSITION, "In NFC fragment, NFC is " + status);
            nfc_tv_tag_status.setText("Error occured");
        }
    }
}
