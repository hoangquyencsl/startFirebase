package com.example.hoangthequyen_project.Fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hoangthequyen_project.R;
import com.example.hoangthequyen_project.ViewDetail;
import com.example.hoangthequyen_project.model.Images;

import java.util.ArrayList;

public class ImgAdapter extends RecyclerView.Adapter <ImgAdapter.MyViewHolder>{

    private Context mContext;
    private ArrayList<Images> imagesList =new ArrayList<>();

    public ImgAdapter(Context context, ArrayList<Images> itemList) {
        this.mContext = context ;
        this.imagesList = itemList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.img_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(mContext).load(imagesList.get(position).getUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, ViewDetail.class);
                intent.putExtra("image",imagesList.get(position));
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.imagesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imageView2;
        TextView dTitle, dTopic;

        public MyViewHolder(View view) {
            super(view);
            imageView= view.findViewById(R.id.item_imageview);
            imageView2 =view.findViewById(R.id.detail_imageview);
            dTitle = view.findViewById(R.id.detail_title);
            dTopic = view.findViewById(R.id.detail_topic);
        }
    }
}
