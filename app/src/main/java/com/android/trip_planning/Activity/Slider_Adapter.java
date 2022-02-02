package com.android.trip_planning.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.trip_planning.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class Slider_Adapter extends SliderViewAdapter<Slider_Adapter.ViewHolder> {

    int[] images;

    public Slider_Adapter(int[] images) {
        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.my_imageView.setImageResource(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView my_imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            my_imageView = itemView.findViewById(R.id.image_view_s);
        }
    }
}