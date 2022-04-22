package com.example.hive.ui.onboarding;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hive.R;
import com.example.hive.databinding.FragmentLoginBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;


public class LoginFragment extends Fragment {


    /**
     * This fragment should handle the login form ,
     */
    private static String TAG = "com.example.hive.ui.onboarding.LoginFragment.class";
    private FirebaseAuth mAuth;
    private FragmentLoginBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;
    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.view_onboarding_container);
        navController = navHostFragment.getNavController();

        //On login Button Clicked checking users credentials in firebase database.
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable email = binding.etEmail.getText();
                Editable password = binding.etPassword.getText();
                if(email != null && password != null){
                    checkLoginCredentials(email.toString(), password.toString());
                }

                //navController.navigate(R.id.action_loginFragment_to_customerActivity);

            }
        });

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
            }
        });


        View view = binding.getRoot();


        return view;
    }

    public void checkLoginCredentials(String email, String password) {
        mAuth = FirebaseAuth.getInstance();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "checkLoginCredentials - LoginWithEmailAndPassword:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection("users").document(uid)
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        Map<String, Object> userDetails = document.getData();
                                        setLoggedInUserDetails(userDetails,document.getId());
                                        if ("Customer".equals((String) userDetails.get("user_type"))) {
                                            navController.navigate(R.id.action_loginFragment_to_customerActivity);
                                        } else if ("ServiceProvider".equals((String) userDetails.get("user_type"))) {
                                            navController.navigate(R.id.action_loginFragment_to_serviceProviderActivity);
                                        }

                                    } else {
                                        Log.w(TAG, "Error getting documents.", task.getException());
                                    }
                                }
                            });


                        } else {
                            Log.w(TAG, "checkLoginCredentials - LoginWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setLoggedInUserDetails(Map<String, Object> userDetails, String uid) {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
        sharedEditor.putBoolean("isUserLoggedIn", true);
        sharedEditor.putString("user_type", (String) userDetails.get("user_type"));
        sharedEditor.putString("city", (String) userDetails.get("city"));
        sharedEditor.putString("name", (String) userDetails.get("name"));
        sharedEditor.putString("mobile", (String) userDetails.get("mobile"));
        sharedEditor.putString("email", (String) userDetails.get("email"));
        sharedEditor.putString("uid", uid);
        if(userDetails.get("service_type") != null){
            sharedEditor.putString("service_type", (String) userDetails.get("service_type"));
        }
        sharedEditor.commit();
        sharedEditor.apply();
    }

}

