package com.project.thesis.util;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.project.thesis.MainActivity;
import com.project.thesis.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIMEOUT = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent screenChange = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(screenChange);
                finish();
            }
        }, SPLASH_TIMEOUT);

    }
}
