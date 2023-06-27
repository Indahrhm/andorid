package com.privilage.laundry.view.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.privilage.laundry.R;

public class Login extends AppCompatActivity {
    Button btnSave;
    EditText tv_name, tv_password;
    private ProgressDialog progressDialog;
    private String parentDBName = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        setTitle("Login");
        btnSave = findViewById(R.id.btnSave);
        tv_name = findViewById(R.id.tvName);
        tv_password = findViewById(R.id.tvPassword);

        progressDialog = new ProgressDialog( this);

        btnSave.setOnClickListener((view) -> { login(); } );

    }

    private void login() {
        String name = tv_name.getText().toString();
        String password = tv_password.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText( this, "Please write your name..", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password..", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Login.this, "Logged In Successfully..", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

            loginUser(name, password);
        }
    }

    private void loginUser(String name, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child(parentDBName);
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child(parentDBName).child(name).exists()) && !(snapshot.child(parentDBName).child(password).exists())) {
//                    user userdata = snapshot.child(parentDBName).child(name).getValue(user.class);
                        Toast.makeText(Login.this, "Logged In Successfully..", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

