package com.example.sqlitedemo.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.databinding.ActivityMainBinding;
import com.example.sqlitedemo.pojo.CustomerModel;
import com.example.sqlitedemo.pojo.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String name;
    boolean isActive;
    DatabaseHelper helper;
    List<CustomerModel> allRecords = new ArrayList<>();
    CustomerAdapter adapter;
    LinearLayoutManager l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        name = binding.nameEdtTxt.getText().toString();
        isActive = binding.ActvCustmrSWCH.isActivated();
        binding.addBTN.setOnClickListener(view -> {
            CustomerModel customer;
            try {
                customer = new CustomerModel(1, binding.nameEdtTxt.getText().toString(), Integer.parseInt(binding.ageEdtTxt.getText().toString()), isActive = binding.ActvCustmrSWCH.isActivated());
            }catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                customer = new CustomerModel(-1, "N/A", 0, false);
            }
            helper = new DatabaseHelper(MainActivity.this);
            boolean addRecord = helper.addRecord(customer);
            Toast.makeText(this, "Customer Added " + addRecord, Toast.LENGTH_SHORT).show();
        });
        binding.viewBTN.setOnClickListener(view -> {
            helper = new DatabaseHelper(MainActivity.this);
            allRecords = helper.getAllRecords();
            adapter = new CustomerAdapter(allRecords);
            binding.Recycler.setAdapter(adapter);
        });
    }
}