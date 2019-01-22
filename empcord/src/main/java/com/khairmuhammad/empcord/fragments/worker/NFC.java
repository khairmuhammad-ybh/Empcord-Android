package com.khairmuhammad.empcord.fragments.worker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khairmuhammad.empcord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NFC extends Fragment {


    public NFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nfc, container, false);

        /**
         * Require to implement NFC technology in here and invoke
         * methods in transition module to store data to database
         */

        return rootView;
    }

}
