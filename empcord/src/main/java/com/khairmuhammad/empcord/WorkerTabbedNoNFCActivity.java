package com.khairmuhammad.empcord;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.khairmuhammad.empcord.fragments.worker.Agenda;
import com.khairmuhammad.empcord.fragments.worker.NFC;
import com.khairmuhammad.empcord.fragments.worker.NFCInterface;
import com.khairmuhammad.empcord.fragments.worker.QRCode;
import com.khairmuhammad.transactions.WorkerTransactions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WorkerTabbedNoNFCActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    //Fragments
    QRCode QRFrag;
    Agenda agendaFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_tabbed_no_nfc_layout);

        //Shared Preference
        SharedPreferences sharedPreferences;

        sharedPreferences = getSharedPreferences("Auth_User", Context.MODE_PRIVATE);

        if(sharedPreferences.contains("_id")){
            String type = sharedPreferences.getString("type", "");
            if(type.equals("Officer")){
                startActivity(new Intent(this, OfficerTabbedActivity.class));
                finish();
            }else{
//                startActivity(new Intent(this, WorkerTabbedActivity.class));
            }
        }else{
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Fragment init
        QRFrag = new QRCode();
        agendaFrag = new Agenda();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(QRFrag, getString(R.string.fragment_worker_lbl_qrode));
        adapter.addFragment(agendaFrag, getString(R.string.fragment_worker_lbl_agenda));
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
