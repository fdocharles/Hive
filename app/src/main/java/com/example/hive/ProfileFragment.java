package com.example.hive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hive.MainActivity;
import com.example.hive.R;
import com.example.hive.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;
    private AppCompatActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity =  ((AppCompatActivity)getActivity());
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(true);


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

        binding.buttonSave.setOnClickListener(view -> {
            String user_type = getLoggedInUserType();

            Map<String, String> userDetails = new HashMap<>();

            userDetails.put("name", binding.etName.getText().toString());
            userDetails.put("email", binding.etEmail.getText().toString());
            userDetails.put("city", binding.etCity.getText().toString());
            userDetails.put("mobile", binding.etMobile.getText().toString());
            userDetails.put("user_type",user_type);

            if ("ServiceProvider".equals(user_type)) {
                userDetails.put("service_type", binding.etServiceType.getText().toString());
            }
            String uid = getLoggedInUserId();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users")
                    .document(uid)
                    .set(userDetails)
                    .addOnCompleteListener(
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        setLoggedInUserDetails(userDetails,uid);
                                        Toast.makeText(getContext(), "Successfully Updated Details", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), "Error Updating Details", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );


        });

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
        binding.etEmail.setText(sharedPreferences.getString("email", ""));
        binding.etName.setText(sharedPreferences.getString("name", ""));
        binding.etCity.setText(sharedPreferences.getString("city", ""));
        binding.etMobile.setText(sharedPreferences.getString("mobile", ""));
        if ("ServiceProvider".equals(sharedPreferences.getString("user_type", ""))) {
            binding.etServiceType.setText(sharedPreferences.getString("service_type", ""));
            binding.tfServiceType.setVisibility(View.VISIBLE);
        } else {
            binding.tfServiceType.setVisibility(View.GONE);
        }

    }

    private String getLoggedInUserType() {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        String userType = sharedPreferences.getString("user_type", null);
        return userType;
    }

    private String getLoggedInUserId() {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", null);
        return uid;
    }

    private void setLoggedInUserDetails(Map<String, String> userDetails, String uid) {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
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