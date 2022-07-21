package com.example.myfrien1.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfrien1.CoursesLVAdapter;
import com.example.myfrien1.CoursesLVAdapter1;
import com.example.myfrien1.R;
import com.example.myfrien1.databinding.FragmentSlideshowBinding;
import com.example.myfrien1.ui.home.DataModal;
import com.example.myfrien1.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    ListView coursesLV;
    ArrayList<CartPro> cartArrayList1;
    String getUser;
    int flag=0;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root=inflater.inflate(R.layout.fragment_slideshow, container, false);

        coursesLV = root.findViewById(R.id.idLVCourses);
        cartArrayList1=new ArrayList<>();
        databaseReference.child("Currentuser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                getUser = snapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Cart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(getUser)){
                    databaseReference.child("Cart").child(getUser).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                CartPro c=snapshot1.getValue(CartPro.class);
                                //Toast.makeText(SlideshowFragment.this.getActivity(),c.getProductName(), Toast.LENGTH_SHORT).show();
                                String name=c.getName();
                                String Desc=c.getDescription();
                                String price=c.getPrice();
                                String Img=c.getImg();
                                CartPro c1=new CartPro(Desc,Img,price,name);
                                cartArrayList1.add(c1);

                            }
                            CoursesLVAdapter1 adapter = new CoursesLVAdapter1(SlideshowFragment.this.getActivity(), cartArrayList1);
                            coursesLV.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       /*databaseReference.child("Cart").child(getUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //String k=""+snapshot.getValue();
                Toast.makeText(SlideshowFragment.this.getActivity(),"hi", Toast.LENGTH_SHORT).show();

                /*for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    CartPro user = snapshot.getValue(CartPro.class);
                    String name=user.getProductName();
                    Toast.makeText(SlideshowFragment.this.getActivity(),name, Toast.LENGTH_SHORT).show();

                    //System.out.println(user.email);
                }
*/


              /*  for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String k=""+snapshot1.getValue();
                    // CartPro model = snapshot1.getValue();
                    //String name = model.getProductName();
                   // String Img =  model.getImgUrl();
                    Toast.makeText(SlideshowFragment.this.getActivity(),k, Toast.LENGTH_SHORT).show();

                    Toast.makeText(SlideshowFragment.this.getActivity(),k, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(SlideshowFragment.this.getActivity(),model.getProductName(), Toast.LENGTH_SHORT).show();

                    // String Description =model.getProductDes();
                   // String Price = model.getProductPrice();
                    //CartPro d=new CartPro(Description,name,Price);

                   // dataModalArrayList1.add(d);
                }*/
               /*CoursesLVAdapter1 adapter = new CoursesLVAdapter1(SlideshowFragment.this.getActivity(), dataModalArrayList1);
                coursesLV.setAdapter(adapter);}*/






          /*  @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        */


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
