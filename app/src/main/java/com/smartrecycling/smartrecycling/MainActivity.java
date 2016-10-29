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

public class MainActivity extends AppCompatActivity {
    private TextView infor;
    private DatabaseReference mDatabase;
    private String username;
    private String email;
    private String userID;
    private Button addData;
    private Button showData;
    private Firebase ref;
    private String a;
    private int count;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        a = "key";

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://smartrecycling-147902.firebaseio.com/");

        //Create tree index
        //Phan nay chi la DEMO, tat ca de Chuong lo
        addData = (Button) findViewById(R.id.add);
        showData = (Button) findViewById(R.id.retrieve);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Show data from firesbase
        infor = (TextView) findViewById(R.id.infor);

    }
    ;

    private void writeNewUser(int type, String field1, String field2, String field3, String field4) {
        if (type == 0)
        {
            User user = new User(field1, field2, field3, field4);
            mDatabase.child("Users").child(field1).setValue(user);
        } else if (type == 1)
        {
            //Field1: ID products
            //Field2: meterial products
            //Field3: name products
            //Field4: content products
            Products product = new Products(field1, field2, field3, field4);

            //Add index in Search Tree
            count++;
            //mDatabase.child("Products").child(field1).setValue(product);
            mDatabase.child("Products").setValue(product);
            mDatabase.child("Search").child("material").child(field2).child(a + count + "").setValue(field1);
            mDatabase.child("")
        }

    }
    //Add new user
    void onAddData(View view) {
        //Input data for user
        userID = ((EditText) findViewById(R.id.editText)).getText().toString();
        username = ((EditText) findViewById(R.id.editText2)).getText().toString();
        email = ((EditText) findViewById(R.id.editText3)).getText().toString();

        writeNewUser(0, userID, username, email, "0");


    }


    //Add new products
    void onAddProduct(View view)
    {
        writeNewUser(1, "1", "material1", "name1", "content1");
        writeNewUser(1, "2", "material1", "name2", "content2");
        writeNewUser(1, "3", "material3", "name3", "content3");
        writeNewUser(1, "4", "material4", "name4", "content4");
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

    void onRetrieveProducts(View view)
    {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap<String, String> a = (HashMap<String, String>)dataSnapshot.child("Search").child("material").child("material1").getValue();

                Set set = a.entrySet();
                Iterator i = set.iterator();
                while (i.hasNext())
                {
                    Map.Entry me = (Map.Entry)i.next();
                    Toast.makeText(getApplicationContext(), me.getValue().toString(), Toast.LENGTH_SHORT).show();
                }


            }

            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

}


