package com.android.trip_planning;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static maes.tech.intentanim.CustomIntent.customType;

public class Help extends AppCompatActivity {
    TextView appinfo, helpcenter, aboutus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        appinfo=findViewById(R.id.appinfo);
        aboutus=findViewById(R.id.aboutus);
        appinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Help.this,Appinfo.class);
                startActivity(intent);
                customType(Help.this,"left-to-right");

            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Help.this,Aboutus.class);
                startActivity(intent);
                customType(Help.this,"left-to-right");

            }
        });
    }
}