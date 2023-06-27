package com.privilage.laundry.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.privilage.laundry.R;

public class Splash extends AppCompatActivity {
    private int waktu_loading=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(() -> {
            Intent Splash = new Intent( getApplicationContext(), Welcome.class);
            startActivity(Splash);
            finish();
        }, waktu_loading);
    }

}