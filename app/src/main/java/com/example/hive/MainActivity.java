package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//This activity should check user is logged in or not ,
//User interface of the activity should show loading when checking user loggedin details
//User interface should have option for selecting service provider and customer button.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

    }
}