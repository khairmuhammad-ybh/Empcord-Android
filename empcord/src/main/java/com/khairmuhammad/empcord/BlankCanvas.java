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
import com.khairmuhammad.empcord.fragments.officer.reports.management.Monthly;
import com.khairmuhammad.empcord.fragments.officer.reports.management.Worker;
import com.khairmuhammad.empcord.fragments.officer.workers.management.Location;
import com.khairmuhammad.empcord.fragments.officer.workers.management.Reminder;

public class BlankCanvas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank_canvas);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String type = bundle.getString("TYPE");
        String selectedOption = bundle.getString("SELECTED");

        FragmentManager manager = getSupportFragmentManager();

        Log.d(Tags.TAG_TRANSITION, "selected Option: " + selectedOption);

        switch (type){
            case "Account":
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
                break;
            case "Worker":
                switch (selectedOption){
                    case "Location":

                        Location locationFragment = new Location();

                        manager.beginTransaction().replace(
                                R.id.fragment_container,
                                locationFragment,
                                locationFragment.getTag())
                                .commit();
                        break;
                    case "Reminder":
                        Reminder reminderFragment = new Reminder();

                        manager.beginTransaction().replace(
                                R.id.fragment_container,
                                reminderFragment,
                                reminderFragment.getTag())
                                .commit();
                        break;
                }
                break;
            case "Report":
                switch (selectedOption){
                    case "Worker\'s Report":
                        Worker workerReportFragment = new Worker();

                        manager.beginTransaction().replace(
                                R.id.fragment_container,
                                workerReportFragment,
                                workerReportFragment.getTag())
                                .commit();
                        break;
                    case "Monthly Report":
                        Monthly monthlyReportFragment = new Monthly();

                        manager.beginTransaction().replace(
                                R.id.fragment_container,
                                monthlyReportFragment,
                                monthlyReportFragment.getTag())
                                .commit();
                        break;
                }
                break;
        }
    }
}
