package com.privilage.laundry.view.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.privilage.laundry.R;

public class Welcome extends AppCompatActivity {

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.welcome);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister= findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);


        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        });


//        Paper.init(this);
//        String userEmailKey = Paper.book().read(prevalent.userEmailKey);


    }
}
