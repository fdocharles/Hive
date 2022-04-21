package com.example.hive;

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

import com.example.hive.R;
import com.example.hive.databinding.FragmentServiceProviderListBinding;
import com.example.hive.helper.ServiceProviderListAdapter;
import com.example.hive.model.HiveUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ServiceProviderListFragment extends Fragment {

    private static String TAG = "com.example.hive.ServiceProviderListFragment";
    private FragmentServiceProviderListBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_provider_list, container, false);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.customer_fragments_view_container);
        navController = navHostFragment.getNavController();


        if(getArguments().get("service_type") != null){

            String serviceType =(String) getArguments().get("service_type");
            binding.tvServiceType.setText(serviceType);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            // Create a reference to the cities collection
            CollectionReference usersRef = db.collection("users");

            // Create a query against the collection.
            Query serviceTypeQuery = usersRef.whereEqualTo("service_type", serviceType);
            serviceTypeQuery.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    List<HiveUser> users = new ArrayList<>();
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            HiveUser user = new HiveUser();
                            user.setServiceType((String)document.get("service_type"));
                            user.setName((String)document.get("name"));
                            user.setCity((String)document.get("city"));
                            user.setMobile((String)document.get("mobile"));
                            user.setUid(document.getId());
                            user.setEmail((String)document.get("email"));

                           // user.setHourlyRate("$"+(String)document.get("hourly_rate"));
                            users.add(user);
                        }
                        if(users.size() > 0){
                            bindAdapter(users);
                        }
                        else{
                            Toast.makeText(getContext(),"No Records Found",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });


        }
       return binding.getRoot();
    }

    private void bindAdapter(List<HiveUser> users) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        ServiceProviderListAdapter listAdapter = new ServiceProviderListAdapter(users,(user)->{
            Bundle bundle = new Bundle();
            bundle.putString("service_type", user.getServiceType());
            bundle.putString("uid", user.getUid());
            bundle.putString("email", user.getEmail());
            bundle.putString("name", user.getName());
            bundle.putString("mobile", user.getMobile());
            bundle.putString("city", user.getCity());
           // bundle.putString("hourly_rate", user.getHourlyRate());
            navController.navigate(R.id.action_serviceProviderListFragment_to_serviceProviderDetailsFragment,bundle);
        }  );
        binding.recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }
}