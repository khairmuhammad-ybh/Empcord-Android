package com.khairmuhammad.empcord.fragments.officer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.khairmuhammad.empcord.BlankCanvas;
import com.khairmuhammad.empcord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Accounts extends Fragment implements View.OnClickListener {

    //Global variables
    Button officer_btn_creation, officer_btn_update, officer_btn_reset, officer_btn_remove;

    public Accounts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_accounts, container, false);

        officer_btn_creation = rootView.findViewById(R.id.officer_btn_creation);
        officer_btn_update = rootView.findViewById(R.id.officer_btn_update);
        officer_btn_reset = rootView.findViewById(R.id.officer_btn_reset);
        officer_btn_remove = rootView.findViewById(R.id.officer_btn_remove);

        officer_btn_creation.setOnClickListener(this);
        officer_btn_update.setOnClickListener(this);
        officer_btn_reset.setOnClickListener(this);
        officer_btn_remove.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();

        bundle.putString("SELECTED", ((Button)v).getText().toString());
        Intent intent = new Intent(getContext(), BlankCanvas.class);
        intent.putExtras(bundle);
        startActivity(intent);

//        switch (v.getId()){
//            case R.id.officer_btn_creation:
//                bundle.putString("SELECTED", ((Button)v).getText().toString());
//                Intent intent = new Intent(getContext(), BlankCanvas.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//
//        }
    }
}
