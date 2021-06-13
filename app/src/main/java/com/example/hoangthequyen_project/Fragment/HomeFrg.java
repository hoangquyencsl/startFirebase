package com.example.hoangthequyen_project.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hoangthequyen_project.AddImgActivity;
import com.example.hoangthequyen_project.R;
import com.example.hoangthequyen_project.model.Images;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class HomeFrg extends Fragment {


    private RecyclerView mRecyclerView;
    private ImgAdapter imgAdapter;
    private ArrayList<Images> imagesList;
    private ImageView imageView;

    public HomeFrg(){
    }

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home_frg, container, false);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(HomeFrg.this.getContext()));

        imagesList = new ArrayList<>();
        imgAdapter = new ImgAdapter(HomeFrg.this.getContext(),imagesList);

        mRecyclerView.setAdapter(imgAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Images images = dataSnapshot.getValue(Images.class);
                    imagesList.add(images);
                }
                imgAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    return rootView;

    }
}