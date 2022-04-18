package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.hive.databinding.ActivityCustomerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerActivity extends AppCompatActivity {

    private ActivityCustomerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.customer_fragments_view_container);
        NavController navController = navHostFragment.getNavController();

       // NavController navController = Navigation.findNavController(this,R.id.customer_fragments_view_container);
        BottomNavigationView bnv = binding.bottomNavigationView;
        Log.i("Testing",bnv.toString());
        NavigationUI.setupWithNavController(bnv,navController);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}