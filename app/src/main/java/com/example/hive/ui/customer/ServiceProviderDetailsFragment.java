package com.example.hive.ui.customer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.hive.R;
import com.example.hive.databinding.FragmentServiceProviderDetailsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServiceProviderDetailsFragment extends Fragment {

    private static String TAG = "com.example.hive.ServiceProviderDetailsFragment";
    private FragmentServiceProviderDetailsBinding binding;
    private NavController navController;
    private SharedPreferences sharedPreferences;
    private String serviceProviderUserId = "";
    private String serviceProviderUserName = "";
    private String serviceProviderUserServiceType = "";

    private  DatePickerDialog datePickerDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_provider_details, container, false);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.customer_fragments_view_container);
        navController = navHostFragment.getNavController();

        serviceProviderUserId = (String) getArguments().get("uid");
        serviceProviderUserName = (String) getArguments().get("name");
        serviceProviderUserServiceType =(String) getArguments().get("service_type");
        binding.tvName.setText((String) getArguments().get("name"));
        binding.tvEmail.setText((String) getArguments().get("email"));
        binding.tvCity.setText((String) getArguments().get("city"));
        binding.tvServiceType.setText((String) getArguments().get("service_type"));

        binding.tvMobile.setText((String) getArguments().get("mobile"));


        binding.btnAppointmentDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        binding.appointmentDate.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });



        binding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable appointmentDate = binding.appointmentDate.getText();
                String message = binding.message.getText().toString();
                if(appointmentDate != null || !"".equals(appointmentDate.toString())) {
                    bookAppointment(appointmentDate.toString(), message);
                }
            }
        });


        return binding.getRoot();
    }

    private void bookAppointment(String appointmentDate, String message) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Map<String, String> appointmentData = new HashMap<>();
        appointmentData.put("appointment_date",appointmentDate);
        appointmentData.put("message",message);
        appointmentData.put("status","pending");
        appointmentData.put("user_id",serviceProviderUserId);
        appointmentData.put("booked_by",getLoggedInUser());
        appointmentData.put("created_at",formatter.format(date));
        appointmentData.put("user_name",serviceProviderUserName);
        appointmentData.put("user_service_type",serviceProviderUserServiceType);
        appointmentData.put("booked_by_user_name",getLoggedInUsername());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("appointments").document()
                .set(appointmentData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Appointment created");
                        Toast.makeText(getContext(), "Appointment Created Successful", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("service_type", serviceProviderUserServiceType);
                        navController.navigate(R.id.action_serviceProviderDetailsFragment_to_serviceProviderListFragment,bundle);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error creating appointment", e);
                        Toast.makeText(getContext(), "Error Creating Appointment", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private String getLoggedInUser() {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", null);
        return uid;
    }
    private String getLoggedInUsername() {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("name", null);
        return uid;
    }


}