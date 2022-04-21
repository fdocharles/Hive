package com.example.hive.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.model.HiveUser;

import java.util.List;

public class ServiceProviderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<HiveUser> users;

    public interface OnItemClickListener {
        void onItemClick(HiveUser user);
    }
    private final OnItemClickListener listener;

    public ServiceProviderListAdapter(List<HiveUser> users, OnItemClickListener listener){
        super();
        this.users = users;
        this.listener = listener;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView mobile;
        public TextView city;

        public TextView email;
        public TextView uid;
       /* public TextView serviceType;
        public TextView credit;
        public TextView marks;*/

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.tv_name);
            this.mobile = (TextView) itemView.findViewById(R.id.tv_mobile);
            this.city = (TextView) itemView.findViewById(R.id.tv_city);

            this.email = (TextView) itemView.findViewById(R.id.tv_email);
            this.uid = (TextView) itemView.findViewById(R.id.tv_uid);

        }
        public void bind(final HiveUser user, final OnItemClickListener listener) {
           // name.setText(item.name);
           /* Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This line inflate the individual grade record layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_record_layout,parent,false);
        UserViewHolder viewHolder = new UserViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HiveUser user = users.get(position);
        //These line set the values of individual column to the viewholder
        ((UserViewHolder) holder).name.setText(user.getName());
        ((UserViewHolder) holder).mobile.setText(user.getMobile());
        ((UserViewHolder) holder).city.setText(user.getCity());

        ((UserViewHolder) holder).uid.setText(user.getUid());
        ((UserViewHolder) holder).email.setText(user.getEmail());

        ((UserViewHolder) holder).bind(user,listener);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


}
