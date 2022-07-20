package com.example.myfrien1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerInsert extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myfrien1-f5a16-default-rtdb.firebaseio.com/");
    EditText name,id,desc,price,img;
    Button insert;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_insert);
        getSupportActionBar().hide();
        final EditText name=findViewById(R.id.name);
        final EditText desc=findViewById(R.id.desc);
        final EditText price=findViewById(R.id.price);
        final EditText id=findViewById(R.id.id);
        final EditText img=findViewById(R.id.img);

        final Button insert=findViewById(R.id.button_insert);
        //final TextView loginNowBtn=findViewById(R.id.loginNow);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname=name.getText().toString();
                final String desc1=desc.getText().toString();
                final String price1=price.getText().toString();
                final String id1=id.getText().toString();
                final String img1=img.getText().toString();

                if(fullname.isEmpty() || desc1.isEmpty() || price1.isEmpty() || id1.isEmpty() || img1.isEmpty())
                {
                    Toast.makeText(ManagerInsert.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(id1)){
                                    Toast.makeText(ManagerInsert.this, "Product is already exist", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    databaseReference.child("Products").child(id1).child("name").setValue(fullname);
                                    databaseReference.child("Products").child(id1).child("Description").setValue(desc1);
                                    databaseReference.child("Products").child(id1).child("Price").setValue(price1);
                                    databaseReference.child("Products").child(id1).child("Img").setValue(img1);


                                    Toast.makeText(ManagerInsert.this, "Product is added successfully.", Toast.LENGTH_SHORT).show();
                                    finish();

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}