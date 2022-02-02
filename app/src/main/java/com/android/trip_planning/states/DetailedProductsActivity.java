package com.android.trip_planning.states;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.trip_planning.R;
import com.bumptech.glide.Glide;

import java.util.Locale;
public class DetailedProductsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private ImageView mphoto;
    private ImageView mmap,mapres,maphotel;
    private TextView mplace_name,mdescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_products);
        mToolbar = findViewById(R.id.toolbar);
        mphoto = findViewById(R.id.image_view);
        mmap = findViewById(R.id.map);
        mapres = findViewById(R.id.mapres);
        maphotel = findViewById(R.id.maphotel);
        mdescription = findViewById(R.id.description);
        mplace_name = findViewById(R.id.place_name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back));
        Intent intent = getIntent();
        String place_name = intent.getStringExtra("place_name");
        String state_name = intent.getStringExtra("state_name");
        final String map = intent.getStringExtra("map");
        String description = intent.getStringExtra("description");
        String photo = intent.getStringExtra("photo");
        if (intent !=null){
            mActionBar.setTitle(place_name);
            mplace_name.setText(place_name);
            mdescription.setText(description);
            final String url = "http://maps.google.com/maps?daddr="+map;
            mmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.setPackage("com.google.android.apps.maps");
                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivity(intent);
                    }
                }
            });
            Glide.with(DetailedProductsActivity.this).load(photo).into(mphoto);
            final String urires = String.format(Locale.ENGLISH, "geo:%S?z=15&q=restaurants",map);
            mapres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DetailedProductsActivity.this,"Showing near by restuarents in Google map",Toast.LENGTH_LONG).show();
                    Intent mapIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(urires));
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });
            final String urihotel = String.format(Locale.ENGLISH, "geo:%S?z=15&q=Hotels",map);
            maphotel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DetailedProductsActivity.this,"Showing near by hotels in Google map",Toast.LENGTH_LONG).show();
                    Intent mapIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(urihotel));
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });
        }
    }
}