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

public class Register extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myfrien1-f5a16-default-rtdb.firebaseio.com/");
    EditText name,pass,confpass,phone,email;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        final EditText fullname=findViewById(R.id.fullname);
        final EditText email=findViewById(R.id.emile);
        final EditText phone=findViewById(R.id.phone);
        final EditText password=findViewById(R.id.password);
        final EditText conpassword=findViewById(R.id.conpassword);

        final Button registerBtn=findViewById(R.id.registerBtn);
        //final TextView loginNowBtn=findViewById(R.id.loginNow);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullnameTxt=fullname.getText().toString();
                final String emailTxt=email.getText().toString();
                final String phoneTxt=phone.getText().toString();
                final String passwordTxt=password.getText().toString();
                final String conpasswordTxt=conpassword.getText().toString();

                if(fullnameTxt.isEmpty() || emailTxt.isEmpty() || phoneTxt.isEmpty() || passwordTxt.isEmpty())
                {
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!passwordTxt.equals(conpasswordTxt))
                {
                    Toast.makeText(Register.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(fullnameTxt)){
                                Toast.makeText(Register.this,"Username is already Registered",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                databaseReference.child("users").child(fullnameTxt).child("Phone").setValue(phoneTxt);
                                databaseReference.child("users").child(fullnameTxt).child("email").setValue(emailTxt);
                                databaseReference.child("users").child(fullnameTxt).child("password").setValue(passwordTxt);

                                Toast.makeText(Register.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                                finish();

                            }
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