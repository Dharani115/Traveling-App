package com.android.trip_planning.top_places;
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

import static maes.tech.intentanim.CustomIntent.customType;

public class Top_DetailedProductsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private ImageView mphoto;
    private ImageView mmap,mapres,maphotel;
    private TextView mplace_name,mstate_name,mdescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_detail_activity);
        mToolbar = findViewById(R.id.toolbar);
        mphoto = findViewById(R.id.image_view);
        mmap = findViewById(R.id.map);
        mapres = findViewById(R.id.mapres);
        maphotel = findViewById(R.id.maphotel);
        mdescription = findViewById(R.id.description);
        mplace_name = findViewById(R.id.place_name);
        mstate_name = findViewById(R.id.state_name);
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
            mstate_name.setText(state_name);
            mdescription.setText(description);
            final String url = "http://maps.google.com/maps?daddr="+map;
            mmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.setPackage("com.google.android.apps.maps");
                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivity(intent);
                        customType(Top_DetailedProductsActivity.this,"left-to-right");

                    }
                }
            });
            Glide.with(Top_DetailedProductsActivity.this).load(photo).into(mphoto);
            final String uri = String.format(Locale.ENGLISH, "geo:%S?z=15&q=restaurants near",map);
            mapres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Top_DetailedProductsActivity.this,"Showing near by restaurants in Google map",Toast.LENGTH_LONG).show();
                    Intent mapIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                    customType(Top_DetailedProductsActivity.this,"left-to-right");

                }
            });
            final String urihotel = String.format(Locale.ENGLISH, "geo:%S?z=15&q=Hotels",map);
            maphotel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Top_DetailedProductsActivity.this,"Showing near by hotels in Google map",Toast.LENGTH_LONG).show();
                    Intent mapIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(urihotel));
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                    customType(Top_DetailedProductsActivity.this,"left-to-right");

                }
            });
        }
    }
}