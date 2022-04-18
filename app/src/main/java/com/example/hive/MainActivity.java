package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.hive.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

//This activity should check user is logged in or not ,
//User interface of the activity should show loading when checking user loggedin details
//User interface should have option for selecting service provider and customer button.
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // DataBindingUtil.inflate(inflater,R.layout.fragment_splash_screen, container, false);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.view_onboarding_container);
        NavController navController = navHostFragment.getNavController();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                sharedEditor = sharedPreferences.edit();
                if (isItFirestTime()) {
                    //frstTime.setText("First Time");
                } else {
                    navController.navigate(R.id.action_splashScreenFragment_to_loginFragment);
                }


            }
        }, 3000);

       // getSupportActionBar().hide();
    }

    public boolean isItFirestTime() {
        if (sharedPreferences.getBoolean("firstTime", true)) {
            sharedEditor.putBoolean("firstTime", false);
            sharedEditor.commit();
            sharedEditor.apply();
            return true;
        } else {
            return false;
        }
    }


}