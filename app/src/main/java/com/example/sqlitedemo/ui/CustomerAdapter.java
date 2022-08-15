package com.example.sqlitedemo.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.pojo.CustomerModel;
import com.example.sqlitedemo.pojo.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {


    public CustomerAdapter(List<CustomerModel> customerList) {
        this.customerList = customerList;
    }
    List<CustomerModel> customerList;
    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_item, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.nameTV.setText(customerList.get(position).getName());
        holder.ageTV.setText(String.valueOf(customerList.get(position).getAge()));
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class CustomerViewHolder  extends RecyclerView.ViewHolder{
        TextView nameTV, ageTV, activeTV;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nametxtTV);
            ageTV = itemView.findViewById(R.id.agetxtTV);
        }
    }
}
