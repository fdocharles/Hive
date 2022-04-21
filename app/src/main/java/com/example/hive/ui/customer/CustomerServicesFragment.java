package com.example.hive.ui.customer;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hive.Constants;
import com.example.hive.R;
import com.example.hive.databinding.FragmentCustomerServicesBinding;

public class CustomerServicesFragment extends Fragment {
    private FragmentCustomerServicesBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_services, container, false);

        binding.constructionLayout.setOnClickListener(clickListener(Constants.SERVICE_CONSTRUCTION));
        binding.cookLayout.setOnClickListener(clickListener(Constants.SERVICE_COOK));
        binding.doctorLayout.setOnClickListener(clickListener(Constants.SERVICE_DOCTOR));
        binding.investigatorLayout.setOnClickListener(clickListener(Constants.SERVICE_INVESTIGATOR));
        binding.lawyerLayout.setOnClickListener(clickListener(Constants.SERVICE_LAWYER));
        binding.plumberLayout.setOnClickListener(clickListener(Constants.SERVICE_PLUMBER));
        binding.securityLayout.setOnClickListener(clickListener(Constants.SERVICE_SECURITY));
        binding.waiterLayout.setOnClickListener(clickListener(Constants.SERVICE_WAITER));

        return binding.getRoot();
    }

    private View.OnClickListener clickListener(String service){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToServiceProviderList(view,service);
            }
        };
    }

    private void redirectToServiceProviderList(View view,String serviceType)
    {
        Bundle bundle = new Bundle();
        bundle.putString("service_type", serviceType);
        Navigation.findNavController(view).navigate(R.id.action_customerServicesFragment_to_serviceProviderListFragment,bundle);
    }
}