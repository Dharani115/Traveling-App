package com.android.trip_planning.Activity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.trip_planning.R;

public class TopActivity extends RecyclerView.Adapter<TopActivity.ViewHolder> {

    int[] images;

    public TopActivity(int[] images){
        this.images=images;
    }
    @NonNull
    @Override
    public TopActivity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopActivity.ViewHolder holder, int position) {
        //seting image on imageview
        holder.imageView.setBackgroundResource(images[position]);
    }

    @Override
    public int getItemCount() {
        //return array length
        return images.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.image_view);
        }
    }
}