package com.smartrecycling.smartrecycling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AddProductActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_product);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        myKey ="key";
        myProduct = "product";
        addProduct=(Button)findViewById(R.id.add);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://smartrecycling-147902.firebaseio.com/");
        //Get current count
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mySys = dataSnapshot.child("SSystem").getValue(SSystem.class);
            }
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
    void writeNew(String field1, String field2, String field3, String field4) {
            //Field1: ID products
            //Field2: meterial products
            //Field3: name products
            //Field4: content products
            Products product = new Products(field1, field2, field3, field4);
            //Add index in Search Tree

            mDatabase.child("Products").child(mySys.CurrentCount + "").setValue(product);
            mDatabase.child("Search").child("material").child(field2).child(myKey +  mySys.CurrentCount + "").setValue(field1);
            mDatabase.child("Search").child("productName").child(field4).child(myProduct + mySys.CurrentCount + "").setValue(field1);
    }

    void onAddProduct(View view)
    {
        productName = ((EditText) findViewById(R.id.editText4)).getText().toString();
        productMaterial = ((EditText) findViewById(R.id.editText5)).getText().toString();
        productContent = ((EditText) findViewById(R.id.editText6)).getText().toString();
        mySys.CurrentCount++;
        writeNew(mySys.CurrentCount + "", productMaterial,productName, productContent);
        mDatabase.child("SSystem").setValue(mySys);
    }
}
