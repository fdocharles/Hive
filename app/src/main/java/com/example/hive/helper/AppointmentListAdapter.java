package com.example.hive.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.model.Appointment;
import com.example.hive.model.HiveUser;

import java.util.List;

public class AppointmentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Appointment> appointments;

    public interface OnItemClickListener {
        void onItemClick(Appointment appointment);
    }
    private final AppointmentListAdapter.OnItemClickListener listener;

    public AppointmentListAdapter(List<Appointment> appointments, AppointmentListAdapter.OnItemClickListener listener){
        super();
        this.appointments = appointments;
        this.listener = listener;
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder{

        //public TextView bookedBy;
        public TextView username;
        public TextView appointmentDate;
        //public TextView message;
       // public TextView createdAt;
        public TextView status;
        public TextView userServiceType;
       /* public TextView credit;
        public TextView marks;*/

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.username = (TextView) itemView.findViewById(R.id.tv_name);
            this.appointmentDate = (TextView) itemView.findViewById(R.id.tv_appointment_date);
            this.userServiceType = (TextView) itemView.findViewById(R.id.tv_service_type);
            this.status = (TextView) itemView.findViewById(R.id.tv_status);

        }
        public void bind(final Appointment appointment, final AppointmentListAdapter.OnItemClickListener listener) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_record_layout,parent,false);
        AppointmentListAdapter.AppointmentViewHolder viewHolder = new AppointmentListAdapter.AppointmentViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);
        //These line set the values of individual column to the viewholder
        ((AppointmentViewHolder) holder).username.setText(appointment.getUsername());
        ((AppointmentViewHolder) holder).appointmentDate.setText(appointment.getAppointmentDate());
        ((AppointmentViewHolder) holder).status.setText(appointment.getStatus());
        ((AppointmentListAdapter.AppointmentViewHolder) holder).userServiceType.setText(appointment.getUserServiceType());
       /* ((AppointmentListAdapter.AppointmentViewHolder) holder).uid.setText(appointments.getUid());
        ((AppointmentListAdapter.AppointmentViewHolder) holder).email.setText(appointments.getEmail());
*/
        ((AppointmentListAdapter.AppointmentViewHolder) holder).bind(appointment,listener);

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }




}
