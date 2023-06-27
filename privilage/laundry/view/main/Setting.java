package com.privilage.laundry.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.privilage.laundry.R;

public class Setting extends AppCompatActivity {

    Button btnAcc;
    Button btnAbout;
    Button btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        setInitLayout();
    }

    private void setInitLayout() {
        btnAcc = findViewById(R.id.btnAcc);
        btnAbout =findViewById(R.id.btnAbout);
        btnOut =findViewById(R.id.btnOut);


        btnAcc.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this, Akun.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this, About.class);
            startActivity(intent);
        });

        btnOut.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this, Splash.class);
            startActivity(intent);
        });
}}
