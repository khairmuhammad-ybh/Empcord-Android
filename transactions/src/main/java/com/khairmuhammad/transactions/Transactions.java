package com.khairmuhammad.transactions;

import com.khairmuhammad.dataservices.DataServices;

/**
 * Handling all transactions, nfc & QR code, dealing a lot with database, Mysql and/or SQLite
 * Dependant on dataservices module
 *
 * Module contribution: Muhammad Khairi
 */

public class Transactions {
    public static String MESSAGE = "Transactions Class dependant on " + DataServices.MESSAGE;

    public void insertQrCode (){
        /**
         * insert event (tag type: QR Code) into the database by calling the dataservices module
         * and store into Shared Preference, so to be able to retrieved in the QRCode fragment
         */
    }

    public void insertNfc(){
        /**
         * insert event (tag type: NFC) into the database by calling the dataservices module
         * and store into Shared Preference, so to be able to retrieved in the NFC fragment
         */
    }
}
