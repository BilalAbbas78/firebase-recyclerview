package com.example.firebase_practice;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button recylerviewbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ABCDEF", "Create");


        recylerviewbtn = findViewById(R.id.recyclerviewbtn);
        recylerviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("ABCDEF", "Btn click");

//                Intent i = new Intent(getApplicationContext(),userlist.class);
                Log.i("ABCDEF", "Btn 1");

//                startActivity(i);

                startActivity(new Intent(MainActivity.this, userlist.class));
                Log.i("ABCDEF", "Btn 2");
                finish();
                Log.i("ABCDEF", "Btn 3");


            }
        });

    }
}

