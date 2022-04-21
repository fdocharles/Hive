package com.example.hive;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class CustomerServicesFragment extends Fragment {


    LinearLayout construction,cook,doctor,investigator,lawyer,plumber,security,waiter;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customer_services, container, false);

        construction = (LinearLayout) view.findViewById(R.id.construction_layout);
        cook = (LinearLayout) view.findViewById(R.id.cook_layout);
        doctor = (LinearLayout) view.findViewById(R.id.doctor_layout);
        investigator = (LinearLayout) view.findViewById(R.id.investigator_layout);
        lawyer = (LinearLayout) view.findViewById(R.id.lawyer_layout);
        plumber = (LinearLayout) view.findViewById(R.id.plumber_layout);
        security = (LinearLayout) view.findViewById(R.id.security_layout);
        waiter = (LinearLayout) view.findViewById(R.id.waiter_layout);



        construction.setOnClickListener(clickListener(Constants.SERVICE_CONSTRUCTION));
        construction.setOnClickListener(clickListener(Constants.SERVICE_COOK));
        construction.setOnClickListener(clickListener(Constants.SERVICE_DOCTOR));
        construction.setOnClickListener(clickListener(Constants.SERVICE_INVESTIGATOR));
        construction.setOnClickListener(clickListener(Constants.SERVICE_LAWYER));
        construction.setOnClickListener(clickListener(Constants.SERVICE_PLUMBER));
        construction.setOnClickListener(clickListener(Constants.SERVICE_SECURITY));
        construction.setOnClickListener(clickListener(Constants.SERVICE_WAITER));

        return view;
    }

    private View.OnClickListener clickListener(String service){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToServiceProviderList(service);
            }
        };
    }

    private void redirectToServiceProviderList(String data)
    {
        Bundle bundle = new Bundle();
        if(!data.isEmpty())
        {

            bundle.putString(Constants.KEY_SELECTED_SERVICE, data);
        }

        Navigation.findNavController(view).navigate(R.id.action_customerServicesFragment_to_serviceProviderListFragment, bundle);
    }
}