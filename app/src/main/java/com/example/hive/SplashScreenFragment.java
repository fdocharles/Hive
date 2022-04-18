package com.example.hive;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hive.databinding.FragmentSplashScreenBinding;


public class SplashScreenFragment extends Fragment {

    private FragmentSplashScreenBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_splash_screen, container, false);

        View view =binding.getRoot();

        return view;
    }
}