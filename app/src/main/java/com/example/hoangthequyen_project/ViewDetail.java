package com.example.hoangthequyen_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hoangthequyen_project.model.Images;

public class ViewDetail extends AppCompatActivity {

    private static final String TAG = "image";
    private TextView topic, title;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        topic = findViewById(R.id.detail_topic);
        title =findViewById(R.id.detail_title);
        imageView= findViewById(R.id.detail_imageview);

        final Intent intent = getIntent();
        if(intent.getSerializableExtra("todo")!=null) {
            final Images images = (Images) intent.getSerializableExtra("image");
            title.setText(images.getTitle());
            topic.setText(images.getTopic());
        }
    }
}