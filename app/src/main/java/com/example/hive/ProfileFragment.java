package com.example.hive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hive.MainActivity;
import com.example.hive.R;
import com.example.hive.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        setLoggedInUserDetails();
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removedLoggedInUserDetails();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("fragment_name", "LoginFragment");
                startActivity(intent);
                getActivity().finish();
            }
        });

        binding.buttonSave.setOnClickListener(view -> {});

        return  binding.getRoot();
    }

    private void removedLoggedInUserDetails() {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
        sharedEditor.clear();
        sharedEditor.commit();
        sharedEditor.apply();
    }

    private void setLoggedInUserDetails() {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        binding.etEmail.setText(sharedPreferences.getString("email",""));
        binding.etName.setText(sharedPreferences.getString("name",""));
        binding.etCity.setText(sharedPreferences.getString("city",""));
        binding.etMobile.setText(sharedPreferences.getString("mobile",""));
        if("ServiceProvider".equals(sharedPreferences.getString("user_type",""))) {
            binding.etServiceType.setText(sharedPreferences.getString("service_type", ""));
            binding.tfServiceType.setVisibility(View.VISIBLE);
        }
        else{
            binding.tfServiceType.setVisibility(View.GONE);
        }

    }
}