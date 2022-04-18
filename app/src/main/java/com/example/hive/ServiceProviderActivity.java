package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ServiceProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);
        //Navigation.findNavController(this,R.id.bottomNavigationView).navigate();
        BottomNavigationView btv  = null ;
        NavigationUI.setupWithNavController(btv,Navigation.findNavController(this,R.id.bottomNavigationView));

    }
}