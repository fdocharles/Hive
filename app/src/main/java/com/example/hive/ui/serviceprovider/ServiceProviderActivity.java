package com.example.hive.ui.serviceprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.example.hive.R;
import com.example.hive.databinding.ActivityServiceProviderBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ServiceProviderActivity extends AppCompatActivity {
    private ActivityServiceProviderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.service_provider_container_view);
        NavController navController = navHostFragment.getNavController();

        // NavController navController = Navigation.findNavController(this,R.id.customer_fragments_view_container);
        BottomNavigationView bnv = binding.bottomNavigationView;
        Log.i("Service Provider Activity ",bnv.toString());
        NavigationUI.setupWithNavController(bnv,navController);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this,R.color.button_primary)));
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}