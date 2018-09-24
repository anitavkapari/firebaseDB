package com.example.dell.firebasedb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dell.firebasedb.R;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="notification" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

    }

    public void addEmployee(View view) {
        Intent intent=new Intent(this,AddActivity.class);
        startActivity(intent);
    }

    public void viewEmployee(View view) {
        Intent intent=new Intent(this,ViewActivity.class);
        startActivity(intent);
    }

    public void deleteEmployee(View view) {
    }

    public void updateEmployee(View view) {
    }
}
