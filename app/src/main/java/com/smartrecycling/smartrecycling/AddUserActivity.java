package com.smartrecycling.smartrecycling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserActivity extends AppCompatActivity {
    private String productID;
    private String productName;
    private String productMaterial;
    private String productContent;
    private DatabaseReference mDatabase;
    private SSystem mySys;
    private String myProduct;
    private String myKey;
    private Firebase ref;
    private Button addProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://smartrecycling-147902.firebaseio.com/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mySys = dataSnapshot.child("SSystem").getValue(SSystem.class);
            }

            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }



}
