package com.khairmuhammad.transactions.configuration;

import com.khairmuhammad.transactions.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    /**
     * Create any object need related to user.
     * Get data object from database and create
     * a user list based on model
     * @return
     */

    public static List<UserModel> dummyUserList(){
        List<UserModel> userList = new ArrayList<>();

        UserModel user1 = new UserModel();
        user1.setName("User1");
        user1.setEmail("user1@email.com");
        user1.setType("Worker");
        user1.setOfficerName("ABC");
        user1.setCompanyName("MC");
        user1.setZone(1);

        UserModel user2 = new UserModel();
        user2.setName("User2");
        user2.setEmail("user2@email.com");
        user2.setType("Worker");
        user2.setOfficerName("DEF");
        user2.setCompanyName("CV");
        user2.setZone(2);

        UserModel user3 = new UserModel();
        user3.setName("User3");
        user3.setEmail("user3@email.com");
        user3.setType("Worker");
        user3.setOfficerName("ABC");
        user3.setCompanyName("MC");
        user3.setZone(1);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        return userList;
    }
}
