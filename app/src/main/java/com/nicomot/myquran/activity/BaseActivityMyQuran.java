package com.nicomot.myquran.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.nicomot.myquran.R;
import com.nicomot.myquran.fragment.HomeFragment;
import com.nicomot.myquran.util.Hidden;

public class BaseActivityMyQuran extends AppCompatActivity {

    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_my_quran);
        frameLayout = findViewById(R.id.id_frame_layout_base);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Hidden.hiddenActionBar(getSupportActionBar());
        if(getSupportFragmentManager() != null){
            getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), new HomeFragment()).commit();
        }
    }
}