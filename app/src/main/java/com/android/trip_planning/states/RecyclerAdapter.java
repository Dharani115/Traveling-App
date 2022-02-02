package com.android.trip_planning.states;
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
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<Product> products = new ArrayList<>();
    public RecyclerAdapter (Context context,List<Product> products){
        this.mContext = context;
        this.products = products;
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.products_list_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.mplace_name.setText(product.getplace_name());
        Glide.with(mContext).load(product.getPhoto()).into(holder.mphoto);
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetailedProductsActivity.class);
                intent.putExtra("place_name",product.getplace_name());
                intent.putExtra("state_name",product.getstate_name());
                intent.putExtra("map",product.getmap());
                intent.putExtra("description",product.getdescription());
                intent.putExtra("photo",product.getPhoto());
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return products.size();
    }
}