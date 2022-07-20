package com.example.myfrien1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myfrien1.ui.slideshow.CartPro;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CoursesLVAdapter1 extends ArrayAdapter<CartPro> {
    ArrayList<CartPro> cartArrayList1;

    public CoursesLVAdapter1(@NonNull Context context, ArrayList<CartPro> cartArrayList) {
            super(context, 0, cartArrayList);
         cartArrayList1= cartArrayList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View listitemView = convertView;
            if (listitemView == null) {
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.image_lv_item1, parent, false);
            }


            CartPro cart = getItem(position);
            TextView nameTV = listitemView.findViewById(R.id.prName);
            ImageView courseIV = listitemView.findViewById(R.id.prImg);
            TextView Description=listitemView.findViewById(R.id.prDesc);
            TextView Price=listitemView.findViewById(R.id.prPrice);
            TextView total=listitemView.findViewById(R.id.total);
            Button cal=listitemView.findViewById(R.id.cal);
            EditText quan=listitemView.findViewById(R.id.quan);
            cal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int num1=Integer.parseInt(quan.getText().toString());
                    int num2=Integer.parseInt(cart.getPrice());
                    int sum=num1*num2;
                    total.setText("Total : "+sum+"");
                    cartArrayList1.remove(cart);
                }
            });


            nameTV.setText(cart.getName());
            Description.setText(cart.getDescription());
            Price.setText(cart.getPrice());



            Picasso.get().load(cart.getImg()).into(courseIV);



            return listitemView;
        }
}
