package com.khairmuhammad.empcord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.khairmuhammad.statistics.Statistics;
import com.khairmuhammad.transitions.Transitions;

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
        TextView tvTest = findViewById(R.id.tvTest);
        tvTest.setText(Statistics.MESSAGE + " to Empcord mainActivity");
        TextView tvTest2 = findViewById(R.id.tvTest2);
        tvTest2.setText(Transitions.MESSAGE + " to Empcord mainActivity");

//        Log.d("Github", "Test commit");h
    }
}
