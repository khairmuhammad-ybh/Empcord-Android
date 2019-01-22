package com.khairmuhammad.empcord;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.khairmuhammad.empcord.configurations.Tags;
import com.khairmuhammad.empcord.fragments.officer.account.management.Creation;
import com.khairmuhammad.empcord.fragments.officer.account.management.Remove;
import com.khairmuhammad.empcord.fragments.officer.account.management.Reset;
import com.khairmuhammad.empcord.fragments.officer.account.management.Update;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BlankCanvas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank_canvas);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String selectedOption = bundle.getString("SELECTED");

        FragmentManager manager = getSupportFragmentManager();

        Log.d(Tags.TAG_TRANSITION, "selected Option: " + selectedOption);

        switch (selectedOption){
            case "Creation":

                Creation creationFragment = new Creation();

                manager.beginTransaction().replace(
                        R.id.fragment_container,
                        creationFragment,
                        creationFragment.getTag())
                        .commit();
                break;
            case "Update":
                Update updateFragment = new Update();

                manager.beginTransaction().replace(
                        R.id.fragment_container,
                        updateFragment,
                        updateFragment.getTag())
                        .commit();
                break;
            case "Reset":
                Reset resetFragment = new Reset();

                manager.beginTransaction().replace(
                        R.id.fragment_container,
                        resetFragment,
                        resetFragment.getTag())
                        .commit();
                break;
            case "Remove":
                Remove RemoveFragment = new Remove();

                manager.beginTransaction().replace(
                        R.id.fragment_container,
                        RemoveFragment,
                        RemoveFragment.getTag())
                        .commit();
                break;
        }




    }
}
