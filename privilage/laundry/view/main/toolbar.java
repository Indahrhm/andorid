package com.privilage.laundry.view.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.privilage.laundry.R;


public class toolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.toolbar);

        ImageButton btnAkun = findViewById(R.id.btnAkun);

        btnAkun.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            startActivity(intent);
        });
}}
