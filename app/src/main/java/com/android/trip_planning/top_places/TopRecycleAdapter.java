package com.android.trip_planning.top_places;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.trip_planning.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TopRecycleAdapter extends RecyclerView.Adapter<TopRecycleAdapter.MyViewHolder> {
    private Context mContext;
    private List<Top_Place_Product> topPlaceProducts = new ArrayList<>();
    public TopRecycleAdapter(Context context, List<Top_Place_Product> topPlaceProducts){
        this.mContext = context;
        this.topPlaceProducts = topPlaceProducts;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mphoto;
        public TextView mplace_name;
        public TextView mstate_name;
        public TextView mmap;
        private LinearLayout mContainer;
        public MyViewHolder (View view){
            super(view);
            mplace_name = view.findViewById(R.id.place_name);
            mphoto = view.findViewById(R.id.photo);
            mstate_name = view.findViewById(R.id.state_name);
            mmap = view.findViewById(R.id.map);
            mContainer = view.findViewById(R.id.product_container);
        }
    }
    @NonNull
    @Override
    public TopRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_place_list,parent,false);
        return new TopRecycleAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TopRecycleAdapter.MyViewHolder holder, int position) {
        final Top_Place_Product topPlaceProduct = topPlaceProducts.get(position);
        holder.mplace_name.setText(topPlaceProduct.getplace_name());
        holder.mstate_name.setText(topPlaceProduct.getstate_name());
        Glide.with(mContext).load(topPlaceProduct.getPhoto()).into(holder.mphoto);
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, com.android.trip_planning.top_places.Top_DetailedProductsActivity.class);
                intent.putExtra("place_name", topPlaceProduct.getplace_name());
                intent.putExtra("state_name", topPlaceProduct.getstate_name());
                intent.putExtra("map", topPlaceProduct.getmap());
                intent.putExtra("description", topPlaceProduct.getdescription());
                intent.putExtra("photo", topPlaceProduct.getPhoto());
                mContext.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return topPlaceProducts.size();
    }
}