package com.android.trip_planning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import static maes.tech.intentanim.CustomIntent.customType;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ImageView back= findViewById(R.id.stateback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Aboutus.this, Help.class);
                startActivity(intent);
                customType(Aboutus.this,"right-to-left");
            }
        });
    }
}