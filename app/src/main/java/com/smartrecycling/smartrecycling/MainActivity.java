package com.smartrecycling.smartrecycling;

import android.app.TabActivity;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import com.firebase.client.Query;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends TabActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        // Tab for Home
        TabHost.TabSpec homegspec = tabHost.newTabSpec("Home");

        homegspec.setIndicator("", getResources().getDrawable(R.drawable.home_tabspec));
        Intent timingIntent = new Intent(this, Home.class);
        homegspec.setContent(timingIntent);

        // Tab for Search
        TabHost.TabSpec searchspec = tabHost.newTabSpec("Search");
        // setting Title and Icon for the Tab
        searchspec.setIndicator("", getResources().getDrawable(R.drawable.find_tabspec));
        Intent groupIntent = new Intent(this, search.class);
        searchspec.setContent(groupIntent);

        // Tab for Profile
        TabHost.TabSpec profilespec = tabHost.newTabSpec("Profile");

        profilespec.setIndicator("", getResources().getDrawable(R.drawable.profile_tabspec));
        Intent setupIntent = new Intent(this, profile.class);
        profilespec.setContent(setupIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(homegspec);
        tabHost.addTab(searchspec);
        tabHost.addTab(profilespec);
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)
        {
            tabHost.getTabWidget().getChildAt(i).setPadding(0,0,0,0);
        }
    }
    ;
    //Add new user
   /* void onAddData(View view) {
        //Input data for user
        userID = ((EditText) findViewById(R.id.editText)).getText().toString();
        username = ((EditText) findViewById(R.id.editText2)).getText().toString();
        email = ((EditText) findViewById(R.id.editText3)).getText().toString();

        writeNewUser(0, userID, username, email, "0");
    }*/


    //Add new products

    //Get data from firebase --     BUILDING ----
}


