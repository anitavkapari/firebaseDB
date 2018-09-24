package com.example.dell.firebasedb.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dell.firebasedb.R;
import com.example.dell.firebasedb.models.Employee;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {
    EditText edtName, edtAddress, edtPhoneNumber, edtSalary, edtDesignation;
    ProgressBar progress;

    Button btnAdd;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtAddress = findViewById(R.id.edtAddress);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtSalary = findViewById(R.id.edtSalary);
        edtDesignation = findViewById(R.id.edtDesignation);
        progress = findViewById(R.id.progress);
        btnAdd = findViewById(R.id.btnAdd);

     mDatabaseReference = FirebaseDatabase.getInstance().getReference("employees");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToDatabase();

            }
        });
    }
    private void addToDatabase() {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phoneNumber = edtPhoneNumber.getText().toString().trim();
        String salString = edtSalary.getText().toString().trim();
        String designation = edtDesignation.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phoneNumber)
                || TextUtils.isEmpty(salString) || TextUtils.isEmpty(designation)) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        long salary = Long.parseLong(salString);

        Employee employee = new Employee(name, address, phoneNumber, salary, designation);

       String key = mDatabaseReference.push().getKey();

       employee.setId(key);

        progress.setVisibility(View.VISIBLE);
        mDatabaseReference.child(key).setValue(employee).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progress.setVisibility(View.GONE);
            }
        });


    }

}
