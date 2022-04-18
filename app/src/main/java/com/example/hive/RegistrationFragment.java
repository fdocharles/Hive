package com.example.hive;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hive.databinding.FragmentRegistrationBinding;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationFragment extends Fragment {
    private static String TAG ="RegistrationFragment.class";
    private FirebaseAuth mAuth;
    private FragmentRegistrationBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_registration, container, false);

       //this will check the registration in firebase database
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here will the logic for registration in firebase database
            }
        });

        binding.btnMoveToLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment);
            }
        });

        View view = binding.getRoot();


        return view;
    }


    /*  // Initialize Firebase Auth
       // mAuth = FirebaseAuth.getInstance();
        FirebaseService firebaseService = new FirebaseService();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                String contactNumber = binding.etContactNumber.getText().toString();
                UserType type  = UserType.ServiceProvider;
               if( binding.rgUserType.getCheckedRadioButtonId() == R.id.rb_customer){
                   type = UserType.Customer;
               }
                //createAccount(email,password);
                if(firebaseService.registerAccount(email,password,contactNumber,type)){
                    Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Unsuccessful in Registration", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.btnLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment);
            }
        });*/
}