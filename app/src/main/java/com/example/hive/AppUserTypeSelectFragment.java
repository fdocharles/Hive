package com.example.hive;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hive.databinding.FragmentAppUserTypeSelectBinding;

public class AppUserTypeSelectFragment extends Fragment {

    private FragmentAppUserTypeSelectBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_app_user_type_select, container, false);


        binding.getHiredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect to service provider registration   -- will discuss on this.

                //For now redirecting it to login screen
                Navigation.findNavController(view).navigate(R.id.action_appUserTypeSelectFragment_to_loginFragment);

            }
        });


        binding.hireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect to customer registration   -- will discuss on this.

                //For now redirecting it to login screen
                Navigation.findNavController(view).navigate(R.id.action_appUserTypeSelectFragment_to_loginFragment);

            }
        });

        return binding.getRoot();
    }
}