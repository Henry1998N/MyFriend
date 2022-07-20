package com.example.myfrien1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfrien1.CoursesLVAdapter;
import com.example.myfrien1.MainActivity;
import com.example.myfrien1.R;
import com.example.myfrien1.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ListView coursesLV;
    ArrayList<Games> Gamesarr;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myfrien1-f5a16-default-rtdb.firebaseio.com/");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root=inflater.inflate(R.layout.fragment_home, container, false);
        coursesLV = root.findViewById(R.id.idLVCourses);

        Gamesarr = new ArrayList<>();
        databaseReference.child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Games model = snapshot1.getValue(Games.class);
                    String name = model.getName();
                    String imgUrl =  model.getImg();
                    String Description =model.getDescription();
                    String Price = model.getPrice();
                    Games d=new Games(Description,imgUrl,Price,name);

                    Gamesarr.add(d);
                }
                CoursesLVAdapter adapter = new CoursesLVAdapter(HomeFragment.this.getActivity(), Gamesarr);
                coursesLV.setAdapter(adapter);


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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