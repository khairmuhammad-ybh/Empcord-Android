package com.khairmuhammad.transactions;

import android.app.Activity;

import com.khairmuhammad.transactions.models.UserModel;
import com.khairmuhammad.transactions.security.HashingAlgorithms;

public class OfficerTransactions {

    public static boolean createWorker(UserModel newUser){

        /**
         * Hash password
         */
        //hashed password
        String hashPassword = HashingAlgorithms.MD5Hashing(newUser.getPassword());
        newUser.setPassword("");            //clear password field
        newUser.setConfirmPassword("");     //clear confirm password field
        newUser.setHashPassword(hashPassword);  //set hashed password

        /**
         * Invoke dataservices to register into database
         * return true if success, else false
         */


        return true;
    }

    public static boolean removeUser(UserModel removedUser){

        /**
         * Invoke dataservices to remove user from database
         * return true if success, else false
         */

        return true;
    }

    public static boolean unbindUser(UserModel unbindUser){

        /**
         * Invoke dataservces to unbind user
         * return true if success, else false
         */

        return true;
    }

    public static boolean passwordReset(UserModel userReset, String newPassword){

        /**
         * Hash new password
         */
        String hashPassword = HashingAlgorithms.MD5Hashing(newPassword);
        userReset.setPassword("");            //clear password field
        userReset.setConfirmPassword("");     //clear confirm password field
        userReset.setHashPassword(hashPassword);  //set hashed password

        /**
         * Invoke dataservices to reset user account into database
         * return true if success, else false
         */

        return true;
    }

    public static boolean updateWorkerEmail(UserModel userUpdate, String newEmail){
        /**
         * Invoke dataservices to update user account into database
         * return true if success, else false
         */

        return true;
    }
}
