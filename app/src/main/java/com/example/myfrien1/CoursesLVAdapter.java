package com.example.myfrien1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myfrien1.ui.home.DataModal;
import com.example.myfrien1.ui.home.Games;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoursesLVAdapter extends ArrayAdapter<Games> {

     String getPassword;

        // constructor for our list view adapter.
    public CoursesLVAdapter(@NonNull Context context, ArrayList<Games> GamesArrayList) {
            super(context, 0, GamesArrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // below line is use to inflate the
            // layout for our item of list view.

            View listitemView = convertView;
            if (listitemView == null) {
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.image_lv_item, parent, false);
            }

            // after inflating an item of listview item
            // we are getting data from array list inside
            // our modal class.
            Games game = getItem(position);
            // initializing our UI components of list view item.
            TextView nameTV = listitemView.findViewById(R.id.prName);
            ImageView courseIV = listitemView.findViewById(R.id.prImg);
            TextView Description=listitemView.findViewById(R.id.prDesc);
            TextView Price=listitemView.findViewById(R.id.prPrice);
            Button addtocart=listitemView.findViewById(R.id.addtocart);
            addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(CoursesLVAdapter.this.getContext(), (game.getName()), Toast.LENGTH_SHORT).show();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("");
                    databaseReference.child("Currentuser").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            getPassword = snapshot.getValue(String.class);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Map<String,String> map=new HashMap<>();
                            map.put("name",game.getName());
                            map.put("Img",game.getImg());
                            map.put("Description",game.getDescription());
                            map.put("Price",game.getPrice());


                            databaseReference.child("Cart").child(getPassword).push().setValue(map);




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });

            // after initializing our items we are
            // setting data to our view.
            // below line is use to set data to our text view.
            nameTV.setText(game.getName());
            Description.setText(game.getDescription());
            Price.setText(game.getPrice());

            // in below line we are using Picasso to
            // load image from URL in our Image VIew.
            Picasso.get().load(game.getImg()).into(courseIV);

            // below line is use to add item click listener
            // for our item of list view.

            return listitemView;
        }
}
