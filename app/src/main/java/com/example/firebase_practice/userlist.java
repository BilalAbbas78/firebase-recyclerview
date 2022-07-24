package com.example.firebase_practice;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userlist extends AppCompatActivity {
//    MyAdapter recyclerAdapter;

    RecyclerView recyclerView;
    DatabaseReference database;
//    com.example.realtimerecyclerview.MyAdapter myAdapter;
    MyAdapter myAdapter;
    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new com.example.firebase_practice.MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);





        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Log.i("ABCDEF", String.valueOf(list));


//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        // do whatever
//                        Toast.makeText(getApplicationContext(), "Item Pressed " +  (position+1) + " " + list.get(position).firstName, Toast.LENGTH_SHORT).show();
//
//
////                        Toast.makeText(view.findViewById(R.id.btnDelete).getContext(), "Btn Pressed " +  (position+1) + " " + list.get(position).firstName, Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override public void onLongItemClick(View view, int position) {
//                        // do whatever
////                        Toast.makeText(getApplicationContext(), "Item Long Pressed " + (position+1), Toast.LENGTH_SHORT).show();
//                    }
//                })
//        );
//



        Log.i("ABCDE", "A0");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//
        Log.i("ABCDE", "A1");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        Log.i("ABCDE", "A2");

        MenuItem menuItem = menu.findItem(R.id.action_search);
        Log.i("ABCDE", "A3");

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//if (newText.equals("")){
//    finish();
//    startActivity(getIntent());
//                myAdapter.getFilter().filter("");

//                Log.i("MyyAdapter", "listt " + list);

//myAdapter.notifyItemChanged();

//                myAdapter = new MyAdapter(userlist.this, list);
//                recyclerView.setAdapter(myAdapter);

//}



                myAdapter.getFilter().filter(newText);
myAdapter.notifyDataSetChanged();
                return false;
            }
        });
//
//
        return super.onCreateOptionsMenu(menu);


}
}
