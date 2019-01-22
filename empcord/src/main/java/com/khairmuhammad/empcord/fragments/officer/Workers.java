package com.khairmuhammad.empcord.fragments.officer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khairmuhammad.empcord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Workers extends Fragment {


    public Workers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workers, container, false);
    }

}
