package com.khairmuhammad.empcord.fragments.worker;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.khairmuhammad.empcord.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NFC extends Fragment {

    //Global variables
    TextView nfc_tv_tag_location, nfc_tv_tag_status, nfc_tv_tag_message;


    public NFC() {
        // Required empty public constructor
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

        return rootView;
    }

}
