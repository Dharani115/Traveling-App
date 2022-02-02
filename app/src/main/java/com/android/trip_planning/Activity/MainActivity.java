package com.android.trip_planning.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.trip_planning.R;

public class MainActivity extends AppCompatActivity {

    private  static  int SPLASH_SCREEN =2000;
    final String PREFS_NAME = "MyPrefsFile";
    final String PREFS_FIRST_RUN = "first_run";
    ImageView imageView;
    TextView textView1;
    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.imageView);
        textView1 = findViewById(R.id.textView);


        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        imageView.setAnimation(top);
        textView1.setAnimation(bottom);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, NavigatonDrawer.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}


