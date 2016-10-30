package com.smartrecycling.smartrecycling;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class search extends AppCompatActivity {

    private Firebase ref;
    private String inputMaterial;
    private String inputName;
    private ArrayList<Products> productList;
    private HashMap<String, String> List;
    private int type;
    private Set set;
    private Button retrieve;
    private Products temp1;
    private ArrayList<String> arr;
    private ArrayAdapter<String> adapter;
    ListView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://smartrecycling-147902.firebaseio.com/");

        retrieve = (Button) findViewById(R.id.buttonFind);
        productList = new ArrayList<>();
        lstView = (ListView) findViewById(R.id.lstproducts);
    }

    public void onRetrieveList(View view)
    {
        inputMaterial = ((EditText) findViewById(R.id.editTextMaterial)).getText().toString();
        inputName = ((EditText) findViewById(R.id.productnameedittext)).getText().toString();
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
                                String temp = me.getValue().toString();
                                temp1 = dataSnapshot.child("Products").child(temp).getValue(Products.class);
                                if (temp1.name.equals(inputName))
                                    productList.add(temp1);
                            }
                        }
                        arr = new ArrayList<>();
                        for (int i = 0; i<productList.size();i++){
                            arr.add(productList.get(i).name);
                        }

                        adapter=new ArrayAdapter<String>
                                (getApplicationContext(),android.R.layout.simple_list_item_1, arr);
                        lstView.setAdapter(adapter);
                        break;
                    case 2:
                        getProductList(inputMaterial, "material", dataSnapshot);
                        arr = new ArrayList<>();
                        for (int i = 0; i<productList.size();i++){
                            arr.add(productList.get(i).name);
                        }

                        adapter=new ArrayAdapter<String>
                                (getApplicationContext(),android.R.layout.simple_list_item_1, arr);
                        lstView.setAdapter(adapter);
                        break;
                    case 3:
                        getProductList(inputName, "productName", dataSnapshot);
                        arr = new ArrayList<>();
                        for (int i = 0; i<productList.size();i++){
                            arr.add(productList.get(i).name);
                        }

                        adapter=new ArrayAdapter<String>
                                (getApplicationContext(),android.R.layout.simple_list_item_1, arr);
                        lstView.setAdapter(adapter);
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

    protected void getProductList(String input, String typeSearch, DataSnapshot dataSnapshot)
    {
        List = (HashMap<String, String>) dataSnapshot.child("Search").child(typeSearch).child(input).getValue();
        if (List != null)
        {
            set = List.entrySet();
            Iterator a = set.iterator();
            while (a.hasNext()) {
                Map.Entry me = (Map.Entry) a.next();
                String temp = me.getValue().toString();
                temp1 = dataSnapshot.child("Products").child(temp).getValue(Products.class);
                productList.add(temp1);
            }
        }
    }

}
