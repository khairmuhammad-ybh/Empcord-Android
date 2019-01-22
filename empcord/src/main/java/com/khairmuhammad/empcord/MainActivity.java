package com.khairmuhammad.empcord;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.khairmuhammad.empcord.fragments.Login;

/**
 * A base application that handle all GUI contents, transition between activities/fragment.
 * This is the main app that will be build into .apk
 *
 * Module contributions: Muhammad Khairi & Oh Shan Ge
 *
 * ----------------------------------------------------
 *
 * Module dependencies:
 * 1. statisics
 * 2. transitions
 *
 * Extra Note:
 *                      Statistics  |  empcord   |  dataservices   |   transitions
 * Muhammad Khairi                        *              *                  *
 * Oh Shan Ge                *            *              *
 *
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //transact to login fragment
        Login loginFragment = new Login();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(
                R.id.fragment_container,
                loginFragment,
                loginFragment.getTag())
                .commit();

    }
}
