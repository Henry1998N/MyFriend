package com.example.myfrien1.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfrien1.R;
import com.example.myfrien1.databinding.FragmentGalleryBinding;
import com.example.myfrien1.login;
import com.example.myfrien1.ui.slideshow.SlideshowFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GalleryFragment extends Fragment {
    Button btn;
    String cur;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root=inflater.inflate(R.layout.fragment_gallery, container, false);
        TextView user,email,phone;
        Button btn;
        user=(TextView) root.findViewById(R.id.user);
        email=(TextView) root.findViewById(R.id.email);
        phone=(TextView) root.findViewById(R.id.phone);
        btn=(Button)root.findViewById(R.id.logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GalleryFragment.this.getActivity(),login.class));
            }
        });
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myfrien1-f5a16-default-rtdb.firebaseio.com/");
        databaseReference.child("Currentuser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final String getPassword = snapshot.getValue(String.class);
                    cur=getPassword;
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(getPassword)) {
                            final String getemail = snapshot.child(getPassword).child("email").getValue(String.class);

                            final String getphone = snapshot.child(getPassword).child("Phone").getValue(String.class);
                            user.setText("User :"+getPassword.toString());
                            email.setText("Email :"+getemail.toString());
                            phone.setText("Phone :"+getphone.toString());
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

         btn=root.findViewById(R.id.checkout);
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 databaseReference.child("Cart").child(cur).setValue(null);
                 Toast.makeText(GalleryFragment.this.getActivity(), "Cart Items for this User Deleted", Toast.LENGTH_SHORT).show();
                 Fragment fragment=new SlideshowFragment();
                 FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                 FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                 fragmentTransaction.replace(R.id.nav_view,fragment);
                 fragmentTransaction.addToBackStack(null);
                 fragmentTransaction.commit();
             }
         });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}