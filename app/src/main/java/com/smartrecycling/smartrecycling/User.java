package com.smartrecycling.smartrecycling;

import java.util.ArrayList;

/**
 * Created by OP on 10/29/2016.
 */

public class User {
    public String username;
    public String email;
    //cai may la private
    public String phone;
    public String id;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String id, String username, String email, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

}
