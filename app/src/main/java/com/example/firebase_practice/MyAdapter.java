package com.example.firebase_practice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    Context context;

    ArrayList<User> list;
    ArrayList<User> lst;
    ArrayList<User> moviesListAll = new ArrayList<>();


    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
        lst = list;
//        moviesListAll = new ArrayList<>();

        moviesListAll.addAll(this.list);
//        Log.i("MyyAdapter", "movies list all " + moviesListAll);
//        Log.i("MyyAdapter", "movies list " + list);


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.firstName.setText(user.getFirstName());
        holder.lastName.setText(user.getLastName());
        holder.age.setText(user.getAge());

//        holder.itemView.findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                    Toast.makeText(view.getContext(), "Item Pres ", Toast.LENGTH_SHORT).show();
//
//                Log.i("MyyAdapter", "Btn Delete");
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView firstName, lastName, age;
        Button btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tvfirstName);
            lastName = itemView.findViewById(R.id.tvlastName);
            age = itemView.findViewById(R.id.tvage);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            btnDelete.setOnClickListener((View.OnClickListener) this);
            itemView.setOnClickListener(this);






        }


        @Override
        public void onClick(View view) {
            if (view.getId() == btnDelete.getId()) {

                Log.i("MyyAdapter", "Btn Delete");
                Toast.makeText(view.getContext(), "ITEM PRESSED = " +  String.valueOf(getAdapterPosition()) + " " + firstName.getText(), Toast.LENGTH_SHORT).show();
            } else {
                Log.i("MyyAdapter", "Btn Delete");
                Toast.makeText(view.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }






    @Override
    public Filter getFilter() {

        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
//            moviesListAll.clear();
//            moviesListAll.addAll(list);



            List<User> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(moviesListAll);
            } else {
//                moviesListAll.clear();

//                moviesListAll.addAll(list);
                Log.i("MyyAdapter", "movies list " + list);
                Log.i("MyyAdapter", "movies All " + moviesListAll);

                moviesListAll = lst;

                for (User movie: lst) {
//                    Log.i("MyyAdapter", "movies " + movie.firstName);

                    if (movie.firstName.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(movie);
                    }
                }
                Log.i("MyyAdapter", "Filtered " + filteredList);
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            list.clear();
//            list.addAll((Collection<? extends User>) filterResults.values);
//
//            moviesListAll.clear();
//            moviesListAll.addAll((Collection<? extends User>) filterResults.values);

            list = (ArrayList<User>) filterResults.values;

            notifyDataSetChanged();
        }
    };

}
