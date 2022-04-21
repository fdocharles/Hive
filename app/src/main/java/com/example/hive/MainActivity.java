package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.hive.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//This activity should check user is logged in or not ,
//User interface of the activity should show loading when checking user loggedin details
//User interface should have option for selecting service provider and customer button.
public class MainActivity extends AppCompatActivity {
    private static String TAG ="com.example.hive.MainActivity.class";

    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;
    private FirebaseAuth mAuth;;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // DataBindingUtil.inflate(inflater,R.layout.fragment_splash_screen, container, false);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.view_onboarding_container);
        navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this,navController);

        Bundle extras = getIntent().getExtras();
        String startFragment = "";
        if (extras != null) {
            startFragment = extras.getString("fragment_name");
            if(startFragment.equals("LoginFragment"))
            navController.navigate(R.id.loginFragment);
        }
        else{
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {


                    if (!isUserLoggedIn()) {
                        navController.navigate(R.id.action_splashScreenFragment_to_appUserTypeSelectFragment);
                    } else {
                        String userType = getLoggedInUserType();
                        if("Customer".equals(userType)){
                            navController.navigate(R.id.action_splashScreenFragment_to_customerActivity);
                        }
                        else if("ServiceProvider".equals(userType)){
                            navController.navigate(R.id.action_splashScreenFragment_to_serviceProviderActivity);
                        }

                        Log.i(TAG,"User_type : "+userType);
                        //Get User details and show appropriate activity
                    }
                }
            }, 3000);

        }

        mAuth = FirebaseAuth.getInstance();

        Log.i(TAG,"onCreate method");

       getSupportActionBar().hide();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"onStart method");
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Log.i(TAG,"Current User :" + currentUser.getEmail());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

    //Checking sharedPreferences to check whether isUserLoggedIn flag value
    public boolean isUserLoggedIn() {
        sharedPreferences = getSharedPreferences("hive",Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
        boolean isUserLoggedIn = sharedPreferences.getBoolean("isUserLoggedIn", false);
        if (!isUserLoggedIn) {
            sharedEditor.putBoolean("isUserLoggedIn", false);
            sharedEditor.commit();
            sharedEditor.apply();
        }
        return isUserLoggedIn;
    }
    //Checking sharedPreferences to check whether userType of loggedIn user
    public String getLoggedInUserType(){
        sharedPreferences = getSharedPreferences("hive",Context.MODE_PRIVATE);
        return sharedPreferences.getString("user_type",null);
    }


}