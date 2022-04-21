package com.example.hive;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hive.databinding.FragmentAppointmentListBinding;
import com.example.hive.helper.AppointmentListAdapter;
import com.example.hive.model.Appointment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class AppointmentListFragment extends Fragment {


    private static String TAG = "com.example.hive.AppointmentListFragment";
    private FragmentAppointmentListBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment_list, container, false);


        String uid = getLoggedInUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a reference to the cities collection
        CollectionReference usersRef = db.collection("appointments");

        // Create a query against the collection.
        Query serviceTypeQuery = usersRef.whereEqualTo("booked_by", uid);
        serviceTypeQuery.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Appointment> appointments = new ArrayList<>();
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Appointment appointment = new Appointment();
                                appointment.setUserServiceType((String) document.get("user_service_type"));
                                appointment.setUsername((String) document.get("user_name"));
                                appointment.setAppointmentDate((String) document.get("appointment_date"));
                                appointment.setStatus((String) document.get("status"));
                                appointment.setBookedBy((String) document.get("booked_by"));
                                appointment.setMessage((String) document.get("message"));
                                appointment.setCreatedAt( (String) document.get("created_at"));
                                appointment.setBookedByUserName( (String) document.get("booked_by_user_name"));

                                appointments.add(appointment);
                            }
                            if (appointments.size() > 0) {
                                bindAdapter(appointments);
                            } else {
                                Toast.makeText(getContext(), "No Records Found", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        return binding.getRoot();
    }

    private void bindAdapter(List<Appointment> appointments) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        AppointmentListAdapter listAdapter = new AppointmentListAdapter(appointments, (appointment) -> {

        });
        binding.recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    private String getLoggedInUser() {
        sharedPreferences = getContext().getSharedPreferences("hive", Context.MODE_PRIVATE);
        String uid = sharedPreferences.getString("uid", null);
        return uid;
    }
}