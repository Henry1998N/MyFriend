package com.example.myfrien1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManagerHome extends AppCompatActivity {
    Button Delete;
    EditText txt;
    TextView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manger_home);
        getSupportActionBar().hide();
        Delete = (Button) findViewById(R.id.delete);
        txt = (EditText) findViewById(R.id.delete1);
        add=(TextView)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                startActivity(new Intent(ManagerHome.this,ManagerSignUp.class));
            }
        });
        DatabaseReference rootDataseRef = FirebaseDatabase.getInstance().getReference().child("Products");
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt1 = txt.getText().toString();
                rootDataseRef.child(txt1).setValue(null);
                Toast.makeText(ManagerHome.this, "item deleted successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }
        public void insert(View view) {
            startActivity(new Intent(ManagerHome.this,ManagerInsert.class));

        }
}