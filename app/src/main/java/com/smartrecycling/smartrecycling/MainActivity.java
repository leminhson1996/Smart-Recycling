package com.smartrecycling.smartrecycling;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView infor;
    private DatabaseReference mDatabase;
    private String username;
    private String email;
    private String userID;
    private Button addData;
    private Button showData;
    private Firebase ref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://smartrecycling-147902.firebaseio.com/");

        //Phan nay chi la DEMO, tat ca de Chuong lo
        addData = (Button) findViewById(R.id.add);
        showData = (Button) findViewById(R.id.retrieve);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Show data from firesbase
        infor = (TextView) findViewById(R.id.infor);

    }
    ;

    private void writeNewUser(String userId, String name, String email, String phone) {
        User user = new User(userId, name, email, phone);
        mDatabase.child("Users").child(userId).setValue(user);
    }
    //Add new user
    void onAddData(View view) {
        //Input data for user
        userID = ((EditText) findViewById(R.id.editText)).getText().toString();
        username = ((EditText) findViewById(R.id.editText2)).getText().toString();
        email = ((EditText) findViewById(R.id.editText3)).getText().toString();

        writeNewUser(userID, username, email, "0");
    }

    //Get data from firebase --     BUILDING ----
    void onRetrieveData(View view)
    {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                if (dataSnapshot.child("users").child("10").child("username").getValue() != null)
                {
                    String buffer = dataSnapshot.child("users").child("10").child("username").getValue().toString();
                    Toast.makeText(getApplicationContext(), buffer, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "ahihi", Toast.LENGTH_SHORT).show();
                }*/
                if (dataSnapshot.child("users").child("1").getValue() != null)
                {
                    User tmp = dataSnapshot.child("users").child("1").getValue(User.class);

                    Toast.makeText(getApplicationContext(), tmp.email, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "ahihi", Toast.LENGTH_SHORT).show();
                }
            }

            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

}


