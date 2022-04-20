package com.example.hive;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hive.databinding.FragmentCustomerHomeBinding;

public class CustomerHomeFragment extends Fragment {

    private FragmentCustomerHomeBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_customer_home, container, false);
        setDataFromSharedPreferences();

        return binding.getRoot();
    }

    private void setDataFromSharedPreferences() {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        boolean isUserLoggedIn = sharedPreferences.getBoolean("isUserLoggedIn", false);
        String name = sharedPreferences.getString("name", null);
        if(name != null){
            binding.customerName.setText(name);
        }
    }
}