package com.example.dell.firebasedb.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dell.firebasedb.R;
import com.example.dell.firebasedb.adapter.EmployeeClickListener;
import com.example.dell.firebasedb.adapter.EmployeeListAdapter;
import com.example.dell.firebasedb.models.Employee;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity implements EmployeeClickListener {
    RecyclerView rvEmployees;
    EmployeeListAdapter employeeListAdapter;
    ProgressBar progress;

    List<Employee> employees = new ArrayList<>();

    DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        rvEmployees = findViewById(R.id.rvEmployees);
        progress = findViewById(R.id.progress);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("employees");

        employeeListAdapter = new EmployeeListAdapter(employees, this);

        rvEmployees.setAdapter(employeeListAdapter);
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                progress.setVisibility(View.GONE);
                Employee employee = dataSnapshot.getValue(Employee.class);
                employees.add(employee);
                employeeListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Employee employee = dataSnapshot.getValue(Employee.class);
                employees.remove(employee);
                employeeListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Inflate the layout for this fragment
    }


    @Override
    public void onDeleteClick(int position) {

             Employee employee = employeeListAdapter.getItemAtPosition(position);

            String employeeId = employee.getId();
        progress.setVisibility(View.VISIBLE);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("employees");



    }

    @Override
    public void onItemClick(int position) {

    }
}

