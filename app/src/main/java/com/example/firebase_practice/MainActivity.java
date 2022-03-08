package com.example.firebase_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    public TextView textView;
    public Button btnDelete;
    public Button btnLoad;
    public Task<DataSnapshot> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView1);
        btnDelete = findViewById(R.id.btnDelete);
        btnLoad = findViewById(R.id.btnLoad);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");

//        myRef.setValue("Hello, World!");



        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("message").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                tasks = task;


                if (!task.isSuccessful()) {
                    Log.e("firebase12345", "Error getting data", task.getException());
                    Toast.makeText(getApplicationContext(), "Not", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("firebase12345", String.valueOf(task.getResult().getValue()));
                    Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();


                }
            }



        });

//        textView.setText("H");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDatabase.child("message").removeValue();
                Toast.makeText(getApplicationContext(), "Deleted succesfully", Toast.LENGTH_SHORT).show();
                tasks = null;
                textView.setText("");
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tasks != null)
                    textView.setText(String.valueOf(tasks.getResult().getValue()));

            }
        });










    }
}