package com.nicomot.myquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.nicomot.myquran.activity.BaseActivityMyQuran;
import com.nicomot.myquran.util.Hidden;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Hidden.hiddenActionBar(getSupportActionBar());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inten = new Intent(SplashScreenActivity.this, BaseActivityMyQuran.class);
                startActivity(inten);
            }
        },2000);
    }
}