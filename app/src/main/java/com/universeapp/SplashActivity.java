package com.universeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {
   TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        title = findViewById(R.id.title);
        getSupportActionBar().hide();
        YoYo.with(Techniques.BounceInDown)
                .duration(1000)
                .repeat(0)
                .playOn(title);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
            }
        },3*1000);

    }
}