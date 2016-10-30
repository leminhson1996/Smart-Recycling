package com.smartrecycling.smartrecycling;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by OP on 10/29/2016.
 */

public class User {
    public String name;
    public String email;
    //cai may la private
    public String avatarLink;
    public String id;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String id, String name, String email, String avatarLink) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatarLink=avatarLink;
    }


}
