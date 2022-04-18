package com.example.hive;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AppUserTypeSelectFragment extends Fragment {

    Button getHiredBtn, hireBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_user_type_select, container, false);

        getHiredBtn = (Button) view.findViewById(R.id.get_hired_btn);
        hireBtn = (Button) view.findViewById(R.id.hire_btn);

        getHiredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect to service provider registration
            }
        });


        hireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect to customer registration
            }
        });

        return view;
    }
}