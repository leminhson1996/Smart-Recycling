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
    public int id;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(int id, String name, String email, String avatarLink) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatarLink=avatarLink;
    }

    public void writeNew(String name, String email, String avatarLink, DatabaseReference mDatabase, SSystem mySys) {

        User user = new User(mySys.UserCount, name, email, avatarLink);
        mDatabase.child("User").child(mySys.UserCount + "").setValue(user);
        mySys.UserCount++;
    }
}
