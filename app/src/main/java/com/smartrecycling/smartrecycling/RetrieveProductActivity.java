package com.smartrecycling.smartrecycling;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RetrieveProductActivity extends AppCompatActivity {
    private Firebase ref;
    private String inputMaterial;
    private String inputName;
    private ArrayList<Products> productList;
    private HashMap<String, String> List;
    private int type;
    private Set set;
    private Button retrieve;
    private Products temp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_product);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://smartrecycling-147902.firebaseio.com/");

        retrieve = (Button) findViewById(R.id.retrieve);
        productList = new ArrayList<>();
    }

    void onRetrieveList(View view)
    {
        inputMaterial = ((EditText) findViewById(R.id.editText7)).getText().toString();
        inputName = ((EditText) findViewById(R.id.editText8)).getText().toString();
        if (!inputMaterial.isEmpty() && !inputName.isEmpty())
            type = 1;
        else    if (!inputMaterial.isEmpty())
            type = 2;
        else    if (!inputName.isEmpty())
            type = 3;
        else    type = 4;


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                switch (type) {
                    case 1:
                        List = (HashMap<String, String>) dataSnapshot.child("Search").child("material").child(inputMaterial).getValue();
                        if (List != null)
                        {
                            set = List.entrySet();
                            Iterator i = set.iterator();
                            while (i.hasNext()) {
                                Map.Entry me = (Map.Entry) i.next();
                                String temp = (String) me.getValue();
                                temp1 = dataSnapshot.child("Products").child(temp).getValue(Products.class);
                                if (temp1.name.equals(inputName))
                                    productList.add(temp1);
                            }
                        }
                        break;
                    case 2:
                        getProductList(inputMaterial, dataSnapshot);
                        break;
                    case 3:
                        getProductList(inputName, dataSnapshot);
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(), "Input at least one field!!!!!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    protected void getProductList(String input, DataSnapshot dataSnapshot)
    {
        List = (HashMap<String, String>) dataSnapshot.child("Search").child("material").child(input).getValue();
        if (List != null)
        {
            set = List.entrySet();
            Iterator a = set.iterator();
            while (a.hasNext()) {
                Map.Entry me = (Map.Entry) a.next();
                String temp = (String) me.getValue();
                temp1 = dataSnapshot.child("Products").child(temp).getValue(Products.class);
                productList.add(temp1);
            }
            //Toast.makeText(getApplicationContext(), productList.size() + "", Toast.LENGTH_SHORT).show();
        }
        //else    Toast.makeText(getApplicationContext(), "not in database", Toast.LENGTH_SHORT).show();
    }

    protected void typeProcess()
    {

    }
}
