package com.khairmuhammad.transactions;

import android.content.Context;
import android.content.SharedPreferences;

import com.khairmuhammad.dataservices.DataServices;
import com.khairmuhammad.transactions.configuration.Config;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Handling all transactions, nfc & QR code, dealing a lot with database, Mysql and/or SQLite
 * Dependant on dataservices module
 *
 * Module contribution: Muhammad Khairi
 */

public class WorkerTransactions {

    public void insertQrCode (){
        /**
         * insert event (tag type: QR Code) into the database by calling the dataservices module
         * and store into Shared Preference, so to be able to retrieved in the QRCode fragment
         */
    }
    public static String[] getNFCPref(){
        String[] prefNFC = {Config.TRANSACTION_PREFERENCES_NFC_NAME,
                Config.TRANSACTION_PREFERENCES_NFC_LOCATION,
                Config.TRANSACTION_PREFERENCES_NFC_STATUS,
                Config.TRANSACTION_PREFERENCES_NFC_MESSAGE};

        return prefNFC;
    }

    public static boolean insertNfc(Context context, String nfcResult){
        /**
         * insert event (tag type: NFC) into the database by calling the dataservices module
         * and store into Shared Preference, so to be able to retrieved in the NFC fragment
         */

        /**
         * invoke methods in dataservices to get location from database
         */


        /**
         * Store NFC tag in Shared Preference
         */
        try{
            SharedPreferences sharedPreferences;

            sharedPreferences = context.getSharedPreferences(Config.TRANSACTION_PREFERENCES_NFC_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(Config.TRANSACTION_PREFERENCES_NFC_MESSAGE, nfcResult);
            editor.apply();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void statusNFC(Context context, String nfcStatus){
        /**
         * insert event (tag type: NFC) into the database by calling the dataservices module
         * and store into Shared Preference, so to be able to retrieved in the NFC fragment
         */
        try{
            SharedPreferences sharedPreferences;

            sharedPreferences = context.getSharedPreferences(Config.TRANSACTION_PREFERENCES_NFC_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(Config.TRANSACTION_PREFERENCES_NFC_STATUS, nfcStatus);
            editor.apply();

        }catch (Exception e){
            //immediately thrown
        }
    }
}
