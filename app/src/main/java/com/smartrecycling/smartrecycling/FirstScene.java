package com.smartrecycling.smartrecycling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstScene extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            Intent main=new Intent(getApplicationContext(),Login.class);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
            finish();
    }
}
