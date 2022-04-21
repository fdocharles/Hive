package com.example.hive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hive.databinding.FragmentRegistrationBinding;
import com.example.hive.model.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegistrationFragment extends Fragment {
    private static String TAG ="RegistrationFragment.class";
    private FirebaseAuth mAuth;
    private FragmentRegistrationBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_registration, container, false);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.view_onboarding_container);
        navController = navHostFragment.getNavController();

        //this will check the registration in firebase database
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegistration();
                //here will the logic for registration in firebase database
            }
        });

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_registrationFragment_to_loginFragment);
            }
        });

        View view = binding.getRoot();


        return view;
    }


    private void handleRegistration(){
        mAuth = FirebaseAuth.getInstance();
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        String mobile = binding.etMobile.getText().toString();
        String name = binding.etName.getText().toString();
       // String confirmPassword = binding.etConfirmPassword.getText().toString();

        Map<String,Object> hiveUserObject = new HashMap<>();
        hiveUserObject.put("email",email);
        hiveUserObject.put("mobile",mobile);
        hiveUserObject.put("name",name);
        /*hiveUserObject.put("lastname",lastname);


        if(!password.equals(confirmPassword)){
            Log.i(TAG,"Password : "+password +", Confirm Password : "+confirmPassword);
            binding.registrationErrorMessage.setText("Please confirm password");
            return;
        }*/

        UserType type  = UserType.ServiceProvider;
        if( binding.rgUserType.getCheckedRadioButtonId() == R.id.rb_customer){
            type = UserType.Customer;

        }
        hiveUserObject.put("user_type",type.toString());


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection("users").document(uid)
                                    .set(hiveUserObject)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                //save user details on sharedpreference
                                                Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                                navController.navigate(R.id.action_registrationFragment_to_loginFragment);
                                            }
                                            else{
                                                Toast.makeText(getContext(), "Unsuccessful in Registration", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Unsuccessful in Registration", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }

}