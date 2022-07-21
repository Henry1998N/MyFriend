package com.example.myfrien1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    TextView Signup;
    Button Login;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        final EditText user = findViewById(R.id.Username);
        final EditText password = findViewById(R.id.Password);

        Signup = (TextView) findViewById(R.id.signup);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, Register.class));
            }
        });
        Login = (Button) findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneTxt = user.getText().toString();
                final String passwordTxt = password.getText().toString();

                if (phoneTxt.isEmpty() || passwordTxt.isEmpty()) {
                    Toast.makeText(login.this, "please enter your mobile or password", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phoneTxt)) {
                                final String getPassword = snapshot.child(phoneTxt).child("password").getValue(String.class);
                                final String getemail = snapshot.child(phoneTxt).child("email").getValue(String.class);
                                final String getphone = snapshot.child(phoneTxt).child("Phone").getValue(String.class);

                                if (getPassword.equals(passwordTxt)) {
                                    Toast.makeText(login.this, "Successfully Logged in ", Toast.LENGTH_SHORT).show();
                                    Intent inte = (new Intent(login.this, SideBar.class));
                                    databaseReference.child("Currentuser").setValue(phoneTxt);
                                    startActivity(inte);
                                    finish();
                                } else {
                                    Toast.makeText(login.this, "Wrong Password ", Toast.LENGTH_SHORT).show();
                                }
                            } else if (true) {
                                databaseReference.child("Managers").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.hasChild(phoneTxt)) {
                                            final String getPassword = snapshot.child(phoneTxt).child("password").getValue(String.class);

                                            if (getPassword.equals(passwordTxt)) {
                                                Toast.makeText(login.this, "Successfully Logged in ", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(login.this, ManagerHome.class));
                                                finish();
                                            } else {
                                                Toast.makeText(login.this, "Wrong Password ", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            } else {
                                Toast.makeText(login.this, "Wrong Password1 ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });
       /* registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn2.this,Register.class));
            }
        });

    }
        });*/
    }
}
