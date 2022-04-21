package com.example.hive.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.model.Appointment;

import java.util.List;

public class ServiceProviderAppointmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Appointment> appointments;

    public interface OnItemClickListener {
        void onItemClick(Appointment appointment);
    }
    private final ServiceProviderAppointmentAdapter.OnItemClickListener listener;

    public ServiceProviderAppointmentAdapter(List<Appointment> appointments, ServiceProviderAppointmentAdapter.OnItemClickListener listener){
        super();
        this.appointments = appointments;
        this.listener = listener;
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder{

        public TextView bookedBy;
        public TextView appointmentId;

        public TextView appointmentDate;
        //public TextView message;
        // public TextView createdAt;
        public TextView status;
       // public TextView userServiceType;
       /* public TextView credit;
        public TextView marks;*/

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bookedBy = (TextView) itemView.findViewById(R.id.tv_booked_by_name);
            this.appointmentDate = (TextView) itemView.findViewById(R.id.tv_appointment_date);
            //this.appointmentId = (TextView) itemView.findViewById(R.id.tv_service_type);
            this.status = (TextView) itemView.findViewById(R.id.tv_status);

        }
        public void bind(final Appointment appointment, final ServiceProviderAppointmentAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(appointment);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This line inflate the individual grade record layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sp_appointment_record_layout,parent,false);
        ServiceProviderAppointmentAdapter.AppointmentViewHolder viewHolder = new ServiceProviderAppointmentAdapter.AppointmentViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);
        //These line set the values of individual column to the viewholder
        ((AppointmentViewHolder) holder).bookedBy.setText(appointment.getBookedByUserName());
        ((ServiceProviderAppointmentAdapter.AppointmentViewHolder) holder).appointmentDate.setText(appointment.getAppointmentDate());
        ((ServiceProviderAppointmentAdapter.AppointmentViewHolder) holder).status.setText(appointment.getStatus());

        ((ServiceProviderAppointmentAdapter.AppointmentViewHolder) holder).bind(appointment,listener);

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }




}